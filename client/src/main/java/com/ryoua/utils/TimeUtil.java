package com.ryoua.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: RyouA
 * @Date: 2020/5/23 - 23:15
 **/
public class TimeUtil {
    public static String getNowTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年mm月dd日-hh:mm:ss");
        return dateFormat.format(new Date());
    }
}