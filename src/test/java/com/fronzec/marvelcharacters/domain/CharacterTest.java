package com.fronzec.marvelcharacters.domain;

import com.fronzec.marvelcharacters.domain.marvelresponses.SingleComicResponse;
import com.fronzec.marvelcharacters.util.JsonMapper;
import com.fronzec.marvelcharacters.utiltests.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CharacterTest {

    List<SingleComicResponse> responseList;
    Character character;

    @BeforeEach
    void setUp() {
        SingleComicResponse[] ra = JsonMapper.toObject(TestUtils.readFile("response_list_comics.json"), SingleComicResponse[].class);
        this.responseList = Arrays.asList(ra);
        this.character = new Character();
    }

    @Test
    void whenBuildFromThenAllOk() {
        Character actual = Character.buildFrom(character, responseList);
        assertNotNull(actual);
        assertNotNull(actual.getCharactersData());
        assertNotNull(actual.getCollaboratorsData());
    }
}