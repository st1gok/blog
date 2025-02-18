package ru.practicum.blog.models;

import lombok.Data;

@Data
public abstract class Resource implements java.io.Serializable{

    private boolean visible;
    private boolean deleted;
    private long createdDate = System.currentTimeMillis();

}
