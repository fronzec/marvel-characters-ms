package com.fronzec.marvelcharacters.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fronzec.marvelcharacters.services.CreatorsDataWrapper;
import com.fronzec.marvelcharacters.services.SingleComicResponse;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CollaboratorsData {

    private static final String EDITOR = "editor";
    private static final String WRITER = "writer";
    private static final String COLORIST = "colorist";
    private Instant lastSync;
    //editor
    private List<String> editors = new ArrayList<>();
    //writer
    private List<String> writers = new ArrayList<>();
    //colorist
    private List<String> colorists = new ArrayList<>();

    public CollaboratorsData() {
    }

    public static CollaboratorsData buildFrom(final List<SingleComicResponse> result) {
        CollaboratorsData collaboratorsData = new CollaboratorsData();
        collaboratorsData.setLastSync(Instant.now());

        result.stream().map(SingleComicResponse::getCreators)
                .map(CreatorsDataWrapper::getItems)
                .forEach( creators -> {
                    creators.forEach(c -> {
                        if(c.getRole().equals(EDITOR)) {
                            if(!collaboratorsData.getEditors().contains(c.getName()))
                                collaboratorsData.getEditors().add(c.getName());
                        } else if(c.getRole().equals(WRITER)) {
                            if(!collaboratorsData.getWriters().contains(c.getName()))
                                collaboratorsData.getWriters().add(c.getName());
                        }else if(c.getRole().equals(COLORIST)) {
                            if(!collaboratorsData.getColorists().contains(c.getName()))
                                collaboratorsData.getColorists().add(c.getName());
                        }
                    });
                });

        return collaboratorsData;
    }

    public Instant getLastSync() {
        return lastSync;
    }

    public void setLastSync(Instant lastSync) {
        this.lastSync = lastSync;
    }

    public List<String> getEditors() {
        return editors;
    }

    public void setEditors(List<String> editors) {
        this.editors = editors;
    }

    public List<String> getWriters() {
        return writers;
    }

    public void setWriters(List<String> writers) {
        this.writers = writers;
    }

    public List<String> getColorists() {
        return colorists;
    }

    public void setColorists(List<String> colorists) {
        this.colorists = colorists;
    }

}
