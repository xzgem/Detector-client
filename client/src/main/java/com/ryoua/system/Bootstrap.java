package com.ryoua.system;

import com.ryoua.system.config.Constants;
import com.ryoua.system.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/23
 **/
@Component
public class Bootstrap implements ApplicationRunner {
    @Autowired
    private ConfigService configService;

    @Override
    public void run(ApplicationArguments args) {
        try {
            Constants.mid = configService.getConfig().getMid();
            System.out.println(Constants.mid);
        } catch (Exception e) {
            configService.createConfig(Constants.mid);
        }
    }
}
