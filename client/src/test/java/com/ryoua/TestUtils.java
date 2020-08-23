package com.ryoua;

import java.lang.reflect.Field;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/23
 **/
public class TestUtils {
    public static Boolean isAllFieldNull(Object obj) throws Exception{
        Class stuCla = (Class) obj.getClass();
        Field[] fs = stuCla.getDeclaredFields();
        boolean flag = true;
        for (Field f : fs) {
            f.setAccessible(true);
            Object val = f.get(obj);
            if(val==null) {
                flag = false;
                break;
            }else {
                if(val instanceof String) {
                    if ("".equals(val)) {
                        flag = false;
                        break;
                    }
                }
            }
        }
        return flag;
    }
}
