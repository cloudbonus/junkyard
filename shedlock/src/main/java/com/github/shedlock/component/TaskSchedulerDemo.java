package com.github.shedlock.component;

import net.javacrumbs.shedlock.core.LockAssert;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

/**
 * @author Raman Haurylau
 */
@Component
public class TaskSchedulerDemo {

    private static final Logger log = LoggerFactory.getLogger(TaskSchedulerDemo.class);

    @Value("${app.node}")
    private String appNode;

    @Scheduled(cron = "0/10 * * * * *")
    @SchedulerLock(name = "printTheDateTask", lockAtMostFor = "30s", lockAtLeastFor = "30s")
    public void printTheDateTask() {
        LockAssert.assertLocked();
        log.info("Running task at [{}] from node [{}]", ZonedDateTime.now(), appNode);
    }
}
