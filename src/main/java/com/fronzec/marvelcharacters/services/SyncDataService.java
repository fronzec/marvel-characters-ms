package com.fronzec.marvelcharacters.services;

import com.fronzec.marvelcharacters.domain.Character;
import com.fronzec.marvelcharacters.domain.marvelresponses.SingleComicResponse;
import com.fronzec.marvelcharacters.repositories.CharacterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This service provide main logic to sync remote data
 */
@Service
public class SyncDataService {

    private static final Logger logger = LoggerFactory.getLogger(SyncDataService.class);
    private final MarvelApi marvelApi;
    private final CharacterRepository repository;

    public SyncDataService(final MarvelApi marvelApi,
                           final CharacterRepository repository) {
        this.marvelApi = marvelApi;
        this.repository = repository;
    }


    public void syncData() {
        logger.info("[SYNC-PROCESS]:: Syncing data");
        List<Character> all = repository.findAll();
        all.stream().filter(Character::isMustSync)
                .forEach(character -> {
                    try {
                        logger.info("SYNCYNG -> {}", character.getName());
                        List<SingleComicResponse> result = marvelApi.GetComicsRecursively(character.getMarvelId());
                        Character updatedCharacter = Character.buildFrom(character, result);
                        repository.save(updatedCharacter);
                    } catch (Exception e) {
                        logger.error("Error syncing data for -> {} e -> {}",
                                character.getName(), e.getMessage(), e);
                    }
                });
    }
}
