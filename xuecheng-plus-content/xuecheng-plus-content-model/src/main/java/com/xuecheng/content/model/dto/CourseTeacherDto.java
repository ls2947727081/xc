package com.xuecheng.content.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author liu
 * @version 1.0
 * @project xuecheng-plus-project
 * @description 接收添加教师的请求body
 * @date 2023/11/6 19:38:45
 */

@Data
@ApiModel(value = "courseTeacherDto",description = "教师基本信息")
public class CourseTeacherDto {
    Long courseId;
    String teacherName;
    String position;
    String introduction;
}
