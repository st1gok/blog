package ru.practicum.blog.services;

import org.springframework.stereotype.Service;
import ru.practicum.blog.dto.CommentDto;
import ru.practicum.blog.mappers.CommentMapper;
import ru.practicum.blog.repositories.CommentsRepository;

@Service
public class PostCommentService implements CommentService {
    private final CommentsRepository commentsRepository;
    private final CommentMapper commentMapper;

    public PostCommentService(CommentsRepository commentsRepository,
                              CommentMapper commentMapper) {
        this.commentsRepository = commentsRepository;
        this.commentMapper = commentMapper;
    }


    @Override
    public CommentDto saveComment(CommentDto commentDto) {
        return commentMapper.toDto(commentsRepository.save(commentMapper.toEntity(commentDto)));
    }

    @Override
    public void deleteComment(Long commentId) {
        commentsRepository.deleteById(commentId);
    }
}
