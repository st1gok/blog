package ru.practicum.blog.mappers;

import org.mapstruct.Mapper;
import ru.practicum.blog.dto.CommentDto;
import ru.practicum.blog.models.Comment;

@Mapper(componentModel = "spring", uses = {})
public interface CommentMapper  extends EntityMapper<CommentDto, Comment> {

}
