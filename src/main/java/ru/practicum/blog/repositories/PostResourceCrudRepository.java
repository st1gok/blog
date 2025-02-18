package ru.practicum.blog.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.practicum.blog.models.Post;

import java.util.List;

@Repository
public interface PostResourceCrudRepository extends PagingAndSortingRepository<Post, Long>, CrudRepository<Post, Long> {

      @Modifying
      @Query("insert into posts_tags (post_id, tag_id) values (:id, :tagsId)")
      void setTag(@Param("tagsId") Long tags, @Param("id") Long id);

      @Modifying
      @Query("DELETE from posts_tags where post_id = :postId")
      void deleteTagsMapping(@Param("postId") Long id);

      @Query("SELECT p.* from Posts p right join Posts_Tags pt ON p.id = pt.post_id left join Tags t ON pt.tag_id = t.id where t.tag=:tag ORDER BY p.created_date desc limit :limit offset :offset")
      List<Post> findNewestByTag(@Param("tag") String tag, @Param("offset") Long offset, @Param("limit") Integer limit, Pageable pageable);
}
