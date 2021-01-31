package com.fronzec.marvelcharacters.services;

import com.fronzec.marvelcharacters.domain.Character;
import com.fronzec.marvelcharacters.repositories.CharacterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SyncDataService.class)
class SyncDataServiceTest {

    @Autowired
    private SyncDataService service;

    @MockBean
    private CharacterRepository repository;

    @MockBean
    private MarvelApi marvelApi;

    List<Character> allCharacters = new ArrayList<>();

    @BeforeEach
    void beforeAll() {
        Character a = new Character();
        a.setName("ä");
        a.setMarvelId(1);
        a.setId("1");
        a.setMustSync(true);
        a.setNick("a");

        Character b = new Character();
        b.setName("b");
        b.setMarvelId(2);
        b.setId("2");
        b.setMustSync(true);
        b.setNick("b");

        allCharacters.add(a);
        allCharacters.add(b);

    }

    @Test
    void whenSyncDataThenCheckUpdateInfo() {
        Mockito.when(repository.findAll()).thenReturn(allCharacters);
        Mockito.when(marvelApi.GetComicsRecursively(ArgumentMatchers.anyInt()))
                .thenReturn(Collections.emptyList());

        service.syncData();

        Mockito.verify(repository, Mockito.times(2))
                .save(ArgumentMatchers.any(Character.class));
    }
}