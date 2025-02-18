package ru.practicum.blog.repositories;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.practicum.blog.models.Like;

import java.util.Optional;

@Repository
public interface LikeRepository extends CrudRepository<Like, Long> {

    @Modifying
    @Query("UPDATE likes set like_count = like_count+1")
    void addLike(@Param("resourceId") Long postId);

    Optional<Like> findByResourceId(Long resourceId);

    @Modifying
    @Query("INSERT INTO likes (resource_id) values (:id  )")
    void create(@Param("id") Long id);
}
