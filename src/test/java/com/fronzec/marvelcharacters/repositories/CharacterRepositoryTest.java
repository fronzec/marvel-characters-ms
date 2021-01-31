package com.fronzec.marvelcharacters.repositories;

import com.fronzec.marvelcharacters.domain.Character;
import com.fronzec.marvelcharacters.domain.marvelresponses.SingleComicResponse;
import com.fronzec.marvelcharacters.util.JsonMapper;
import com.fronzec.marvelcharacters.utiltests.TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ContextConfiguration(initializers = CharacterRepositoryTest.MongoDbInitializer.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@Slf4j
class CharacterRepositoryTest {

    @Autowired
    private CharacterRepository repository;

    List<SingleComicResponse> responseList;

    private static MongoDbContainer mongoDbContainer;

    @BeforeAll
    public static void startContainerAndPublicPortIsAvailable() {
        mongoDbContainer = new MongoDbContainer();
        mongoDbContainer.start();
    }

    @BeforeEach
    void setUp() {
        SingleComicResponse[] ra = JsonMapper.toObject(TestUtils.readFile("response_list_comics.json"), SingleComicResponse[].class);
        this.responseList = Arrays.asList(ra);
    }

    @Test
    void whenCreateCharacterAllOk() {
        Character baseCharacter = new Character();
        baseCharacter.setNick("myhero");
        baseCharacter.setMarvelId(1000);
        baseCharacter.setName("my hero");
        baseCharacter.setMustSync(true);
        Character character2Save = Character.buildFrom(baseCharacter, responseList);

        Character actual = repository.save(character2Save);

        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertTrue(actual.isMustSync());
        assertEquals(1000, actual.getMarvelId());
        assertEquals("myhero", actual.getNick());
        assertEquals("my hero", actual.getName());

        actual.setName("othername");
        Character updatedActual = repository.save(actual);
        assertEquals("othername", actual.getName());

        Optional<Character> myhero = repository.findByNick("myhero");
        Optional<Character> unexistinghero = repository.findByNick("unexistinghero");
        assertTrue(myhero.isPresent());
        assertFalse(unexistinghero.isPresent());
    }

    public static class MongoDbInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            log.info("Overrding Spring Properties for mongodb !!!!!!!!!");

            TestPropertyValues values = TestPropertyValues.of(
                    "spring.data.mongodb.host=" + mongoDbContainer.getContainerIpAddress(),
                    "spring.data.mongodb.port=" + mongoDbContainer.getPort()

            );
            values.applyTo(configurableApplicationContext);
        }
    }
}