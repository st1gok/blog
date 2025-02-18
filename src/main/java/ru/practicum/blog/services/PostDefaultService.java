package ru.practicum.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.blog.dto.PostDto;
import ru.practicum.blog.mappers.PostMapper;
import ru.practicum.blog.models.*;
import ru.practicum.blog.repositories.PostResourceCrudRepository;
import ru.practicum.blog.repositories.TagsRepository;

import java.util.*;

@Service
public class PostDefaultService implements PostService {

    protected final PostMapper postMapper;
    protected final PostResourceCrudRepository postResourceRepository;
    protected final TagsRepository tagsRepository;

    @Autowired
    public PostDefaultService(PostMapper postMapper,
                              PostResourceCrudRepository postResourceRepository,
                              TagsRepository tagsRepository
    ) {
        this.postMapper = postMapper;
        this.postResourceRepository = postResourceRepository;
        this.tagsRepository = tagsRepository;
    }

    @Override
    @Transactional
    public PostDto addPost(PostDto post) {
        Set<Tag> tags = new HashSet<>();
        if (post.getId() != null) {
            postResourceRepository.deleteTagsMapping(post.getId());
        }
        for (var tagName : post.getTags()) {
            Optional<Tag> tag = tagsRepository.findByTag(tagName);
            tags.add(tag.orElse(tagsRepository.save(tagName)));
        }
        Post postEntity = postMapper.toEntity(post);
        var newPost = postResourceRepository.save(postEntity);
        setTags(tags, newPost.getId());
        return postMapper.toDto(newPost);
    }


    private void setTags(Set<Tag> tags, Long id) {
        for (var tag : tags) {
            postResourceRepository.setTag(tag.getId(), id);
        }
    }

    Page<PostDto> getNewestResourcePosts(Pageable pageable) {
        Pageable page = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(new Sort.Order(Sort.Direction.DESC, "createdDate")));
        Page<Post> posts = postResourceRepository.findAll(page);
        addTagsToPosts(posts);
        return posts.map(postMapper::toDto);
    }

    @Transactional(readOnly = true)
    @Override
    public PostDto getPost(Long id) {
        Post post = postResourceRepository.findById(id).orElse(null);
        post.setTags(tagsRepository.findTagsByPostId(id));
        return postMapper.toDto(post);
    }

    private void addTagsToPosts(Streamable<Post> postStream) {
        postStream.forEach(post -> addTagsToPost(post));
    }
    private void addTagsToPost(Post post) {
        post.setTags(tagsRepository.findTagsByPostId(post.getId()));
    }

    @Override
    public Page<PostDto> getNewestPosts(String tag, Pageable pageable) {
        if (tag == null || tag.isEmpty()) {
            return getNewestResourcePosts(pageable);
        }
        return getNewestPostsByTag(tag, pageable);
    }

    private Page<PostDto> getNewestPostsByTag(String tag, Pageable pageable) {
        List<Post> posts = postResourceRepository.findNewestByTag(tag, pageable.getOffset(), pageable.getPageSize(), pageable);

        Page<Post> postPage = new PageImpl<>(posts, pageable, 0);
        addTagsToPosts(postPage);
        return postPage.map(postMapper::toDto);
    }

    @Override
    public void deletePost(Long id) {
        postResourceRepository.deleteTagsMapping(id);
        postResourceRepository.deleteById(id);
    }


}
