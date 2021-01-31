package com.fronzec.marvelcharacters.utiltests;

import com.google.common.base.Charsets;
import org.testcontainers.shaded.com.google.common.io.Resources;

import java.io.IOException;
import java.net.URL;

public class TestUtils {
    public static String readFile(String file) {
        URL url = Resources.getResource(file);
        try {
            return Resources.toString(url, Charsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("ERROR WHEN READING FILE");
        }
    }
}