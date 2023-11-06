package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.mapper.*;
import com.xuecheng.content.model.dto.*;
import com.xuecheng.content.model.po.*;
import com.xuecheng.content.service.CourseBaseInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author liu
 * @version 1.0
 * @project xuecheng-plus-project
 * @description TOOD
 * @date 2023/10/25 19:05:33
 */

@Slf4j
@Service
public class CourseBaseInfoServiceImpl implements CourseBaseInfoService {

    @Autowired
    CourseBaseMapper courseBaseMapper;

    @Autowired
    CourseMarketMapper courseMarketMapper;

    @Autowired
    CourseCategoryMapper courseCategoryMapper;


    @Autowired
    CoursePublishMapper coursePublishMapper;

    @Autowired
    CourseTeacherMapper courseTeacherMapper;

    @Autowired
    TeachplanMapper teachplanMapper;

    @Autowired
    TeachplanMediaMapper teachplanMediaMapper;

    /**
     * 课程分页查询
     *
     * @param pageParams      分页查询参数
     * @param courseParamsDto 查询条件
     * @return 查询结果
     */
    @Override
    public PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDto courseParamsDto) {


        //封装查询条件
        LambdaQueryWrapper<CourseBase> queryWrapper = new LambdaQueryWrapper<>();
        //根据名称模糊查询,在sql中拼接course_base.name like '%值%'
        queryWrapper.like(StringUtils.isNotEmpty(courseParamsDto.getCourseName()), CourseBase::getName, courseParamsDto.getCourseName());
        //根据审核状态查询,在sql中拼接course_base.audit_status = ?
        queryWrapper.eq(StringUtils.isNotEmpty(courseParamsDto.getAuditStatus()), CourseBase::getAuditStatus, courseParamsDto.getAuditStatus());
        //todo:按课程发布状态查询（publishStatus）
        queryWrapper.eq(StringUtils.isNotEmpty(courseParamsDto.getPublishStatus()), CourseBase::getStatus, courseParamsDto.getPublishStatus());


//        创建分页参数
        Page<CourseBase> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());

        //开始进行分页查询
        Page<CourseBase> basePage = courseBaseMapper.selectPage(page, queryWrapper);

        List<CourseBase> items = basePage.getRecords();
        long total = basePage.getTotal();
