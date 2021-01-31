package com.fronzec.marvelcharacters.controllers;

import com.fronzec.marvelcharacters.domain.Character;
import com.fronzec.marvelcharacters.repositories.CharacterRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static java.util.Optional.ofNullable;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CharactersController.class)
class CharactersControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CharacterRepository repository;

    Character result = new Character();

    @Test
    public void whenGetRelatedCharactersThenReturnInfo() throws Exception {
        when(repository.findByNick(ArgumentMatchers.anyString()))
                .thenReturn(ofNullable(result));
        mvc.perform(get("/characters/ironman"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenGetRelatedCharactersThenReturNotFound() throws Exception {
        when(repository.findByNick(ArgumentMatchers.anyString()))
                .thenReturn(Optional.empty());
        mvc.perform(get("/characters/unknown"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void whenCreateCharacterThenOk() throws Exception {
        when(repository.save(ArgumentMatchers.any(Character.class)))
                .thenReturn(result);
        String requestBody = "{\n" +
                "  \"nick\": \"ironman\",\n" +
                "  \"name\": \"Iron Man\",\n" +
                "  \"marvel_id\": 1009368,\n" +
                "  \"must_sync\": true\n" +
                "}";

        mvc.perform(post("/characters")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}