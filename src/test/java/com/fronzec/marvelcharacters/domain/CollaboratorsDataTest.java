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

class CollaboratorsDataTest {

    List<SingleComicResponse> responseList;

    @BeforeEach
    void setUp() {
        SingleComicResponse[] ra = JsonMapper.toObject(TestUtils.readFile("response_list_comics.json"), SingleComicResponse[].class);
        this.responseList = Arrays.asList(ra);
    }

    @Test
    void whenBuildFromThenAllOk() {
        CollaboratorsData actual = CollaboratorsData.buildFrom(responseList);
        assertNotNull(actual.getLastSync());
        assertEquals(1, actual.getColorists().size());
        assertEquals(1, actual.getEditors().size());
        assertEquals(1, actual.getWriters().size());
        assertEquals("Tom Brevoort", actual.getEditors().get(0));
        assertEquals("Brian Michael Bendis", actual.getWriters().get(0));
        assertEquals("Dean White", actual.getColorists().get(0));
    }
}