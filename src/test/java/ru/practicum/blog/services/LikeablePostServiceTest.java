package ru.practicum.blog.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.practicum.blog.config.RepositoriesMocks;
import ru.practicum.blog.dto.PostDto;
import ru.practicum.blog.models.Post;
import ru.practicum.blog.repositories.LikeRepository;
import ru.practicum.blog.repositories.PostResourceCrudRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RepositoriesMocks
class LikeablePostServiceTest {

    @Autowired
    private LikeablePostService likeablePostService;

    @Autowired
    private PostResourceCrudRepository postRepository;

    @Autowired
    private LikeRepository likeRepository;


    @BeforeEach
    void setUp() {
    }

    @Test
    void addPost() {
        Post createdPost = new Post();
        createdPost.setId(1l);
        createdPost.setName("PostName");
        createdPost.setText("PostText");
        when(postRepository.save(any(Post.class))).thenReturn(createdPost);

        PostDto newPost = new PostDto()
                .setName("PostName")
                .setText("PostText");
        PostDto createdPostDto = likeablePostService.addPost(newPost);
        assertNotNull(createdPost.getId());
        assertEquals(1l, createdPostDto.getId());
        assertEquals("PostName", createdPostDto.getName());
        assertEquals("PostText", createdPostDto.getText());
        verify(likeRepository, times(1)).create(1l);
    }

    @Test
    void addLike() {
        likeablePostService.addLike(1l);
        verify(likeRepository, times(1)).addLike(1l);
    }
}