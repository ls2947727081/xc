package com.xuecheng.content.api;

import com.xuecheng.content.model.dto.CourseCategoryTreeDto;
import com.xuecheng.content.service.CourseCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author liu
 * @version 1.0
 * @project xuecheng-plus-project
 * @description 课程分类管理
 * @date 2023/10/26 18:04:49
 */
@Api(value = "课程分类编辑接口",tags = "课程分类编辑接口")
@RestController
public class CourseCategoryController {

  @Autowired
  CourseCategoryService courseCategoryService;

  @ApiOperation("课程分类接口")
  @GetMapping("/course-category/tree-nodes")
  public List<CourseCategoryTreeDto> queryTreeNodes(){

    return courseCategoryService.queryTreeNodes("1");
  }


}
