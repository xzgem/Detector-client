package com.ryoua.system.utils;

import java.util.UUID;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/30
 **/
public class StringUtil {
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
