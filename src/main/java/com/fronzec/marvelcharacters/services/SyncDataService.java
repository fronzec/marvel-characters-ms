package com.fronzec.marvelcharacters.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SyncDataService {

    private static final Logger logger = LoggerFactory.getLogger(SyncDataService.class);

    public void syncData() {
        logger.info("[SYNC-PROCESS]:: Syncing data");
        // TODO: 30/01/2021 fetch characters to sync, then do main process, store
    }
}
