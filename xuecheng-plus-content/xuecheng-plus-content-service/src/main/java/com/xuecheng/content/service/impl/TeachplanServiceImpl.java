package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xuecheng.base.exception.RestErrorResponse;
import com.xuecheng.base.exception.XCerrcodeException;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.mapper.TeachplanMapper;
import com.xuecheng.content.mapper.TeachplanMediaMapper;
import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.CourseTeacher;
import com.xuecheng.content.model.po.Teachplan;
import com.xuecheng.content.model.po.TeachplanMedia;
import com.xuecheng.content.service.TeachplanService;
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
 * @date 2023/10/29 21:46:44
 */
@Slf4j
@Service
public class TeachplanServiceImpl implements TeachplanService {

    @Autowired
    private TeachplanMapper teachplanMapper;

    @Autowired
    TeachplanMediaMapper teachplanMediaMapper;

    @Override
    public List<TeachplanDto> findTeachplanTree(long courseId) {
        List<TeachplanDto> teachplanDtos = teachplanMapper.selectTreeNodes(courseId);
        return teachplanDtos;
    }

    private int getTeachplanCount(long courseId,long parentId){
        LambdaQueryWrapper<Teachplan> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper = queryWrapper.eq(Teachplan::getCourseId, courseId).eq(Teachplan::getParentid, parentId);
        Integer count = teachplanMapper.selectCount(queryWrapper);
        return count+1;
    }

    @Override
    public void saveTeachplan(SaveTeachplanDto saveTeachplanDto) {
        //通过课程计划的新增来判断是新增还是修改
        Long teacherplanId = saveTeachplanDto.getId();
        if (teacherplanId == null){
            //新增
            Teachplan teachplan = new Teachplan();
            BeanUtils.copyProperties(saveTeachplanDto,teachplan);
            //确定排序字段
            Long parentid = saveTeachplanDto.getParentid();
            Long courseid = saveTeachplanDto.getCourseId();
            int count = getTeachplanCount(courseid,parentid);
            teachplan.setOrderby(count);
            teachplanMapper.insert(teachplan);

        }else {
            Teachplan teachplan = teachplanMapper.selectById(teacherplanId);
            //将参数复制到teachplan
            BeanUtils.copyProperties(saveTeachplanDto,teachplan);
            teachplanMapper.updateById(teachplan);
        }

    }

    @Override
    public void deleTeachplan(Long id) {
        //判断数据库中是否存在id
        List<TeachplanDto> teachplanDtos = teachplanMapper.checkTreeNodes(id);
        if (teachplanDtos.size()==0){
            TeachplanMedia teachplanMedia = teachplanMediaMapper.selectTeachPlanById(id);
            if (teachplanMedia!=null){
                teachplanMediaMapper.deleteById(teachplanMedia.getId());
            }
            teachplanMapper.deleteById(id);
        }else {
            XCerrcodeException.cast("120409","课程计划信息还有子级信息，无法操作");
        }


    }

    @Override
    public void movedown(Long id) {
//        查找下一行是不是存在
        Teachplan teachplan = teachplanMapper.selectById(id);

        if (teachplan.getParentid()==0){
            List<TeachplanDto> teachplanDtos = teachplanMapper.selectTreeNodes(teachplan.getCourseId());
            move(1,teachplan, teachplanDtos);

        }else {
            List<TeachplanDto> teachplanDtos = teachplanMapper.checkTreeNodes(teachplan.getParentid());
            move(1,teachplan, teachplanDtos);
        }
//        不存在就报个错误，说不存在下一行
//        XueChengPlusException.cast("下移失败，检查下一行是否存在");
    }



    @Override
    public void moveup(Long id) {
        Teachplan teachplan = teachplanMapper.selectById(id);

        if (teachplan.getParentid()==0){
            List<TeachplanDto> teachplanDtos = teachplanMapper.selectTreeNodes(teachplan.getCourseId());
            move(2,teachplan,teachplanDtos);
        }else {
            List<TeachplanDto> teachplanDtos = teachplanMapper.checkTreeNodes(teachplan.getParentid());
            move(2,teachplan,teachplanDtos);

        }
//        不存在就报个错误，说不存在下一行
//        XueChengPlusException.cast("上移失败，检查上一行是否存在");
    }




    private void move(int bool,Teachplan teachplan, List<TeachplanDto> teachplanDtos) {
        Teachplan temp = teachplan;
        for (TeachplanDto item : teachplanDtos) {
            if (bool==1){
                if (teachplan.getParentid().equals(item.getParentid()) && teachplan.getOrderby() < item.getOrderby()) {
                    temp = item;
                    break;
                }
            }
            if (bool==2){
                if (teachplan.getParentid().equals(item.getParentid()) && teachplan.getOrderby()>item.getOrderby()) {
                    temp = item;
                }
            }
        }
        if (temp!=teachplan){
            Integer orderby = temp.getOrderby();
            temp.setOrderby(teachplan.getOrderby());
            teachplan.setOrderby(orderby);
            teachplanMapper.updateById(teachplan);
            teachplanMapper.updateById(temp);
        }
    }

}


