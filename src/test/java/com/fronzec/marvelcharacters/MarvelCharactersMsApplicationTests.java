package com.fronzec.marvelcharacters;

import com.fronzec.marvelcharacters.repositories.CharacterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class MarvelCharactersMsApplicationTests {

	@MockBean
	private CharacterRepository repository;

	@Test
	void contextLoads() {

	}

}
