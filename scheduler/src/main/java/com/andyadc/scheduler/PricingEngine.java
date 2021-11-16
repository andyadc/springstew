package com.andyadc.scheduler;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PricingEngine {

    private static final Logger logger = LoggerFactory.getLogger(PricingEngine.class);

    /**
     * The new job will always wait for the previous job to finish.
     */
//    @Scheduled(fixedDelay = 2000L)
    public void computePrice() throws InterruptedException {
        logger.info("computing price at " + LocalDateTime.now());

        // added sleep to simulate method
        // which takes longer to execute.
        Thread.sleep(4000L);
    }

    //    @Scheduled(fixedDelayString = "${interval}")
    public void computePrice2() throws InterruptedException {
        logger.info("computing price at " + LocalDateTime.now());

        // added sleep to simulate method
        // which takes longer to execute.
        Thread.sleep(4000L);
    }

    /**
     * <code>
     * ┌───────────── second (0-59)
     * │ ┌───────────── minute (0 - 59)
     * │ │ ┌───────────── hour (0 - 23)
     * │ │ │ ┌───────────── day of the month (1 - 31)
     * │ │ │ │ ┌───────────── month (1 - 12) (or JAN-DEC)
     * │ │ │ │ │ ┌───────────── day of the week (0 - 7)
     * │ │ │ │ │ │          (or MON-SUN -- 0 or 7 is Sunday)
     * │ │ │ │ │ │
     * * * * * * *
     * </code>
     */
//    @Scheduled(cron = "${interval-in-cron}")
    public void computePrice3() throws InterruptedException {
        logger.info("computing price at " + LocalDateTime.now());

    }

    /**
     * <p>
     *
     * @hourly,
     * @yearly,
     * @monthly,
     * @weekly,
     * @daily </p>
     */
//    @Scheduled(cron = "@hourly")
    public void computePrice4() throws InterruptedException {
        logger.info("computing price at " + LocalDateTime.now());
    }

    @SchedulerLock(name = "myscheduledTask")
    @Scheduled(initialDelay = 2000L, fixedRate = 3000L)
    public void computePrice5() throws InterruptedException {
        logger.info("computing price at " + LocalDateTime.now());
        Thread.sleep(1000);
    }

    //    @Async
//    @Scheduled(initialDelay = 2000L, fixedRate = 3000L)
    public void refreshPricingParameters() {
        logger.info("refresh computing price at " + LocalDateTime.now());
    }

}
