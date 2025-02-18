package ru.practicum.blog.config;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.Configuration;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class FlywayConfig {

    @Bean
    public Flyway flyway(
            @Value("${spring.datasource.url}") String url,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password
    ) {

        Configuration config = new FluentConfiguration()
                .dataSource(url, username, password)
                .locations("classpath:db/migration");
        Flyway flyway = new Flyway(config);
        return flyway;
    }


}
