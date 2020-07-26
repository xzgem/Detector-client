package com.ryoua.controller;

import com.ryoua.service.LoadInfoService;
import com.ryoua.service.MachineInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/26
 **/
public class BaseController {

    @Value("${easyEye.name:null}")
    protected String oid;

    @Value("${easyEye.main.host}")
    protected String host;

    @Value("${easyEye.main.port}")
    protected String port;

    @Value("${easyEye.auto-register}")
    protected boolean autoRegister;

    @Autowired
    protected RestTemplate restTemplate;

    @Autowired
    protected LoadInfoService loadInfoService;

    @Autowired
    protected MachineInfoService machineInfoService;
}
