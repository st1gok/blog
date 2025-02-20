package ru.practicum.blog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration()

@ComponentScan(basePackages = {"ru.practicum.blog.mappers", "ru.practicum.blog.services"})
public class TestConfig {
}


