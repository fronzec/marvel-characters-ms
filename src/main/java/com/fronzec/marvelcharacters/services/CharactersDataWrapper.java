package com.fronzec.marvelcharacters.services;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CharactersDataWrapper {

    private int available;
    private int returned;

    @JsonProperty("collectionURI")
    private String collectionURI;

    private List<SingleCharacterData> items;

    public CharactersDataWrapper() {
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

    public List<SingleCharacterData> getItems() {
        return items;
    }

    public void setItems(List<SingleCharacterData> items) {
        this.items = items;
    }
}
