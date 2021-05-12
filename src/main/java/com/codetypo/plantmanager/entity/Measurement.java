package com.codetypo.plantmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double temperature;
    private double humidity;
    private double brightness;
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "plant_id")
    @IndexedEmbedded
    private Plant plant;

    public Measurement(double temperature, double humidity, double brightness, LocalDateTime date, Plant plant) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.brightness = brightness;
        this.date = date;
        this.plant = plant;
    }
}
