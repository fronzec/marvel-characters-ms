package com.fronzec.marvelcharacters.controllers;

import com.fronzec.marvelcharacters.domain.CollaboratorsDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/colaborators")
public class CollaboratorsController {

    // TODO: 30/01/2021 get method by character nickname
    @GetMapping("/{characterNick}")
    public CollaboratorsDto getCollaborators(@PathVariable final String characterNick) {
        return null;
    }
}
