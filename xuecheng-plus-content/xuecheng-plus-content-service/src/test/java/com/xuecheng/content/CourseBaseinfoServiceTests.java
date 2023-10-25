package com.xuecheng.content;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.service.CourseBaseInfoService;
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
public class CourseBaseinfoServiceTests {

    @Autowired
    CourseBaseInfoService courseBaseInfoService;

    @Test
    public void testCourseBaseMapper(){
        //拼装查询条件
        QueryCourseParamsDto courseParamsDto = new QueryCourseParamsDto();
        //课程名称查询条件
        courseParamsDto.setCourseName("java");
        courseParamsDto.setAuditStatus("202004");

        //        分页参数对象
        PageParams pageParams = new PageParams();
        pageParams.setPageNo(2L);
        pageParams.setPageSize(2L);


        PageResult<CourseBase> courseBasePageResult = courseBaseInfoService.queryCourseBaseList(pageParams, courseParamsDto);
        System.out.println(courseBasePageResult);
    }
}
