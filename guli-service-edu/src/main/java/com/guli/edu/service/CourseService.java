package com.guli.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.edu.entity.CourseInfoForm;
import com.guli.edu.query.CourseQuery;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Helen
 * @since 2020-01-05
 */
public interface CourseService extends IService<Course> {

    String saveCourseInfo(CourseInfoForm courseInfoForm);
    CourseInfoForm getCourseInfoFormById(String id);
    void updateCourseInfoById (CourseInfoForm courseInfoForm);

    void pageQuery(Page<Course> coursePage, CourseQuery courseQuery);
}
