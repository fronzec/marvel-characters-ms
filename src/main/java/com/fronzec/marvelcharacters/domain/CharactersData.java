package com.fronzec.marvelcharacters.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fronzec.marvelcharacters.services.CharactersDataWrapper;
import com.fronzec.marvelcharacters.services.SingleComicResponse;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CharactersData {

    private Instant lastSync;
    private List<CharacterComicDto> characters;

    public CharactersData() {
    }

    public static CharactersData buildFrom(final List<SingleComicResponse> result) {
        CharactersData data = new CharactersData();
        HashMap<String, List<String>> map = new HashMap(1);
        data.setLastSync(Instant.now());
        result.stream()
                .map(SingleComicResponse::getCharacters)
                .map(CharactersDataWrapper::getItems)
                .forEach(charsPerComic -> {
                    charsPerComic.forEach(singleChar ->
                    {
                        if (!map.containsKey(singleChar.getName())) {
                            map.put(singleChar.getName(), Collections.singletonList(singleChar.getRootComic()));
                        } else {
                            // TODO: 30/01/2021 we could use dinstict
                            List<String> comics = map.get(singleChar.getName());
                            if (!comics.contains(singleChar.getRootComic()))
                                comics.add(singleChar.getRootComic());
                        }
                    });
                });

        List<CharacterComicDto> relations = new ArrayList<>();
        map.forEach((s, strings) -> {
            relations.add(new CharacterComicDto(s, strings));
        });
        data.setCharacters(relations);
        return data;
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
