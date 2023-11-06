package com.xuecheng.content.api;

import com.xuecheng.content.model.dto.CourseTeacherDto;
import com.xuecheng.content.model.po.CourseTeacher;
import com.xuecheng.content.service.CourseTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liu
 * @version 1.0
 * @project xuecheng-plus-project
 * @description TOOD
 * @date 2023/11/6 19:00:59
 */

@Api(value = "教师编辑接口",tags = "教师编辑接口")
@RestController
public class CourseTeacherController {

    @Autowired
    private CourseTeacherService courseTeacherService;

    //  查询教师
    @ApiOperation("课程教师查询")
    @GetMapping("/courseTeacher/list/{courseId}")
    public List<CourseTeacher>  listTeacherforcourseid(@PathVariable Long courseId){
        List<CourseTeacher> courseTeachers = courseTeacherService.listTeacher(courseId);
        return courseTeachers;
    }

    @ApiOperation("教师添加请求")
    @PostMapping("/courseTeacher")
    public CourseTeacher addTeacher(@RequestBody CourseTeacherDto courseTeacherDto){
        Long companyId = 1232141425L;
        CourseTeacher courseTeacher = courseTeacherService.addCourseTeacher(companyId, courseTeacherDto);
        return courseTeacher;
    }

    @ApiOperation("教师修改请求")
    @PutMapping("/courseTeacher")
    public CourseTeacher updateTeacher(@RequestBody CourseTeacher courseTeacher){
        Long companyId = 1232141425L;
        CourseTeacher updateCourseTeacher = courseTeacherService.updateCourseTeacher(companyId, courseTeacher);
        return updateCourseTeacher;

    }

    @ApiOperation("教师删除请求")
    @DeleteMapping("/courseTeacher/course/{courseId}/{id}")
    public void deleteTeacher(@PathVariable Long courseId,@PathVariable Long id){
        Long companyId = 1232141425L;
        courseTeacherService.deleteCourseTeacher(companyId,courseId,id);

    }



}
