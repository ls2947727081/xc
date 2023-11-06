package com.xuecheng.base.exception;

import lombok.Data;

/**
 * @author liu
 * @version 1.0
 * @project xuecheng-plus-project
 * @description 本项目自定义异常类型
 * @date 2023/10/29 13:01:22
 */

@Data
public class XCerrcodeException extends RuntimeException{
    private String errMessage;
    private String errCode;

    public XCerrcodeException() {
    }

    public XCerrcodeException(String errCode, String message) {
        this.errMessage = message;
        this.errCode = errCode;
    }

    public static void cast(String errCode, String errMessage){
        throw new XCerrcodeException(errCode, errMessage);
    }

    @Override
    public String toString() {
        return "{\"errCode\":\"" + errCode + "\",\"errMessage\":\"" + errMessage + "\"}";
    }



}
