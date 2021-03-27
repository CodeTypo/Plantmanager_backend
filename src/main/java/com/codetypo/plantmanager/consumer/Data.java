package com.codetypo.plantmanager.consumer;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
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
}
