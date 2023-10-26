package com.xuecheng.content.model.dto;

import com.xuecheng.content.model.po.CourseCategory;
import lombok.Data;

import java.util.List;

/**
 * @author liu
 * @version 1.0
 * @project xuecheng-plus-project
 * @description TOOD
 * @date 2023/10/26 18:00:16
 */

@Data
public class CourseCategoryTreeDto extends CourseCategory implements java.io.Serializable{
    List<CourseCategoryTreeDto> childrenTreeNodes;
}
