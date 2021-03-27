package com.codetypo.plantmanager.consumer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {
    @JsonProperty("common_name")
    private String commonName;
    @JsonProperty("image_url")
    private String imageUrl;
    private boolean vegetable;
    private boolean edible;
    private Specifications specifications;
    private Growth growth;

    public Data() {
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isVegetable() {
        return vegetable;
    }

    public void setVegetable(boolean vegetable) {
        this.vegetable = vegetable;
    }

    public boolean isEdible() {
        return edible;
    }

    public void setEdible(boolean edible) {
        this.edible = edible;
    }

    public Specifications getSpecifications() {
        return specifications;
    }

    public void setSpecifications(Specifications specifications) {
        this.specifications = specifications;
    }

    public Growth getGrowth() {
        return growth;
    }

    public void setGrowth(Growth growth) {
        this.growth = growth;
    }

    @Override
    public String toString() {
        return "Data{" +
                "common_name='" + commonName + '\'' +
                ", image_url='" + imageUrl + '\'' +
                ", vegetable=" + vegetable +
                ", edible=" + edible +
                ", specifications=" + specifications +
                ", growth=" + growth +
                '}';
    }
}
