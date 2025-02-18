package ru.practicum.blog.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@Configuration
public class MigrateService {
    @Autowired
    private Flyway flyway;

    @EventListener
    public void migrateDatabase(ContextRefreshedEvent event) {
        flyway.migrate();
    }
}
