package com.xuecheng.base.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liu
 * @version 1.0
 * @project xuecheng-plus-project
 * @description TOOD
 * @date 2023/10/29 13:16:20
 */



@Slf4j
//@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {


//  对项目自定义异常类型进行处理
//  @RequestBody
  @ExceptionHandler(XueChengPlusException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public RestErrorResponse customException(XueChengPlusException e){
//    记录异常日志
    log.error("系统异常{}",e.getErrMessage(),e);


//    解析出异常信息
    String errMessage = e.getErrMessage();
    RestErrorResponse restErrorResponse = new RestErrorResponse(errMessage);
    return restErrorResponse;
  }


  @ExceptionHandler(XCerrcodeException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public RestErrorResponse errcodeException(XCerrcodeException e){
//    记录异常日志
    log.error("系统异常{}",e.getErrMessage(),e);


//    解析出异常信息
    String errMessage = e.getErrMessage();
    String errCode = e.getErrCode();
    RestErrorResponse restErrorResponse = new RestErrorResponse(errCode,errMessage);
    return restErrorResponse;
  }


  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public RestErrorResponse exception(Exception e){
//    记录异常日志
    log.error("系统异常{}",e.getMessage(),e);


//    解析出异常信息
    RestErrorResponse restErrorResponse = new RestErrorResponse(CommonError.UNKOWN_ERROR.getErrMessage());
    return restErrorResponse;
  }

//  MethodArgumentNotValidException

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public RestErrorResponse validException(MethodArgumentNotValidException e){

    BindingResult bindingResult = e.getBindingResult();
//    存放错误信息
    List<String> errors = new ArrayList<>();
    bindingResult.getFieldErrors().stream().forEach(item->{
      errors.add(item.getDefaultMessage());
    });

//    将list的错误信息拼接起来
    String errMessage = StringUtils.join(errors, ",");

//    记录异常日志
    log.error("系统异常{}",e.getMessage(),errMessage);

//    解析出异常信息
    RestErrorResponse restErrorResponse = new RestErrorResponse(errMessage);
    return restErrorResponse;
  }


}
