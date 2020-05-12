package com.ryoua.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: RyouA
 * @Date: 2020/5/12 - 1:51 下午
 **/
@RestController
public class SystemController {
    @RequestMapping(value = "/SystemInfo", method = RequestMethod.GET)
    public String SystemInfo() {

        return "";
    }
}
