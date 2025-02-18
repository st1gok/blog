package ru.practicum.blog.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@NoArgsConstructor
public class PostDto {
    private static final int TEXT_MAX_LENGTH_FOR_PREVIEW = 5;
    private Long id;
    private String name;
    private MultipartFile img;
    private String text;
    private Set<String> tags = new HashSet<>();
    private Long likeCount;
    private Set<CommentDto> comments = new HashSet<>();

    public Set<CommentDto> getComments() {
        return comments;
    }

    public PostDto setComments(Set<CommentDto> comments) {
        this.comments = comments;
        return this;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public MultipartFile getImg() {
        return img;
    }

    public PostDto setImg(MultipartFile img) {
        this.img = img;
        return this;
    }

    public PostDto setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
        return this;
    }

    public PostDto setTagsString(String tagsString) {
        this.tags = stringTagsToSet(tagsString);
        return this;
    }

    public PostDto setTags(Set<String> tags) {
        this.tags = tags;
        return this;
    }

    private Set<String> stringTagsToSet(String tagsString) {
        tagsString = tagsString.strip();
        if (tagsString == null || tagsString.isEmpty()) {
            return new HashSet<>();
        }
        return new HashSet<>(List.of(tagsString.strip().split(" ")));
    }

    public String getPreview() {
        int posOfFirstParagraphEnd = text.contains("\n") ? text.indexOf("\n") : (text.length() - 1);
        if (posOfFirstParagraphEnd <= TEXT_MAX_LENGTH_FOR_PREVIEW) {
            return text.substring(0, posOfFirstParagraphEnd + 1);
        }

        return text.substring(0, TEXT_MAX_LENGTH_FOR_PREVIEW);
    }

    public Long getId() {
        return id;
    }

    public PostDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PostDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getText() {
        return text;
    }

    public PostDto setText(String text) {
        this.text = text;
        return this;
    }

    public Set<String> getTags() {
        return tags;
    }

    public String getTagsString() {
        return String.join(" ", tags);
    }

    public String getBase64Image() throws IOException {
        return Base64.getEncoder().encodeToString(img.getBytes());
    }
}
