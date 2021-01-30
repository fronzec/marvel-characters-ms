package com.fronzec.marvelcharacters.domain;

import java.util.List;


public class CharacterComicDto {

    private String character;
    private List<String> comics;

    public CharacterComicDto() {
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public List<String> getComics() {
        return comics;
    }

    public void setComics(List<String> comics) {
        this.comics = comics;
    }
}
