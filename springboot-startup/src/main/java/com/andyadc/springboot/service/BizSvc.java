package com.andyadc.springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

/**
 * andy.an
 */
@Service
public class BizSvc {

    private static final Logger logger = LoggerFactory.getLogger(BizSvc.class);

    @Autowired
    private AsyncSvc asyncSvc;

    public void bizNotify() {
        Instant now = Instant.now();
        asyncSvc.notifyTo();
        logger.info("Timing: " + Duration.between(now, Instant.now()).toMillis());
    }
}
