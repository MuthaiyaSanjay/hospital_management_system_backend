package com.medicare.configuration;


import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public GroupedOpenApi customGroup() {
        return GroupedOpenApi.builder()
                .group("custom-group")
                //.pathsToMatch("/v1/**") // Specify the paths to include
                .pathsToExclude("/internal/**") // Optionally, specify the paths to exclude
                .build();
    }
}

