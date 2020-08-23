package com.ryoua.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/23
 **/
@RestController
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "123";
    }
}
