package com.fronzec.marvelcharacters;

import com.fronzec.marvelcharacters.domain.Character;
import com.fronzec.marvelcharacters.repositories.CharacterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Optional;

@SpringBootApplication
@EnableScheduling
public class MarvelCharactersMsApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MarvelCharactersMsApplication.class);
    private final CharacterRepository repository;

    public MarvelCharactersMsApplication(CharacterRepository repository) {
        this.repository = repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(MarvelCharactersMsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        logger.info("------ Begin:: Loading initial data");

        Optional<Character> im = repository.findByNick("ironman");
        Optional<Character> ca = repository.findByNick("capamerica");

        if (!im.isPresent()) {
            Character ironman = new Character();
            ironman.setNick("ironman");
            ironman.setName("Iron Man");
            ironman.setMustSync(true);
            ironman.setMarvelId(1009368);
            logger.info("------ Initial Iron Man data will be inserted");
            repository.save(ironman);
        }

        if (!ca.isPresent()) {
            Character capamerica = new Character();
            capamerica.setNick("capamerica");
            capamerica.setName("Captain America");
            capamerica.setMustSync(true);
            capamerica.setMarvelId(1009220);
            logger.info("------ Initial Captain America data will be inserted");
            repository.save(capamerica);
        }

        logger.info("------ End:: Loading initial data");

    }

}
