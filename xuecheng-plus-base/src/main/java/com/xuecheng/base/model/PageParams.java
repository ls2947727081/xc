package com.xuecheng.base.model;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.ToString;

/**
 * @author liu
 * @version 1.0
 * @project xuecheng-plus-project
 * @description TOOD
 * @date 2023/10/24 20:22:31
 */

@Data
@ToString
public class PageParams {

  //当前页码
  @ApiModelProperty("页码")
  private Long pageNo = 1L;

  //每页记录数默认值
  @ApiModelProperty("每页记录数")
  private Long pageSize =10L;

  public PageParams(){

  }

  public PageParams(long pageNo,long pageSize){
    this.pageNo = pageNo;
    this.pageSize = pageSize;
  }



}
