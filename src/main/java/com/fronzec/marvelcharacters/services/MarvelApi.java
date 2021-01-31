package com.fronzec.marvelcharacters.services;

import com.fronzec.marvelcharacters.util.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MarvelApi {

    private static final Logger logger = LoggerFactory.getLogger(MarvelApi.class);
    private String MarvelApiBaseUrl = "http://gateway.marvel.com/v1/public";
    private String GetComicsPath = "/characters/{characterId}/comics";
    private String Params = "?ts={ts}&apikey={apikey}&hash={hash}&offset={offset}&limit={limit}";

    private String API_TS;
    private String API_KEY;
    private String API_HASH;

    private RestTemplate restTemplate;

    public MarvelApi(final RestTemplateBuilder restTemplateBuilder,
                     final @Value("${marvel.api.ts}") String apiTS,
                     final @Value("${marvel.api.key}") String apiKey,
                     final @Value("${marvel.api.hash}") String apiHash) {
        this.restTemplate = restTemplateBuilder.setConnectTimeout(Duration.ofSeconds(32))
                .setReadTimeout(Duration.ofSeconds(32))
                .build();
        this.API_TS = apiTS;
        this.API_KEY = apiKey;
        this.API_HASH = apiHash;
    }


    public List<SingleComicResponse> GetComicsRecursively(final int characterId) {

        // Offset from the first item
        int offset = 0;
        // Items per page
        int limit = 100;
        // Total items, we update it on response success
        int totalItems = 0;
        List<SingleComicResponse> comics = new ArrayList<>();

        do {
            logger.info("[MARVEL-REQUEST]:: requesting data with offset -> {}," +
                    " limit -> {}, totalItems -> {}", offset, limit, totalItems);
            String uri = UriComponentsBuilder.fromUriString(MarvelApiBaseUrl + GetComicsPath + Params)
                    .buildAndExpand(characterId, API_TS, API_KEY, API_HASH, offset, limit)
                    .toUriString();

            ComicsResponseRoot response = restTemplate.getForObject(uri, ComicsResponseRoot.class);
            if (response != null)
                totalItems = Optional.ofNullable(response.getData())
                        .map(ComicResponseData::getTotal)
                        .orElse(0);

            comics.addAll(response.getData().getResults());
            logger.info("actual size -> {}", comics.size());
            offset += limit;
        } while (offset < totalItems);

        logger.info("Total response size -> {}", comics.size());
        return comics;
    }

}
