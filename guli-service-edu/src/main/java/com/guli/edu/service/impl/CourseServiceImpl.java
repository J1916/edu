package com.guli.edu.service.impl;

import com.guli.edu.entity.Course;
import com.guli.edu.entity.CourseDescription;
import com.guli.edu.entity.CourseInfoForm;
import com.guli.edu.mapper.CourseDescriptionMapper;
import com.guli.edu.mapper.CourseMapper;
import com.guli.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
}
