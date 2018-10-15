package com.loikun;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 使用Spring 检索网页数据
 */
@SpringBootApplication
public class BootApplication {

    private static final Logger log = LoggerFactory.getLogger(BootApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }

    /**
     * RestTemplateBuilder 是由Spring注入(Springboot 的自动配置方式)
     * @param builder
     * @return
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    /**
     * 表示一个回调方法:
     * RestTemplate 回调一个
     */
    @Bean
    public CommandLineRunner run(RestTemplate restTemplate){
        return args -> {
            Quote quote =
                    restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
            log.info(quote.toString());
        };
    }
}
