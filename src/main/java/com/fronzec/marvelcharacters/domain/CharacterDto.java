package com.fronzec.marvelcharacters.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacterDto {

    @NotNull
    @NotEmpty
    private String nick;

    @NotNull
    @NotEmpty
    private String name;

    @Min(0)
    private int marvelId;

    private boolean mustSync = false;

    public CharacterDto() {
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMarvelId() {
        return marvelId;
    }

    public void setMarvelId(int marvelId) {
        this.marvelId = marvelId;
    }

    public boolean isMustSync() {
        return mustSync;
    }

    public void setMustSync(boolean mustSync) {
        this.mustSync = mustSync;
    }
}
