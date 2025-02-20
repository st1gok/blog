package ru.practicum.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.blog.dto.PostDto;
import ru.practicum.blog.mappers.PostMapper;
import ru.practicum.blog.models.Like;
import ru.practicum.blog.repositories.LikeRepository;
import ru.practicum.blog.repositories.PostResourceCrudRepository;
import ru.practicum.blog.repositories.TagsRepository;

import java.util.Optional;

@Service
public class LikeablePostDefaultService extends PostDefaultService implements LikeablePostService {

    private final LikeRepository likeRepository;

    @Autowired
    public LikeablePostDefaultService(PostMapper postMapper,
                                      PostResourceCrudRepository postResourceRepository,
                                      TagsRepository tagsRepository,
                                      LikeRepository likeRepository) {
        super(postMapper, postResourceRepository, tagsRepository);

        this.likeRepository = likeRepository;
    }

    @Override
    @Transactional
    public PostDto addPost(PostDto post) {
        PostDto postDto = super.addPost(post);
        if (post.getId() == null) {
            likeRepository.create(postDto.getId());
        }
        return postDto;
    }

    @Override
    public Page<PostDto> getNewestPosts(String tag, Pageable pageable) {
        var pagePosts = super.getNewestPosts(tag, pageable);
        for (PostDto postDto : pagePosts.getContent()) {
            addLikes(postDto);
        }
        return pagePosts;
    }

    @Override
    public PostDto getPost(Long id) {
        PostDto postDto = super.getPost(id);
        addLikes(postDto);
        return postDto;
    }

    private void addLikes(PostDto postDto) {
        Optional<Like> like = likeRepository.findByResourceId(postDto.getId());
        postDto.setLikeCount(like.orElseGet(Like::new).getLikeCount());
    }

    @Override
    public void addLike(Long resourceId) {
        likeRepository.addLike(resourceId);
    }
}
