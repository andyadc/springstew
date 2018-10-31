package com.andyadc.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 关闭 Spring 容器
 *
 * @author andy.an
 * @since 2018/10/31
 */
@RestController
public class ExitController {

    private static final Logger logger = LoggerFactory.getLogger(ExitController.class);

    private ApplicationContext context;

    public ExitController(ApplicationContext context) {
        this.context = context;
    }

    @RequestMapping("/exit")
    public Object exit() {
        new Thread(() -> {
            logger.info("Exiting application...");
            int result = SpringApplication.exit(context);
            logger.info("Exited result: {}", result);
        }).start();
        return "success";
    }
}
