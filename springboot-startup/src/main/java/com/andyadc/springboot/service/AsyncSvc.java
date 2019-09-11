package com.andyadc.springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * andy.an
 */
@Service
public class AsyncSvc implements Interface {

    private static final Logger logger = LoggerFactory.getLogger(AsyncSvc.class);

    @Async("notifyTaskExecutor")
    public void notifyTo() {
        logger.info("notify to ...");
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            logger.error("sleep error", e);
        }
        logger.info("notify successed");
    }
}
