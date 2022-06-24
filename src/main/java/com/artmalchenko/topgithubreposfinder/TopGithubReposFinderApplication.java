package com.artmalchenko.topgithubreposfinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TopGithubReposFinderApplication {

    public static void main(String[] args) {
        SpringApplication.run(TopGithubReposFinderApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restBuilder) {
        return restBuilder.build();
    }

}
