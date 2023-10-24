package com.xuecheng.content.model.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @author liu
 * @version 1.0
 * @project xuecheng-plus-project
 * @description TOOD
 * @date 2023/10/24 20:25:46
 */

@Data
@ToString
public class QueryCourseParamsDto {

  //审核状态
  private String auditStatus;
  //课程名称
  private String courseName;
  //发布状态
  private String publishStatus;

}
