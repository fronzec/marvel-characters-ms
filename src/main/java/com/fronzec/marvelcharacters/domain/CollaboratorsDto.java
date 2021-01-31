package com.fronzec.marvelcharacters.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.Instant;
import java.util.List;

@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CollaboratorsDto {
    private Instant lastSync;
    private List<String> editors;
    private List<String> writers;
    private List<String> colorists;

    public CollaboratorsDto() {
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
