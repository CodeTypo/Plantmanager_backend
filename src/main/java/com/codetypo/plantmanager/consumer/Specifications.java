package com.codetypo.plantmanager.consumer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Specifications {
    private String toxicity; //none, low, medium, and high

    public Specifications() {
    }

    public String getToxicity() {
        return toxicity;
    }

    public void setToxicity(String toxicity) {
        this.toxicity = toxicity;
    }

    @Override
    public String toString() {
        return "Specifications{" +
                "toxicity='" + toxicity + '\'' +
                '}';
    }
}
