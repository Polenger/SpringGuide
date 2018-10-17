package com.loikun;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;

/**
 * 消息的接收者： 主要是用于接收和响应消息
 * 定义一个接收消息的方法
 *
 */
public class Receiver {

    private static final Logger log = LoggerFactory.getLogger(Receiver.class);

    // 用来表示接收消息的次数
    private CountDownLatch latch;

    @Autowired
    public Receiver(CountDownLatch latch){
        this.latch = latch;
    }

    /**
     * 定义了一个接收消息的方法，用于对message(消息)的监听
     * @param message
     */
    public void receiveMessage(String message){
        log.info("Received <" + message + ">");
        latch.countDown();
    }
}
