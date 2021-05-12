package com.codetypo.plantmanager;

import com.codetypo.plantmanager.entity.Measurement;
import com.codetypo.plantmanager.entity.Plant;
import com.codetypo.plantmanager.repository.PlantRepo;
import com.codetypo.plantmanager.service.MeasurementService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableJpaAuditing
public class PlantmanagerApplication {

    PlantRepo plantRepo;
    MeasurementService measurementService;

    public PlantmanagerApplication(PlantRepo plantRepo, MeasurementService measurementService) {
        this.plantRepo = plantRepo;
        this.measurementService = measurementService;
    }

    public static void main(String[] args) {
        SpringApplication.run(PlantmanagerApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/users").allowedOrigins("http://localhost:3000");
            }
        };
    }

// add example measurement
//    @EventListener(ApplicationReadyEvent.class)
//    public void fill(){
//        Plant p = plantRepo.findById(2L).get();
//        Measurement measurement = new Measurement(25, 32, 75, LocalDateTime.now(), p);
//        measurementService.save(measurement);
//    }
}
