package com.xuecheng.content.service;

import com.xuecheng.content.model.dto.CourseCategoryTreeDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liu
 * @version 1.0
 * @project xuecheng-plus-project
 * @description TOOD
 * @date 2023/10/26 19:19:02
 */


public interface CourseCategoryService {
  public List<CourseCategoryTreeDto> queryTreeNodes(String id);
}
