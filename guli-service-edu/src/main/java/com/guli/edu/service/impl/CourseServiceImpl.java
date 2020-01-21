package com.guli.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.common.exception.GuliException;
import com.guli.edu.entity.*;
import com.guli.edu.mapper.ChapterMapper;
import com.guli.edu.mapper.CourseDescriptionMapper;
import com.guli.edu.mapper.CourseMapper;
import com.guli.edu.mapper.VideoMapper;
import com.guli.edu.query.CourseQuery;
import com.guli.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.guli.edu.service.VideoService;
import com.netflix.discovery.converters.Auto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2020-01-05
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    CourseDescriptionMapper courseDescriptionMapper;

    @Autowired
    VideoMapper videoMapper;

    @Autowired
    ChapterMapper chapterMapper;

    /**
     * 添加课程信息和课程详情
     * @param courseInfoForm
     * @return 新生成的课程id
     */
    @Override
    @Transactional
    public String saveCourseInfo(CourseInfoForm courseInfoForm) {
        //保存课程基本信息
        Course course = new Course();
        course.setStatus(Course.COURSE_DRAFT);
        BeanUtils.copyProperties(courseInfoForm, course);
        baseMapper.insert(course);

        //保存课程详细信息

        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setId(course.getId());
        courseDescription.setDescription(courseInfoForm.getDescription()); //课程详情
        courseDescriptionMapper.insert(courseDescription);
        return course.getId();
    }

    @Override
    public CourseInfoForm getCourseInfoFormById(String id) {

        //查询课程基础信息
        Course course = baseMapper.selectById(id);
        if(course == null){
            throw new GuliException(20001, "数据不存在");
        }

        //查询课程详情
        CourseDescription courseDescription = courseDescriptionMapper.selectById(id);

        if(courseDescription == null){
            throw new GuliException(20001, "数据不完整");
        }

        CourseInfoForm courseInfoForm = new CourseInfoForm();
        //拷贝对象
        BeanUtils.copyProperties(course,courseInfoForm);
        //详情
        courseInfoForm.setDescription(courseDescription.getDescription());
        return courseInfoForm;
    }

    /**
     * 更新课程
     * @param courseInfoForm
     */
    @Override
    public void updateCourseInfoById(CourseInfoForm courseInfoForm) {
        Course course = new Course();
        //拷贝对象
        BeanUtils.copyProperties(courseInfoForm,course);

        CourseDescription courseDescription = new CourseDescription();
        BeanUtils.copyProperties(courseInfoForm,courseDescription);

        //修改课程基础信息
        baseMapper.updateById(course);
        //修改课程详情
        courseDescriptionMapper.updateById(courseDescription);
    }

    /**
     * 删除课程
     * @param id
     */
    @Override
    public void deleteCourseInfoById(String id) {

        //根据课程id删除课程所有视频
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id",id); //课程id
        videoMapper.delete(videoQueryWrapper);

        //根据课程id删除视频所有章节
        QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id",id); //课程id
        chapterMapper.delete(chapterQueryWrapper);


        //删除课程详情
        courseDescriptionMapper.deleteById(id);

        //删除课程基础信息
        baseMapper.deleteById(id);

    }

    /**
     * 分页+条件查询课程
     * @param coursePage
     * @param courseQuery
     */
    @Override
    public void pageQuery(Page<Course> coursePage, CourseQuery courseQuery) {

        String subjectParentId = courseQuery.getSubjectParentId(); //课程类别 1级类别
        String subjectId = courseQuery.getSubjectId();//课程类别 2级类别
        String teacherId = courseQuery.getTeacherId(); //选择教师
        String title = courseQuery.getTitle(); //课程标题

        QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.orderByAsc("gmt_create");
        if(StringUtils.isNotBlank(title)){
            courseQueryWrapper.like("title",title);
        }
        if(StringUtils.isNotBlank(teacherId)){
            courseQueryWrapper.eq("teacher_id",teacherId);
        }
        if(StringUtils.isNotBlank(subjectId)){
            courseQueryWrapper.eq("subject_id",subjectId);
        }
        if(StringUtils.isNotBlank(subjectParentId)){
            courseQueryWrapper.eq("subject_parent_id",subjectParentId);
        }
        //查询课程基础
        baseMapper.selectPage(coursePage,courseQueryWrapper);

    }
}
