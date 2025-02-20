package ru.practicum.blog.services;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.practicum.blog.config.RepositoriesMocks;
import ru.practicum.blog.config.TestConfig;
import ru.practicum.blog.dto.CommentDto;
import ru.practicum.blog.models.Comment;
import ru.practicum.blog.repositories.CommentsRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = TestConfig.class)
@RepositoriesMocks
class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Autowired
    CommentsRepository commentsRepository;

    @Test
    void addComment() {
        CommentDto commentDto = new CommentDto();
        commentDto.setPostId(1L);
        commentDto.setText("comment");

        commentService.saveComment(commentDto);
        ArgumentCaptor<Comment> comment = ArgumentCaptor.forClass(Comment.class);
        Mockito.verify(commentsRepository, Mockito.times(1)).save(comment.capture());
        assertNull(comment.getValue().getId());
        assertEquals(1l, comment.getValue().getPostId());
        assertEquals("comment", comment.getValue().getText());
    }

    @Test
    void deleteComment() {
        commentsRepository.deleteById(1l);
        Mockito.verify(commentsRepository, Mockito.times(1)).deleteById(1L);
    }
}