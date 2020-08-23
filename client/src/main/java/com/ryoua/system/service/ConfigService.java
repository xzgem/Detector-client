package com.ryoua.system.service;

import com.ryoua.system.model.Config;
import com.ryoua.system.repository.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/23
 **/
@Service
public class ConfigService {
    @Autowired
    private ConfigRepository configRepository;

    public void createConfig(String mid) {
        Config config = new Config();
        config.setMid(mid);
        config.setId(1);
        configRepository.save(config);
    }

    public Config getConfig() {
        return configRepository.findById(1).get();
    }
}
