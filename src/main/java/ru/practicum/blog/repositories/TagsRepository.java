package ru.practicum.blog.repositories;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.practicum.blog.models.Tag;

import java.util.Optional;
import java.util.Set;

@Repository
public interface TagsRepository extends CrudRepository<Tag, Long> {

    @Query("SELECT * FROM NEW TABLE (INSERT INTO Tags (tag) values (:tag) ON CONFLICT DO NOTHING) t;")
    Tag save(@Param("tag") String tagName);

    @Query("SELECT tag FROM posts_tags pt left JOIN tags t ON pt.tag_id = t.id WHERE pt.post_id = :postId")
    Set<String> findTagsByPostId(@Param("postId") Long postId);

    Optional<Tag> findByTag(String tag);
}
