package ru.practicum.blog.config;

import jakarta.servlet.FilterRegistration;
import jakarta.servlet.ServletContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
public class Initializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) {
        FilterRegistration.Dynamic filterRegistration = servletContext.addFilter("encodingFilter", new CharacterEncodingFilter());
        filterRegistration.setInitParameter("encoding", "UTF-8");
        filterRegistration.setInitParameter("forceEncoding", "true");
        filterRegistration.addMappingForUrlPatterns(null, true, "/*");

    }
}
