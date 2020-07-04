package com.ryoua;

import com.ryoua.task.SystemInfoTask;
import com.ryoua.task.config.SystemInfoTaskConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: RyouA
 * @Date: 2020/5/12 - 1:18 下午
 **/
@SpringBootApplication
@Slf4j
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("系统信息发送定时任务开启");
        SystemInfoTaskConfig systemInfoTask = new SystemInfoTaskConfig();
        systemInfoTask.SystemQuartzTrigger();
    }

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        return new RestTemplate(factory);
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(5000);//ms
        factory.setConnectTimeout(15000);//ms
        return factory;
    }
}
