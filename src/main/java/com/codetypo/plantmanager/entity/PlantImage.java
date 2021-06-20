package com.codetypo.plantmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PlantImage {

    public PlantImage(long plantId){
        this.plantId = plantId;
    }

    @Id
    @GeneratedValue
    private Long id;
    @Lob
    private byte[] content;

    private Long plantId;
}