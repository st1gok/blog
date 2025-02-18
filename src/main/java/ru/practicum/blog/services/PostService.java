package ru.practicum.blog.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.practicum.blog.dto.PostDto;

public interface PostService  {
    PostDto addPost(PostDto post);

    PostDto getPost(Long id);

    void deletePost(Long id);

    Page<PostDto> getNewestPosts(String tag, Pageable pageable);
}
