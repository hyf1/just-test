package com.hyf.springboot.justtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JustTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(JustTestApplication.class, args);
    }
}
