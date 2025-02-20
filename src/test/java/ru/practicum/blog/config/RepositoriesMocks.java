package ru.practicum.blog.config;

import org.springframework.test.context.bean.override.mockito.MockitoBean;
import ru.practicum.blog.repositories.CommentsRepository;
import ru.practicum.blog.repositories.LikeRepository;
import ru.practicum.blog.repositories.PostResourceCrudRepository;
import ru.practicum.blog.repositories.TagsRepository;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@MockitoBean(types = {PostResourceCrudRepository.class, LikeRepository.class, TagsRepository.class, CommentsRepository.class})
public @interface RepositoriesMocks {
}
