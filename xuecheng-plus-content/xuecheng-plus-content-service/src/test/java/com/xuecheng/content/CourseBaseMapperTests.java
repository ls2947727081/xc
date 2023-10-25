package com.xuecheng.content;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author liu
 * @version 1.0
 * @project xuecheng-plus-project
 * @description TOOD
 * @date 2023/10/25 09:48:35
 */

@SpringBootTest
public class CourseBaseMapperTests {

    @Autowired(required = false)
    CourseBaseMapper courseBaseMapper;

    @Test
    public void testCourseBaseMapper(){
        CourseBase courseBase = courseBaseMapper.selectById(18L);
        Assertions.assertNotNull(courseBase);

        //拼装查询条件
        QueryCourseParamsDto courseParamsDto = new QueryCourseParamsDto();
        //课程名称查询条件
        courseParamsDto.setCourseName("java");

        //封装查询条件
        LambdaQueryWrapper<CourseBase> queryWrapper = new LambdaQueryWrapper<>();
        //根据名称模糊查询,在sql中拼接course_base.name like '%值%'
        queryWrapper.like(StringUtils.isNotEmpty(courseParamsDto.getCourseName()),CourseBase::getName,courseParamsDto.getCourseName());
        //根据审核状态查询,在sql中拼接course_base.audit_status = ?
        queryWrapper.eq(StringUtils.isNotEmpty(courseParamsDto.getAuditStatus()),CourseBase::getAuditStatus,courseParamsDto.getAuditStatus());
        //todo:按课程发布状态查询（publishStatus）
//        分页参数对象
        PageParams pageParams = new PageParams();
        pageParams.setPageNo(1L);
        pageParams.setPageSize(2L);

//        创建分页参数
        Page<CourseBase> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());

        //开始进行分页查询
        Page<CourseBase> basePage = courseBaseMapper.selectPage(page, queryWrapper);

        List<CourseBase> items = basePage.getRecords();
        long total = basePage.getTotal();
//      List<T> items, long counts, long page, long pageSize
        PageResult<CourseBase> pageResult = new PageResult<CourseBase>(items,total,pageParams.getPageNo(),pageParams.getPageSize());
        System.out.println(pageResult);
    }
}
