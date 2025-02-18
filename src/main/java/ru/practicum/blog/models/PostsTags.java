package ru.practicum.blog.models;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table
@Data
public class PostsTags {
@Column("TAG_ID")
    public Long tagId;
    public Long postId;
}
