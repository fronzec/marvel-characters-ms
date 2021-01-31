package com.fronzec.marvelcharacters.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SingleCreatorData {

    @JsonProperty("resourceURI")
    private String resourceURI;

    private String name;
    private String role;

    private static final String prefix = "http://gateway.marvel.com/v1/public/creators/";

    public SingleCreatorData() {
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
