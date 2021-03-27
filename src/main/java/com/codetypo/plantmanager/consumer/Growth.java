package com.codetypo.plantmanager.consumer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

//https://docs.trefle.io/docs/advanced/plants-fields

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

    public Growth() {
    }

    @Override
    public String toString() {
        return "Growth{" +
                "phMaximum=" + phMaximum +
                ", phMinimum=" + phMinimum +
                ", light=" + light +
                ", atmosphericHumidity=" + atmosphericHumidity +
                ", soilNutriments=" + soilNutriments +
                ", soilSalinity=" + soilSalinity +
                ", soilTexture=" + soilTexture +
                '}';
    }

    public float getPhMaximum() {
        return phMaximum;
    }

    public void setPhMaximum(float phMaximum) {
        this.phMaximum = phMaximum;
    }

    public float getPhMinimum() {
        return phMinimum;
    }

    public void setPhMinimum(float phMinimum) {
        this.phMinimum = phMinimum;
    }

    public int getLight() {
        return light;
    }

    public void setLight(int light) {
        this.light = light;
    }

    public int getAtmosphericHumidity() {
        return atmosphericHumidity;
    }

    public void setAtmosphericHumidity(int atmosphericHumidity) {
        this.atmosphericHumidity = atmosphericHumidity;
    }

    public int getSoilNutriments() {
        return soilNutriments;
    }

    public void setSoilNutriments(int soilNutriments) {
        this.soilNutriments = soilNutriments;
    }

    public int getSoilSalinity() {
        return soilSalinity;
    }

    public void setSoilSalinity(int soilSalinity) {
        this.soilSalinity = soilSalinity;
    }

    public int getSoilTexture() {
        return soilTexture;
    }

    public void setSoilTexture(int soilTexture) {
        this.soilTexture = soilTexture;
    }
}
