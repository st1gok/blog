package ru.practicum.blog.services;

import ru.practicum.blog.dto.CommentDto;

public interface CommentService {
//    void addComment(CommentDto comment, Long postId);

    CommentDto saveComment(CommentDto commentDto);
    void deleteComment(Long commentId);
}
