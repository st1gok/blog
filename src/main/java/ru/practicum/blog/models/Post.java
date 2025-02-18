package ru.practicum.blog.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Data
@Table("POSTS")
public class Post extends Resource{
    @Id
    private Long id;
    private String name;
    private byte[] img;
    private String text;

    @MappedCollection(idColumn = "POST_ID")
    private Set<Comment> comments;

    @Transient private Set<String> tags;

    public void addComment(Comment comment) {
        comments.add(comment);
    }
}
