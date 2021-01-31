package com.fronzec.marvelcharacters.domain.marvelresponses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatorsDataWrapper {

    private int available;
    private int returned;

    @JsonProperty("collectionURI")
    private String collectionURI;

    private List<SingleCreatorData> items;

    public CreatorsDataWrapper() {
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getReturned() {
        return returned;
    }

    public void setReturned(int returned) {
        this.returned = returned;
    }

    public List<SingleCreatorData> getItems() {
        return items;
    }

    public void setItems(List<SingleCreatorData> items) {
        this.items = items;
    }

    public String getCollectionURI() {
        return collectionURI;
    }

    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }
}
