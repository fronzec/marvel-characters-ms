package com.fronzec.marvelcharacters.controllers;

import com.fronzec.marvelcharacters.domain.Character;
import com.fronzec.marvelcharacters.domain.CollaboratorsData;
import com.fronzec.marvelcharacters.repositories.CharacterRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/colaborators")
public class CollaboratorsController {

    private CharacterRepository repository;

    public CollaboratorsController(CharacterRepository repository) {
        this.repository = repository;
    }

    // TODO: 30/01/2021 get method by character nickname
    @GetMapping("/{characterNick}")
    public CollaboratorsData getCollaborators(@PathVariable final String characterNick) {
        Character result = repository.findByNick(characterNick)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return result.getCollaboratorsData();
    }
}
