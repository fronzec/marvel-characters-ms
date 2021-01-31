package com.fronzec.marvelcharacters.domain;

import com.fronzec.marvelcharacters.services.SingleComicResponse;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Character {

    @Id
    private String id;

    private int marvelId;
    private String name;
    private String nick;
    private boolean mustSync;

    // NOTE: 30/01/2021 response first service
    private CollaboratorsData collaboratorsData;

    // NOTE: 30/01/2021 response second service
    private CharactersData charactersData;

    public Character() {
    }

    public static Character buildFrom(final int marvelId,
                                      final String name,
                                      final String nick,
                                      final List<SingleComicResponse> result) {
        // Propagate root comic name
        result.forEach(singleComicResponse -> singleComicResponse.getCharacters().propagateRootComic(singleComicResponse.getTitle()));

        CollaboratorsData collaboratorsData = CollaboratorsData.buildFrom(result);
        CharactersData charactersData = CharactersData.buildFrom(result);
        Character character = new Character();
        character.setCharactersData(charactersData);
        character.setCollaboratorsData(collaboratorsData);
        character.setMarvelId(marvelId);
        character.setNick(nick);
        character.setName(name);
        return new Character();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMarvelId() {
        return marvelId;
    }

    public void setMarvelId(int marvelId) {
        this.marvelId = marvelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public boolean isMustSync() {
        return mustSync;
    }

    public void setMustSync(boolean mustSync) {
        this.mustSync = mustSync;
    }

    public CollaboratorsData getCollaboratorsData() {
        return collaboratorsData;
    }

    public void setCollaboratorsData(CollaboratorsData collaboratorsData) {
        this.collaboratorsData = collaboratorsData;
    }

    public CharactersData getCharactersData() {
        return charactersData;
    }

    public void setCharactersData(CharactersData charactersData) {
        this.charactersData = charactersData;
    }
}
