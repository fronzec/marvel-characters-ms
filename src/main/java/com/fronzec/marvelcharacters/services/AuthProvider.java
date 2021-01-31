package com.fronzec.marvelcharacters.services;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AuthProvider {

    private static final String API_TS = "1";
    private String API_PUBLIC_KEY;
    private String API_HASH;

    public AuthProvider(final @Value("${marvel.api.public-key}") String apiPublicKey,
                        final @Value("${marvel.api.private-key}") String apiPrivateKey) {
        this.API_PUBLIC_KEY = apiPublicKey;
        this.API_HASH = DigestUtils.md5Hex(API_TS + apiPrivateKey + API_PUBLIC_KEY);
    }

    public static String getApiTs() {
        return API_TS;
    }

    public String getAPI_PUBLIC_KEY() {
        return API_PUBLIC_KEY;
    }

    public String getAPI_HASH() {
        return API_HASH;
    }
}
