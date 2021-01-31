package com.fronzec.marvelcharacters.controllers;

import com.fronzec.marvelcharacters.domain.Character;
import com.fronzec.marvelcharacters.domain.CollaboratorsData;
import com.fronzec.marvelcharacters.repositories.CharacterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static java.util.Optional.ofNullable;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CollaboratorsController.class)
class CollaboratorsControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CharacterRepository repository;
    Character result = new Character();

    @BeforeEach
    void setUp() {
        result.setCollaboratorsData(new CollaboratorsData());
    }

    @Test
    public void whenGetCollaboratorsThenReturnInfo() throws Exception {
        when(repository.findByNick(ArgumentMatchers.anyString()))
                .thenReturn(ofNullable(result));
        mvc.perform(get("/colaborators/ironman"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenGetCollaboratorsThenReturnNotFound() throws Exception {
        when(repository.findByNick(ArgumentMatchers.anyString()))
                .thenReturn(Optional.empty());
        mvc.perform(get("/colaborators/unknown"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}