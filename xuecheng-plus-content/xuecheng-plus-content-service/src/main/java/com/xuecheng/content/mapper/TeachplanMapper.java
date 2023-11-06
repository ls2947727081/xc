package com.xuecheng.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.Teachplan;

import java.util.List;

/**
 * <p>
 * 课程计划 Mapper 接口
 * </p>
 *
 * @author itcast
 */
public interface TeachplanMapper extends BaseMapper<Teachplan> {
//    课程查询

    public List<TeachplanDto> selectTreeNodes(Long courseId);

    public List<TeachplanDto> checkTreeNodes(Long parentid);

    public List<TeachplanDto> selectByCourseId(Long courseId);

}
