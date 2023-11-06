package com.xuecheng.base.exception;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liu
 * @version 1.0
 * @project xuecheng-plus-project
 * @description TOOD
 * @date 2023/10/29 12:59:54
 */
@Data
public class RestErrorResponse implements Serializable {

  private String errCode;
  private String errMessage;

  public RestErrorResponse(String errMessage){
    this.errMessage= errMessage;
  }

  public RestErrorResponse(String errCode,String errMessage){
    this.errMessage= errMessage;
    this.errCode = errCode;
  }

}
