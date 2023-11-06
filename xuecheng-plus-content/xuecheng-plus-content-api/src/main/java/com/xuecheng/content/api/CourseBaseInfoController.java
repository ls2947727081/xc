package com.xuecheng.content.api;

import com.xuecheng.base.exception.ValidationGroups;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.AddCourseDto;
import com.xuecheng.content.model.dto.CourseBaseInfoDto;
import com.xuecheng.content.model.dto.EditCourseDto;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.service.CourseBaseInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liu
 * @version 1.0
 * @project xuecheng-plus-project
 * @description 课程查询接口
 * @date 2023/10/24 20:29:24
 */
@Api(value = "课程信息编辑接口",tags = "课程信息编辑接口")
@RestController
public class CourseBaseInfoController {

  @Autowired
  CourseBaseInfoService courseBaseInfoService;

  @ApiOperation("课程查询接口")
  @PostMapping("/course/list")
  public PageResult<CourseBase> list(PageParams pageParams, @RequestBody(required = false) QueryCourseParamsDto queryCourseParams){

    PageResult<CourseBase> pageResult = courseBaseInfoService.queryCourseBaseList(pageParams, queryCourseParams);
    return pageResult;

  }
  @ApiOperation("新增课程接口")
  @PostMapping("/course")
  public CourseBaseInfoDto createCourseBase(@RequestBody @Validated(ValidationGroups.Insert.class) AddCourseDto addCourseDto){

    //用户登录获取到用户所属机构id
    Long companyId = 1232141425L;
//    int i = 1/0;

    CourseBaseInfoDto courseBase = courseBaseInfoService.createCourseBase(companyId, addCourseDto);

    return courseBase;
  }

  @ApiOperation("根据课程id查询")
  @GetMapping("/course/{courseId}")
  public CourseBaseInfoDto getCourseBaseInfoById(@PathVariable Long courseId){

    CourseBaseInfoDto courseBase = courseBaseInfoService.getCourseBaseInfo(courseId);

    return courseBase;
  }

  @ApiOperation("课程修改")
  @PutMapping("/course")
  public CourseBaseInfoDto modifyCourseBase(@RequestBody @Validated(ValidationGroups.Update.class) EditCourseDto editCourseDto){

    Long companyId = 1232141425L;
    CourseBaseInfoDto courseBaseInfoDto = courseBaseInfoService.updateCourseBase(editCourseDto, companyId);

    return courseBaseInfoDto;
  }

  @ApiOperation("课程删除")
  @DeleteMapping("/course/{id}")
  public void deleteCourse(@PathVariable Long id){
    Long companyId = 1232141425L;
    courseBaseInfoService.deleteCourseBase(companyId,id);
  }



}