//      List<T> items, long counts, long page, long pageSize
        PageResult<CourseBase> pageResult = new PageResult<CourseBase>(items, total, pageParams.getPageNo(), pageParams.getPageSize());
        return pageResult;
    }


    @Transactional
    @Override
    public CourseBaseInfoDto createCourseBase(Long companyId, AddCourseDto dto) {
//        凡是控制数据库写入的都进行方法校验
        //合法性校验
//        if (StringUtils.isBlank(dto.getName())) {
////            throw new RuntimeException("课程名称为空");
//            XueChengPlusException.cast("课程名称为空");
//        }
//
//        if (StringUtils.isBlank(dto.getMt())) {
////            throw new RuntimeException("课程分类为空");
//            XueChengPlusException.cast("课程分类为空");
//        }
//
//        if (StringUtils.isBlank(dto.getSt())) {
////            throw new RuntimeException("课程分类为空");
//            XueChengPlusException.cast("课程分类为空");
//        }
//
//        if (StringUtils.isBlank(dto.getGrade())) {
////            throw new RuntimeException("课程等级为空");
//            XueChengPlusException.cast("课程等级为空");
//        }
//
//        if (StringUtils.isBlank(dto.getTeachmode())) {
////            throw new RuntimeException("教育模式为空");
//            XueChengPlusException.cast("教育模式为空");
//        }
//
//        if (StringUtils.isBlank(dto.getUsers())) {
////            throw new RuntimeException("适应人群为空");
//            XueChengPlusException.cast("适应人群为空");
//        }
//
//        if (StringUtils.isBlank(dto.getCharge())) {
////            throw new RuntimeException("收费规则为空");
//            XueChengPlusException.cast("收费规则为空");
//        }


//        //向coursebase表写入数据

        CourseBase courseBaseNew = new CourseBase();
        //        这种方法太复杂
//        courseBaseNew.setName(dto.getName());
        //用dto类进行copy
        BeanUtils.copyProperties(dto,courseBaseNew);//只要属性名一样可以拷贝，如果dto为空，也会覆盖
        courseBaseNew.setCompanyId(companyId);
        courseBaseNew.setCreateDate(LocalDateTime.now());
        //默认审核状态和课程状态
        courseBaseNew.setAuditStatus("202002");
        courseBaseNew.setStatus("203001");

        int insert = courseBaseMapper.insert(courseBaseNew);
        if (insert<=0){
//            throw new RuntimeException("添加课程失败");
            XueChengPlusException.cast("添加课程失败");
        }


        //向课程course_marke表写入数据
        CourseMarket courseMarketNew = new CourseMarket();

//        copy
        BeanUtils.copyProperties(dto,courseMarketNew);

        Long courseId = courseBaseNew.getId();
        courseMarketNew.setId(courseId);

        //校验课程
        saveCourseMarket(courseMarketNew);
        //从数据库查询课程的详细信息

        CourseBaseInfoDto courseBaseInfo = getCourseBaseInfo(courseId);

        return courseBaseInfo;
    }



    //查询课程信息
    @Override
    public CourseBaseInfoDto getCourseBaseInfo(Long couserId){

        CourseBase courseBase = courseBaseMapper.selectById(couserId);
        if (courseBase==null){
            return null;
        }
        CourseMarket courseMarket = courseMarketMapper.selectById(couserId);

        CourseBaseInfoDto courseBaseInfoDto = new CourseBaseInfoDto();
        BeanUtils.copyProperties(courseBase,courseBaseInfoDto);
        if (courseMarket==null){
            return null;
        }
        BeanUtils.copyProperties(courseMarket,courseBaseInfoDto);

        CourseCategory courseCategory = courseCategoryMapper.selectById(courseBase.getMt());
        courseBaseInfoDto.setMtName(courseCategory.getName());

//        提供
        return courseBaseInfoDto;


    }

    @Override
    public CourseBaseInfoDto updateCourseBase(EditCourseDto editCourseDto, Long companyId) {

//        先拿到课程id
        Long couseId = editCourseDto.getId();
//        查询课程
        CourseBase courseBase = courseBaseMapper.selectById(couseId);
        if (courseBase == null){
            XueChengPlusException.cast("课程不存在");
        }


//        数据合法性校验
//        具体业务逻辑校验
//        本机构只能修改本机构的课程
        if (!companyId.equals(courseBase.getCompanyId())){
            XueChengPlusException.cast("本机构只能修改本机构课程");
        }

//        数据封装
        BeanUtils.copyProperties(editCourseDto,courseBase);
//        修改时间
        courseBase.setChangeDate(LocalDateTime.now());

//        更新数据库
        int i = courseBaseMapper.updateById(courseBase);
        if (i<=0){
            XueChengPlusException.cast("课程更新修改失败");
        }

//        更新营销信息
//        todo:更新营销信息
        CourseMarket courseMarket = courseMarketMapper.selectById(couseId);
        BeanUtils.copyProperties(editCourseDto,courseMarket);
        int i1 = courseMarketMapper.updateById(courseMarket);
        if (i1<=0){
            XueChengPlusException.cast("课程营销信息更新修改失败");
        }


//        查询课程信息
        CourseBaseInfoDto courseBaseInfo = getCourseBaseInfo(couseId);

        return courseBaseInfo;
    }

    @Override
    @Transactional
    public void deleteCourseBase(Long companyId, Long id) {
        CourseBase courseBase = courseBaseMapper.selectById(id);
        if (courseBase!=null){
            if (courseBase.getCompanyId().equals(companyId)) {
                courseBaseMapper.deleteById(id);
                CourseMarket courseMarket = courseMarketMapper.selectById(id);
                if (courseMarket!=null){
                    courseMarketMapper.deleteById(id);
                }
                CoursePublish coursePublish = coursePublishMapper.selectById(id);
                if (coursePublish!=null){
                    coursePublishMapper.deleteById(id);
                }

                List<CourseTeacher> courseTeachers = courseTeacherMapper.selectByCourseId(id);
                if (courseTeachers != null) {
                    for (CourseTeacher courseTeacher : courseTeachers) {
                        courseTeacherMapper.deleteById(courseTeacher.getId()); // 假设 delete 方法接受对象的 ID 或对象本身作为参数
                    }
                }
                List<TeachplanDto> teachplanDtos = teachplanMapper.selectByCourseId(id);
                if (teachplanDtos != null && !teachplanDtos.isEmpty()) {
                    for (Teachplan teachplan : teachplanDtos) {
                        // 执行删除操作
                        teachplanMapper.deleteById(teachplan.getId()); // 假设这是删除操作，deleteById 方法需要根据你的 MyBatis Mapper 自定义
                    }
                }

                List<TeachplanMedia> teachplanMediaList = teachplanMediaMapper.selectByCourseId(id);
                if (teachplanMediaList!=null){
                    for (TeachplanMedia teachplanMedia : teachplanMediaList) {
                        teachplanMediaMapper.deleteById(teachplanMedia.getId());
                    }
                }

            }else {
                XueChengPlusException.cast("非本机构课程不得删除！");
            }

        }
    }


    //    单独写一个方法保存营销信息，存在则更新，不存在则添加
    private int saveCourseMarket(CourseMarket courseMarketNew){
        //合法性校验
        String charge = courseMarketNew.getCharge();
        if (StringUtils.isEmpty(charge)){
//            throw new RuntimeException("收费规程未填写");
            XueChengPlusException.cast("收费规程未填写");
        }
        if (charge.equals("201001")){
            if (courseMarketNew.getPrice() == null|| courseMarketNew.getPrice() <=0){
//                throw new RuntimeException("收费课程价格填写错误,不为空且大于0");
                XueChengPlusException.cast("收费课程价格填写错误,不为空且大于0");
            }

        }

        //从数据库查询信息，存在则更新，不存在就保存
        Long id = courseMarketNew.getId();
        CourseMarket courseMarket = courseMarketMapper.selectById(id);
        if (courseMarket == null){
            //为空插入
            int insert = courseMarketMapper.insert(courseMarketNew);
            return insert;
        }else {
//            不为空则拷贝
            BeanUtils.copyProperties(courseMarketNew,courseMarket);
            courseMarket.setId(courseMarketNew.getId());


            int update = courseMarketMapper.updateById(courseMarket);
            return update;
        }

    }


}
