package com.fronzec.marvelcharacters;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MarvelCharactersMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarvelCharactersMsApplication.class, args);
	}

}
