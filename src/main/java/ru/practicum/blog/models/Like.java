package ru.practicum.blog.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("LIKES")
@Data
@NoArgsConstructor
public class Like {
    @Id
    private Long resourceId;
    private Long likeCount = 0L;

    public Like(Long id) {
        resourceId = id;
    }
}
