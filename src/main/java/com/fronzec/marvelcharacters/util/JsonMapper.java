package com.fronzec.marvelcharacters.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JsonMapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonMapper.class);
    public static final ObjectMapper MAPPER = new ObjectMapper();

    private JsonMapper() {
    }

    public static String writeValueAsString(Object value) {
        try {
            return MAPPER.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error serializing object: {}. Value: {}",
                    e.getMessage(), value, e);
            return "{}";
        }
    }

    public static <T> T toObject(final String content, final Class<T> classType) {
        try {
            return MAPPER.readValue(content, classType);
        } catch (IOException e) {
            LOGGER.error("Error during content deserialization -> {}", content, e);
            return null;
        }
    }

    public static <T> T toObject(final JsonNode node, final Class<T> classType) {
        try {
            return MAPPER.treeToValue(node, classType);
        } catch (IOException e) {
            LOGGER.error("Error during content deserialization -> {}", node, e);
            return null;
        }
    }
}
