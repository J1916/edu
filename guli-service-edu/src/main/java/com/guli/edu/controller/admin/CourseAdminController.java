package com.guli.edu.controller.admin;

import com.guli.common.vo.Result;
import com.guli.edu.entity.CourseInfoForm;
import com.guli.edu.service.CourseService;
import com.netflix.discovery.converters.Auto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 课程管理
 */
@RestController
@RequestMapping("/admin/edu/course")
@CrossOrigin
@Api("课程管理")
public class CourseAdminController {

    @Autowired
    private CourseService courseService;

    /**
     * 保存课程信息和课程详情
     * @param courseInfoForm
     * @return
     */
    @PostMapping("/save-course-info")
   public Result saveCourseInfo(@ApiParam(name = "courseInfoForm" ,value="课程基本信息",required = true) @RequestBody CourseInfoForm courseInfoForm){
        String courseId = courseService.saveCourseInfo(courseInfoForm);
        return new Result().ok(courseId);
   }

}
