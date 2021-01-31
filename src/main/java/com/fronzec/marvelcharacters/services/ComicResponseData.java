package com.fronzec.marvelcharacters.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ComicResponseData {

    private int offset;
    private int limit;
    private int total;
    private int count;
    private List<SingleComicResponse> results;

    public ComicResponseData() {
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<SingleComicResponse> getResults() {
        return results;
    }

    public void setResults(List<SingleComicResponse> results) {
        this.results = results;
    }
}
