package com.xuecheng.content.service;

import com.xuecheng.content.model.dto.CourseTeacherDto;
import com.xuecheng.content.model.po.CourseTeacher;

import java.util.List;

/**
 * @author liu
 * @version 1.0
 * @project xuecheng-plus-project
 * @description TOOD
 * @date 2023/11/6 19:03:12
 */
public interface CourseTeacherService {

  /**
   * 查询课程教师
   * @param courseId 课程id
   * @return 课程教师
   */
  List<CourseTeacher> listTeacher(Long courseId);

  CourseTeacher addCourseTeacher(Long companyId, CourseTeacherDto courseTeacherDto);

  CourseTeacher updateCourseTeacher(Long companyId, CourseTeacher courseTeacher);

  void deleteCourseTeacher(Long companyId, Long courseId, Long id);
}
