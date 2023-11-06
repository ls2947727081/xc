package com.xuecheng.content.service;

import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.CourseTeacher;

import java.util.List;

/**
 * @author liu
 * @version 1.0
 * @project xuecheng-plus-project
 * @description 课程计划管理相关接口
 * @date 2023/10/29 21:45:36
 */

public interface TeachplanService {
  /**
   *
   * @param courseId
   * @return
   */
  List<TeachplanDto> findTeachplanTree(long courseId);

  /**
   * 新增，修改，保存课程计划方法
   * @param saveTeachplanDto
   */
  public void saveTeachplan(SaveTeachplanDto saveTeachplanDto);

  /**
   * 删除课程方法
   * @param id 课程的id
   */
  public void deleTeachplan(Long id);

  /**
   * 下移课程的方法
   * @param id 课程id
   */
  public void movedown(Long id);

  /**
   * 上移课程的方法
   * @param id 课程id
   */
  public void moveup(Long id);


}
