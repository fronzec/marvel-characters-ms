package com.fronzec.marvelcharacters.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Define scheduled task
 */
@Component
public class ScheduledTasks {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final SyncDataService syncDataService;

    public ScheduledTasks(SyncDataService syncDataService) {
        this.syncDataService = syncDataService;
    }

    /**
     * Every day at 12:00 am we sync remote data
     */
    @Scheduled(cron = "0 0 12 * * *")
    public void scheduleTaskWithCronExpression() {
        logger.info("Cron Task: begin sync Current Time - {}", formatter.format(LocalDateTime.now()));
        syncDataService.syncData();
        logger.info("Cron Task: end sync Current Time - {}", formatter.format(LocalDateTime.now()));
    }
}
