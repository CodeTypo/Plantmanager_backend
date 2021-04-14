package com.codetypo.plantmanager.consumer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

//https://trefle.io/api/v1/species/190500?token=your_token

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Plantopedia {
    private PlantData plantData;
}
