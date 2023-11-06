package com.xuecheng.content.service;

import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.AddCourseDto;
import com.xuecheng.content.model.dto.CourseBaseInfoDto;
import com.xuecheng.content.model.dto.EditCourseDto;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;

/**
 * @author liu
 * @version 1.0
 * @project xuecheng-plus-project
 * @description 课程信息管理接口
 * @date 2023/10/25 18:57:43
 */

public interface CourseBaseInfoService {

  //课程分页查询
  public PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDto courseParamsDto);

  /**
   *
   * @param companyId 机构id
   * @param addCourseDto 课程id
   * @return 课程添加成功的详细信息
   */
  CourseBaseInfoDto createCourseBase(Long companyId, AddCourseDto addCourseDto);

  /**
   * 根据课程的id查询课程的信息
   * @param courseId 课程id
   * @return 课程的详细信息
   */
  CourseBaseInfoDto getCourseBaseInfo(Long courseId);

  /**
   * 修改课程
   * @param editCourseDto 修改课程的信息
   * @param companyId 机构信息
   * @return 课程的详细信息
   */
  CourseBaseInfoDto updateCourseBase(EditCourseDto editCourseDto, Long companyId);

  /**
   * 删除课程及其关联课程
   * @param companyId 机构id
   * @param id 课程id
   */
    void deleteCourseBase(Long companyId, Long id);
}
