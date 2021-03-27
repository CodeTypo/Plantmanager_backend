package com.codetypo.plantmanager.consumer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

//https://docs.trefle.io/docs/advanced/plants-fields

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Growth {
    @JsonProperty("ph_maximum")
    private float phMaximum;
    @JsonProperty("ph_minimum")
    private float phMinimum;
    private int light; // 0-10 -> 0 ~ 10 lux, 10 ~ 100 000 lux
    @JsonProperty("atmospheric_humidity")
    private int atmosphericHumidity;
    @JsonProperty("soil_nutriments")
    private int soilNutriments;
    @JsonProperty("soil_salinity")
    private int soilSalinity;
    @JsonProperty("soil_texture")
    private int soilTexture;
}
