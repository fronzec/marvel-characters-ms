package com.fronzec.marvelcharacters.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SyncDataService {

    private static final Logger logger = LoggerFactory.getLogger(SyncDataService.class);
    private MarvelApi marvelApi;

    public SyncDataService(final MarvelApi marvelApi) {
        this.marvelApi = marvelApi;
    }


    public void syncData() {
        logger.info("[SYNC-PROCESS]:: Syncing data");
        final int captainamerica = 1009220;
        final int ironman = 1009368;

        List<SingleComicResponse> result = marvelApi.GetComicsRecursively(captainamerica);
        // TODO: 30/01/2021 process and store them
    }
}
