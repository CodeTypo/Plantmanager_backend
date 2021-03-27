package com.codetypo.plantmanager.consumer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//https://trefle.io/api/v1/species/190500?token=your_token

@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Plantopedia {
    private Data data;
}
