package com.fronzec.marvelcharacters.domain.marvelresponses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ComicsResponseRoot {

    private int code;
    private ComicResponseData data;

    public ComicsResponseRoot() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ComicResponseData getData() {
        return data;
    }

    public void setData(ComicResponseData data) {
        this.data = data;
    }
}
