package com.guli.edu.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.common.constants.ResultCodeEnum;
import com.guli.common.exception.GuliException;
import com.guli.common.vo.PageResult;
import com.guli.common.vo.Result;
import com.guli.edu.entity.Teacher;
import com.guli.edu.query.TeacherQuery;
import com.guli.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @author 1916
 * @create 2020-01-05 0:21
 */
@CrossOrigin //跨域
@RestController
@RequestMapping("/admin/edu/teacher")
@Api("讲师管理")
public class TeacherAdminController {

    private Logger logger = LoggerFactory.getLogger(TeacherAdminController.class);

    @Autowired
    private TeacherService teacherService;

    @ApiOperation("所有讲师列表")
    @GetMapping
    public Result list() {
        List<Teacher> list = teacherService.list(null);
        return new Result().ok(list);
    }


    //逻辑删除
    @ApiOperation("根据id删除讲师")
    @DeleteMapping("/{id}")
    public Result removeById(@ApiParam(name = "id", value = "讲师id", required = true) @PathVariable String id) {
        teacherService.removeById(id);
        return Result.ok();
    }

    @ApiOperation(value = "分页讲师列表")
    @GetMapping("/{page}/{limit}")
    public Result pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Integer page,
            @ApiParam(name = "limit", value = "每页显示记录数", required = true)
            @PathVariable Integer limit,
            @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
                    TeacherQuery teacherQuery) {

        if (page == null || limit == null || page <= 0 || limit <= 0) {
            throw new GuliException(ResultCodeEnum.PARAM_ERROR); //抛出参数异常
        }

        Page<Teacher> teacherPage = new Page<>(page, limit); //分页插件

        teacherService.pageQuery(teacherPage, teacherQuery);

        PageResult<Teacher> teacherPageResult = new PageResult<Teacher>(teacherPage.getTotal(), teacherPage.getRecords());
        return new Result().ok(teacherPageResult);
    }


    /**
     * 新增讲师
     *
     * @param teacher
     * @return
     */
    @ApiOperation("新增讲师")
    @PostMapping
    public Result save(@ApiParam(name = "teacher", value = "讲师", required = true) @RequestBody Teacher teacher) {
        teacherService.save(teacher);
        return Result.ok();
    }


    /**
     * 修改讲师
     *
     * @param id
     * @param teacher
     * @return
     */
    @ApiOperation("修改讲师")
    @PutMapping("/{id}")
    public Result update(@PathVariable("id") @ApiParam(name = "id", value = "讲师id", required = true) String id,
                         @ApiParam(name = "teacher", value = "讲师", required = true) @RequestBody Teacher teacher) {
        teacher.setId(id);
        teacherService.updateById(teacher);
        return Result.ok();
    }

    /**
     * 根据id查询讲师
     *
     * @param id
     * @param id
     * @return
     */
    @ApiOperation("根据id查询讲师")
    @GetMapping("/{id}")
    public Result getTeacherById(@ApiParam(name = "id", value = "讲师id", required = true) @PathVariable("id") String id) {

        if (StringUtils.isEmpty(id)) {
            throw new GuliException(ResultCodeEnum.PARAM_ERROR);
        }
        return new Result().ok(teacherService.getById(id));
    }


}
