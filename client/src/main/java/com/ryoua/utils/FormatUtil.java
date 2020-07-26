package com.ryoua.utils;

import java.text.DecimalFormat;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/3
 **/
public class FormatUtil {
    public static String DoubleSaveOnePoint(Double T) {
        DecimalFormat df = new DecimalFormat("#.#");
        return df.format(T);
    }
}
