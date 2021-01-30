package com.fronzec.marvelcharacters.controllers;

import com.fronzec.marvelcharacters.domain.CharacterComicDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharactersController {

    private static final Logger logger = LoggerFactory.getLogger(CharactersController.class);

    @GetMapping("/{characterNick}")
    public List<CharacterComicDto> getRelatedCharacters(@PathVariable final String characterNick) {
        // TODO: 30/01/2021 fetch service info
        return null;
    }

}
