package com.example.prello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PrelloApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrelloApplication.class, args);
    }

}
