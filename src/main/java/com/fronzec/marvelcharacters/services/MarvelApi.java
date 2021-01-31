package com.fronzec.marvelcharacters.services;

import com.fronzec.marvelcharacters.domain.marvelresponses.ComicResponseData;
import com.fronzec.marvelcharacters.domain.marvelresponses.ComicsResponseRoot;
import com.fronzec.marvelcharacters.domain.marvelresponses.SingleComicResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Provide methods to interact with marvel api
 */
@Service
public class MarvelApi {

    private static final Logger logger = LoggerFactory.getLogger(MarvelApi.class);
    private static final int OK_CODE = 200;
    private String MarvelApiBaseUrl = "http://gateway.marvel.com/v1/public";
    private String GetComicsPath = "/characters/{characterId}/comics";
    private String Params = "?ts={ts}&apikey={apikey}&hash={hash}&offset={offset}&limit={limit}";

    private RestTemplate restTemplate;
    private AuthProvider authProvider;

    public MarvelApi(final RestTemplateBuilder restTemplateBuilder,
                     final AuthProvider authProvider) {
        this.restTemplate = restTemplateBuilder.setConnectTimeout(Duration.ofSeconds(32))
                .setReadTimeout(Duration.ofSeconds(32))
                .build();
        this.authProvider = authProvider;
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
                    .buildAndExpand(characterId, AuthProvider.getApiTs(),
                            authProvider.getApiPublicKey(), authProvider.getApiHash(),
                            offset, limit)
                    .toUriString();

            ComicsResponseRoot response = restTemplate.getForObject(uri, ComicsResponseRoot.class);
            if (response != null && response.getCode() != OK_CODE && response.getData() != null) {
                totalItems = Optional.ofNullable(response.getData())
                        .map(ComicResponseData::getTotal)
                        .orElse(0);

                List<SingleComicResponse> results = Optional.ofNullable(response.getData())
                        .map(ComicResponseData::getResults)
                        .orElse(new ArrayList<>());
                comics.addAll(results);
            }

            offset += limit;
        } while (offset < totalItems);

        logger.info("Total response size -> {}", comics.size());
        return comics;
    }

}
