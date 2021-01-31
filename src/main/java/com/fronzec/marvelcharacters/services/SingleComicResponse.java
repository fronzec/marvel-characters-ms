package com.fronzec.marvelcharacters.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SingleComicResponse {

    private int id;
    private String title;
    private CreatorsDataWrapper creators;
    private CharactersDataWrapper characters;

    public SingleComicResponse() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CreatorsDataWrapper getCreators() {
        return creators;
    }

    public void setCreators(CreatorsDataWrapper creators) {
        this.creators = creators;
    }

    public CharactersDataWrapper getCharacters() {
        return characters;
    }

    public void setCharacters(CharactersDataWrapper characters) {
        this.characters = characters;
    }
}
