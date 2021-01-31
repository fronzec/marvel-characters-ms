package com.fronzec.marvelcharacters.domain.marvelresponses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CharactersDataWrapper {

    private int available;
    private int returned;

    @JsonProperty("collectionURI")
    private String collectionURI;

    private List<SingleCharacterData> items = new ArrayList<>();

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

    public void propagateRootComic(final String comicName) {
        this.items.forEach(singleCharacterData -> {
            singleCharacterData.setRootComic(comicName);
        });
    }
}
