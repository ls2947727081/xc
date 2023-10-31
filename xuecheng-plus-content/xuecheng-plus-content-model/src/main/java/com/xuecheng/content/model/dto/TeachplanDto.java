package com.xuecheng.content.model.dto;

import com.xuecheng.content.model.po.Teachplan;
import com.xuecheng.content.model.po.TeachplanMedia;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author liu
 * @version 1.0
 * @project xuecheng-plus-project
 * @description 课程计划信息dto
 * @date 2023/10/29 20:28:32
 */

@Data
@ToString
public class TeachplanDto extends Teachplan {
//  与媒咨管理信息
  private TeachplanMedia teachplanMedia;
//  小章节list
  private List<TeachplanDto> teachPlanTreeNodes;

}
