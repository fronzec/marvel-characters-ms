package com.fronzec.marvelcharacters.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AuthProvider.class)
class AuthProviderTest {

    @Autowired
    AuthProvider authProvider;

    @Test
    void whenLoadAllOk() {
        assertEquals("1", AuthProvider.getApiTs());
        assertEquals("99a5a5d283e67865bb97de0e5015b6ea", authProvider.getApiHash());
        assertEquals("aaaa", authProvider.getApiPublicKey());
    }
}