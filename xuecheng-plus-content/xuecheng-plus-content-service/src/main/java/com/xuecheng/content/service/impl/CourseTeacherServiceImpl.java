package com.xuecheng.content.service.impl;

import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.mapper.CourseTeacherMapper;
import com.xuecheng.content.mapper.TeachplanMapper;
import com.xuecheng.content.model.dto.CourseTeacherDto;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.model.po.CourseTeacher;
import com.xuecheng.content.service.CourseTeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liu
 * @version 1.0
 * @project xuecheng-plus-project
 * @description TOOD
 * @date 2023/11/6 19:07:20
 */
@Slf4j
@Service
public class CourseTeacherServiceImpl implements CourseTeacherService {

    @Autowired
    CourseTeacherMapper courseTeacherMapper;

    @Autowired
    CourseBaseMapper courseBaseMapper;

    @Override
    public List<CourseTeacher> listTeacher(Long courseId) {
        List<CourseTeacher> courseTeachers = courseTeacherMapper.selectByCourseId(courseId);
        return courseTeachers;
    }

    @Override
    public CourseTeacher addCourseTeacher(Long companyId, CourseTeacherDto courseTeacherDto) {
        //判断课程是否属于机构
        CourseBase courseBase = courseBaseMapper.selectById(courseTeacherDto.getCourseId());
        if (courseBase==null){
            return null;
        }
        if (courseBase.getCompanyId().equals(companyId)) {
            CourseTeacher courseTeacher = new CourseTeacher();
            BeanUtils.copyProperties(courseTeacherDto,courseTeacher);
            courseTeacherMapper.insert(courseTeacher);
            return courseTeacher;
        }
        else {
            XueChengPlusException.cast("该课程不属于你的机构，请确定自己机构的课程进行添加");
            return null;
        }

    }

    @Override
    public CourseTeacher updateCourseTeacher(Long companyId, CourseTeacher courseTeacher) {
        CourseBase courseBase = courseBaseMapper.selectById(courseTeacher.getCourseId());
        if (courseBase==null){
            return null;
        }
        if (courseBase.getCompanyId().equals(companyId)) {
            courseTeacherMapper.updateById(courseTeacher);
            return courseTeacher;

        }else {
            XueChengPlusException.cast("该课程不属于你的机构，请确定自己机构的课程进行修改");
            return null;
        }
    }

    @Override
    public void deleteCourseTeacher(Long companyId, Long courseId, Long id) {
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        if (courseBase==null){
            return;
        }
        if (courseBase.getCompanyId().equals(companyId)){
            CourseTeacher courseTeacher = courseTeacherMapper.selectById(id);
            if (courseTeacher!=null){
                courseTeacherMapper.deleteById(id);
            }
        }
        else {
            XueChengPlusException.cast("该课程不属于你的机构，请确定自己机构的课程进行修改");
        }

    }


}
