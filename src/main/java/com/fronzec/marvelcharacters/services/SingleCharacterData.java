package com.fronzec.marvelcharacters.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SingleCharacterData {

    @JsonProperty("resourceURI")
    private String resourceURI;

    private String name;

    private static final String prefix = "http://gateway.marvel.com/v1/public/characters/";

    public SingleCharacterData() {
    }

    public int extractId() {
        return Integer.parseInt(this.name.replace(prefix, ""));
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
