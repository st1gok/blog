package ru.practicum.blog.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("TAGS")
public class Tag {

    @Id
    Long id;
    String tag;
}
