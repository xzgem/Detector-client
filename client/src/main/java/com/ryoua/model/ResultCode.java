package com.ryoua.model;

public enum ResultCode {

    /* 成功状态码 */
    SUCCESS(1,"操作成功！"),

    /* 错误状态码 */
    FAIL(0,"操作失败！"),

    /* 参数错误：10001-19999 */
    PARAM_IS_INVALID(10001, "参数无效"),

    /* 用户错误：20001-29999*/
    USER_NOT_LOGGED_IN(20001, "用户未登录，请先登录"),

    /* 业务错误：30001-39999 */

    /* 系统错误：40001-49999 */
    SYSTEM_INNER_ERROR(40001, "系统繁忙，请稍后重试"),

    /* 接口错误：60001-69999 */

    /* 权限错误：70001-79999 */
    PERMISSION_TOKEN_EXPIRED(70004, "token已过期"),
    PERMISSION_TOKEN_INVALID(70006, "无效token"),
    PERMISSION_SIGNATURE_ERROR(70007, "签名失败");


    int code;
    String message;

    ResultCode(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

