package com.sparta.springblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringblogApplication.class, args);
    }

}
