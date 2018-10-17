package com.loikun;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.concurrent.CountDownLatch;

/**
 * 1、Connection factory
 * 2、message listener container
 * 3、redis template
 *
 * 使用Redis template 发送消息 并注册一个消息监听器，用于连接Redis server
 *
 * 示例采用SpringBoot 默认的RedisConnectionFactory中JedisConnectionFactory(需要导入jedis依赖)
 */
@SpringBootApplication
public class BootApplication {

    private static final Logger log = LoggerFactory.getLogger(BootApplication.class);

    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory factory,
                                                   MessageListenerAdapter adapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(factory);
        container.addMessageListener(adapter, new PatternTopic("chat"));
        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    /**
     *  消息容器，包装信息
     */
    @Bean
    public Receiver receiver(CountDownLatch latch) {
        return new Receiver(latch);
    }

    @Bean
    public CountDownLatch latch() {
        return new CountDownLatch(1);
    }

    @Bean
    public StringRedisTemplate redisTemplate(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = SpringApplication.run(BootApplication.class, args);
        // 采用更为直接的String 类型的redis 模板
        StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
        CountDownLatch latch = ctx.getBean(CountDownLatch.class);
        log.info("sending message...");
        template.convertAndSend("chat", "Hello from Redis!");
        latch.await();
        System.exit(0);
    }
}
