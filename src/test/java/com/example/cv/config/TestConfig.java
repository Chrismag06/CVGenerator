package com.example.cv.config;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration
public class TestConfig{

    @Bean
    public TestRestTemplate testRestTemplate() {
        return new TestRestTemplate();
    }
}


