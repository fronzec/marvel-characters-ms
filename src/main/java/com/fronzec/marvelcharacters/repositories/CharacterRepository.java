package com.fronzec.marvelcharacters.repositories;

import com.fronzec.marvelcharacters.domain.Character;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CharacterRepository extends MongoRepository<Character, String> {
    public Optional<Character> findByNick(final String nick);
}
