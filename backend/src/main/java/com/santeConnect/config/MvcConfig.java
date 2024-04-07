package com.santeConnect.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * To allow test coverage to be shown at ${URL}/jacoco
 * reports is generated with jacoco, after `mvn install`
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/jacoco/**")
                .addResourceLocations("file:target/site/jacoco/");
    }
}