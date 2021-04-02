package com.codetypo.plantmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PlantmanagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlantmanagerApplication.class, args);
    }

}
