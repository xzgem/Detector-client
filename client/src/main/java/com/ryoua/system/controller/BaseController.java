package com.ryoua.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/12
 **/
public class BaseController {
    @Value("${easyEye.main.host}")
    protected String host;

    @Value("${easyEye.main.port}")
    protected String port;

    @Value("${easyEye.auto-register}")
    protected boolean autoRegister;

    @Autowired
    protected RestTemplate restTemplate;
}
