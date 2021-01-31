package com.fronzec.marvelcharacters.domain;

import com.fronzec.marvelcharacters.domain.marvelresponses.SingleComicResponse;
import com.fronzec.marvelcharacters.util.JsonMapper;
import com.fronzec.marvelcharacters.utiltests.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class CharactersDataTest {

    List<SingleComicResponse> responseList;

    @BeforeEach
    void setUp() {
        SingleComicResponse[] ra = JsonMapper.toObject(TestUtils.readFile("response_list_comics.json"), SingleComicResponse[].class);
        this.responseList = Arrays.asList(ra);
    }

    @Test
    void whenBuildFromThenAllOk() {
        CharactersData actual = CharactersData.buildFrom(responseList);
        assertNotNull(actual.getLastSync());
        assertEquals(14, actual.getCharacters().size());
    }
}