package com.xuecheng.content.api;

import com.xuecheng.base.exception.RestErrorResponse;
import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.CourseTeacher;
import com.xuecheng.content.service.TeachplanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liu
 * @version 1.0
 * @project xuecheng-plus-project
 * @description 课程计划管理相关接口
 * @date 2023/10/29 20:33:09
 */

@Api(value = "课程计划编辑接口",tags = "课程计划编辑接口")
@RestController
public class TeachplanController {
  @Autowired
  TeachplanService teachplanService;

//  查询课程计划
  @ApiOperation("查询课程计划树形结构")
  @ApiImplicitParam(value = "courseId",name = "课程Id",required = true,dataType = "Long",paramType = "path")
  @GetMapping("/teachplan/{courseId}/tree-nodes")
  public List<TeachplanDto> getTreeNodes(@PathVariable Long courseId){
    List<TeachplanDto> teachplanTree = teachplanService.findTeachplanTree(courseId);
    return teachplanTree;
  }


  @ApiOperation("课程计划创建或修改")
  @PostMapping("/teachplan")
  public void saveTeachplan( @RequestBody SaveTeachplanDto teachplan){
      //开始执行service
      teachplanService.saveTeachplan(teachplan);
  }

  @ApiOperation("课程计划删除")
  @DeleteMapping("/teachplan/{id}")
  public void deleteTeachplan(@PathVariable Long id){
//      执行delete
    teachplanService.deleTeachplan(id);
  }


  @ApiOperation("课程计划排序")
  @PostMapping("/teachplan/movedown/{id}")
  public void movedownTeachplan(@PathVariable Long id){
//      执行movedown
    teachplanService.movedown(id);
  }

  @ApiOperation("课程计划排序")
  @PostMapping("/teachplan/moveup/{id}")
  public void moveupTeachplan(@PathVariable Long id){
//      执行movedown
    teachplanService.moveup(id);
  }




}
