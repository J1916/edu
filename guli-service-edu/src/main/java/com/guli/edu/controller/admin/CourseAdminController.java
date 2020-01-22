package com.guli.edu.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.common.constants.ResultCodeEnum;
import com.guli.common.exception.GuliException;
import com.guli.common.vo.PageResult;
import com.guli.common.vo.Result;
import com.guli.edu.entity.Course;
import com.guli.edu.entity.CourseInfoForm;
import com.guli.edu.entity.Teacher;
import com.guli.edu.query.CourseQuery;
import com.guli.edu.service.CourseService;
import com.guli.edu.vo.CoursePublishVo;
import com.netflix.discovery.converters.Auto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     *
     * @param courseInfoForm
     * @return
     */
    @ApiOperation("添加课程")
    @PostMapping("/save-course-info")
    public Result saveCourseInfo(@ApiParam(name = "courseInfoForm", value = "课程基本信息", required = true) @RequestBody CourseInfoForm courseInfoForm) {
        String courseId = courseService.saveCourseInfo(courseInfoForm);
        return new Result().ok(courseId);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id查询课程")
    public Result getCoursInfoById(@ApiParam(name = "id", value = "课程id", required = true) @PathVariable String id) {
        CourseInfoForm courseInfoForm = courseService.getCourseInfoFormById(id);
        return new Result().ok(courseInfoForm);
    }

    @PutMapping("/updateCourseInfo/{id}")
    @ApiOperation("更新课程")
    public Result updateCourseInfoById(
            @ApiParam(name = "courseInfoForm", value = "课程基本信息", required = true) @RequestBody CourseInfoForm courseInfoForm,
            @ApiParam(name = "id", value = "课程id", required = true) @PathVariable String id) {
        courseService.updateCourseInfoById(courseInfoForm);
        return Result.ok();
    }

    @ApiOperation("分页+条件查询课程信息")
    @GetMapping("/{page}/{limit}")
    public Result pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Integer page,
            @ApiParam(name = "limit", value = "每页显示记录数", required = true)
            @PathVariable Integer limit,
            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
                    CourseQuery courseQuery) {

        if (page == null || limit == null || page <= 0 || limit <= 0) {
            throw new GuliException(ResultCodeEnum.PARAM_ERROR); //抛出参数异常
        }

        Page<Course> coursePage = new Page<>(page, limit); //分页插件

        //分页+条件查询
        courseService.pageQuery(coursePage, courseQuery);

        PageResult<Course> coursePageResult = new PageResult<Course>(coursePage.getTotal(), coursePage.getRecords());
        return new Result().ok(coursePageResult);
    }


    @DeleteMapping("/{id}")
    @ApiOperation("根据id删除课程")
    public Result deleteCourseInfo(@ApiParam(name = "id", value = "课程id", required = true) @PathVariable String id) {
        courseService.deleteCourseInfoById(id);
        return Result.ok();
    }

    @GetMapping("/getCoursePublishVoById/{id}")
    @ApiOperation("根据ID获取课程发布信息")
    public Result getCoursePublishVoById(@ApiParam(name = "id", value = "课程id", required = true) @PathVariable String id) {
        CoursePublishVo coursePublishVoById = courseService.getCoursePublishVoById(id);
        return new Result().ok(coursePublishVoById);
    }


    /**
     * 根据id发布课程
     * @param id
     * @return
     */
    @PostMapping("/publishCourseById")
    @ApiOperation("根据id发布课程")
    public Result publishCourseById(@ApiParam(name = "id", value = "课程id", required = true) @PathVariable String id){
        courseService.publishCourseById(id);
        return new Result().ok();
    }

}
