package com.fronzec.marvelcharacters.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.Instant;
import java.util.List;

@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CharactersDto {

    private Instant lastSync;
    private List<CharacterComicDto> characters;

    public CharactersDto() {
    }

    public Instant getLastSync() {
        return lastSync;
    }

    public void setLastSync(Instant lastSync) {
        this.lastSync = lastSync;
    }

    public List<CharacterComicDto> getCharacters() {
        return characters;
    }

    public void setCharacters(List<CharacterComicDto> characters) {
        this.characters = characters;
    }
}
