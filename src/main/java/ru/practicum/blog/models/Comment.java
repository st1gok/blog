package ru.practicum.blog.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("COMMENTS")
public class Comment {
    @Id
    private Long id;
    private Long postId;
    private String text;
}
