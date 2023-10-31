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
public class XueChengPlusException extends RuntimeException{
    private String errMessage;

    public XueChengPlusException() {
    }

    public XueChengPlusException(String message) {
        super(message);
        this.errMessage=message;
    }

    public static void cast(String errMessage){
        throw new XueChengPlusException(errMessage);
    }

    public static void cast(CommonError errMessage){
        throw new XueChengPlusException(errMessage.getErrMessage());
    }



}
