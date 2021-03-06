package com.fronzec.marvelcharacters.controllers;

import com.fronzec.marvelcharacters.domain.Character;
import com.fronzec.marvelcharacters.domain.CharacterDto;
import com.fronzec.marvelcharacters.domain.CharactersData;
import com.fronzec.marvelcharacters.repositories.CharacterRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

/**
 * Expose information related for the given character, by nick, also allow create news
 */
@RestController
@RequestMapping("/characters")
public class CharactersController {

    private final CharacterRepository repository;

    public CharactersController(final CharacterRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{characterNick}")
    public CharactersData getRelatedCharacters(@PathVariable final String characterNick) {
        Character result = repository.findByNick(characterNick)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return Optional.ofNullable(result.getCharactersData())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT));
    }

    @PostMapping
    public Character createCharacter(@RequestBody final CharacterDto characterDto) {
        Character c = repository.findByNick(characterDto.getNick()).orElse(new Character());
        c.setNick(characterDto.getNick());
        c.setMarvelId(characterDto.getMarvelId());
        c.setName(characterDto.getName());
        c.setMustSync(characterDto.isMustSync());
        return repository.save(c);
    }

}
