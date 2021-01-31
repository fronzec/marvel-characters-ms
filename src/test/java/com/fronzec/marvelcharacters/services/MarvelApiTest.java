package com.fronzec.marvelcharacters.services;

import com.fronzec.marvelcharacters.domain.marvelresponses.SingleComicResponse;
import com.fronzec.marvelcharacters.repositories.CharacterRepository;
import com.fronzec.marvelcharacters.utiltests.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.queryParam;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@ExtendWith(SpringExtension.class)
@RestClientTest(MarvelApi.class)
@Import(value = {AuthProvider.class, CharacterRepository.class})
class MarvelApiTest {

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private AuthProvider authProvider;

    @Autowired
    private MarvelApi marvelApi;

    @MockBean
    private CharacterRepository repository;

    private static final int OK_CODE = 200;
    private String MarvelApiBaseUrl = "http://gateway.marvel.com/v1/public";
    private String GetComicsPath = "/characters/{characterId}/comics";
    private String Params = "?ts={ts}&apikey={apikey}&hash={hash}&offset={offset}&limit={limit}";
    private static final String PAGE_SIZE_PARAM = "limit";
    private static final String OFFSET_PARAM = "offset";
    int characterId1 = 1111;
    int characterId2 = 2222;
    int characterId3 = 3333;
    String uriOk1Page1;
    String uriOk1Page2;
    String uriOk2;
    String uriBad;
    // Offset from the first item
    int offset = 0;
    // Items per page
    int limit = 100;
    // Total items, we update it on response success
    int totalItems = 0;

    @BeforeEach
    void setUp() {
        uriOk1Page1 = UriComponentsBuilder.fromUriString(MarvelApiBaseUrl + GetComicsPath + Params)
                .buildAndExpand(characterId1, AuthProvider.getApiTs(),
                        authProvider.getApiPublicKey(), authProvider.getApiHash(),
                        0, limit)
                .toUriString();

        uriOk1Page2 = UriComponentsBuilder.fromUriString(MarvelApiBaseUrl + GetComicsPath + Params)
                .buildAndExpand(characterId1, AuthProvider.getApiTs(),
                        authProvider.getApiPublicKey(), authProvider.getApiHash(),
                        100, limit)
                .toUriString();

        uriOk2 = UriComponentsBuilder.fromUriString(MarvelApiBaseUrl + GetComicsPath + Params)
                .buildAndExpand(characterId3, AuthProvider.getApiTs(),
                        authProvider.getApiPublicKey(), authProvider.getApiHash(),
                        offset, limit)
                .toUriString();

        uriBad = UriComponentsBuilder.fromUriString(MarvelApiBaseUrl + GetComicsPath + Params)
                .buildAndExpand(characterId2, AuthProvider.getApiTs(),
                        authProvider.getApiPublicKey(), authProvider.getApiHash(),
                        offset, limit)
                .toUriString();
    }

    @Test
    void testReturnPageByCharacterAndRespondWith409() {
        server.expect(requestTo(uriBad))
                .andExpect(method(HttpMethod.GET))
                .andExpect(queryParam(PAGE_SIZE_PARAM, String.valueOf(limit)))
                .andExpect(queryParam(OFFSET_PARAM, "0"))
                .andRespond(withStatus(HttpStatus.CONFLICT));

        assertThrows(Exception.class,
                () -> marvelApi.GetComicsRecursively(characterId2));
    }

    @Test
    void testReturnPageByCharacterAndRespondWithEmptyBody() {
        String emptyResponseOk = TestUtils.readFile("response_200_empty_data.json");

        server.expect(requestTo(uriOk2))
                .andExpect(method(HttpMethod.GET))
                .andExpect(queryParam(PAGE_SIZE_PARAM, String.valueOf(limit)))
                .andExpect(queryParam(OFFSET_PARAM, "0"))
                .andRespond(withSuccess(emptyResponseOk,
                        MediaType.APPLICATION_JSON));

        List<SingleComicResponse> singleComicResponses = marvelApi.GetComicsRecursively(characterId3);

        assertEquals(0, singleComicResponses.size());
    }

    @Test
    void testReturnPageByCharacterAndRespondWithPages() {
        String responseOkPage1 = TestUtils.readFile("response_200_with_data_page_1.json");
        String responseOkPage2 = TestUtils.readFile("response_200_with_data_page_2.json");

        server.expect(requestTo(uriOk1Page1))
                .andExpect(method(HttpMethod.GET))
                .andExpect(queryParam(PAGE_SIZE_PARAM, String.valueOf(limit)))
                .andExpect(queryParam(OFFSET_PARAM, "0"))
                .andRespond(withSuccess(responseOkPage1,
                        MediaType.APPLICATION_JSON));

        server.expect(requestTo(uriOk1Page2))
                .andExpect(method(HttpMethod.GET))
                .andExpect(queryParam(PAGE_SIZE_PARAM, String.valueOf(limit)))
                .andExpect(queryParam(OFFSET_PARAM, "100"))
                .andRespond(withSuccess(responseOkPage2,
                        MediaType.APPLICATION_JSON));

        List<SingleComicResponse> singleComicResponses = marvelApi.GetComicsRecursively(characterId1);

        assertEquals(200, singleComicResponses.size());
    }

}