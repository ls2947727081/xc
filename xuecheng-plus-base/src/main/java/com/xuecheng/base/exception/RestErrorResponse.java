package com.xuecheng.base.exception;

import java.io.Serializable;

/**
 * @author liu
 * @version 1.0
 * @project xuecheng-plus-project
 * @description TOOD
 * @date 2023/10/29 12:59:54
 */

public class RestErrorResponse implements Serializable {

  private String errMessage;

  public RestErrorResponse(String errMessage){
    this.errMessage= errMessage;
  }

  public String getErrMessage() {
    return errMessage;
  }

  public void setErrMessage(String errMessage) {
    this.errMessage = errMessage;
  }
}
