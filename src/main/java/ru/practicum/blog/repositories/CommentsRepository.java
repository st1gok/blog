package ru.practicum.blog.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.blog.models.Comment;

@Repository
public interface CommentsRepository extends CrudRepository<Comment, Long> {

}
