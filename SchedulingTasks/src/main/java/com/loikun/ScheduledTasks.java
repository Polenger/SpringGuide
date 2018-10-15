package com.loikun;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 定时任务例子
 */
@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    // 定义每5秒执行任务，单位为毫秒
    @Scheduled(fixedRate = 5000)
    //@Scheduled(cron = "")
    public void reportCurrentTime(){
        log.info("The time is now {}", dateFormat.format(new Date()));
    }
}
