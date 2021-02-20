package com.andyadc.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * https://www.cnblogs.com/lenve/p/14142244.html
 */
@RequestMapping("/log")
@RestController
public class LogController {

    private static final Logger logger = LoggerFactory.getLogger(LogController.class);

    @GetMapping
    public String print() {
        for (int i = 0; i < 1000; i++) {
            logger.info("info" + i);
            logger.warn("warn" + i);
            logger.debug("debug" + i);
            logger.error("error" + i);
            logger.trace("trace" + i);
        }
        return "success";
    }
}
