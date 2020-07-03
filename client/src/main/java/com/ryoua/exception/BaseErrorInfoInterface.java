package com.ryoua.exception;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/3
 **/
public interface BaseErrorInfoInterface {
    /** 错误码*/
    String getResultCode();

    /** 错误描述*/
    String getResultMsg();
}
