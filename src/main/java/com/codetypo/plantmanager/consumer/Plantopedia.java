package com.codetypo.plantmanager.consumer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//https://trefle.io/api/v1/species/190500?token=your_token

@JsonIgnoreProperties(ignoreUnknown = true)
public class Plantopedia {
    private Data data;

    public Plantopedia() {
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Plantopedia{" +
                "data=" + data.getCommonName() +
                '}';
    }
}
