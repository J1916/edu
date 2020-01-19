package com.guli.edu.controller;


import com.guli.common.constants.ResultCodeEnum;
import com.guli.common.exception.GuliException;
import com.guli.common.util.ExceptionUtil;
import com.guli.common.vo.Result;
import com.guli.edu.service.SubjectService;
import com.guli.edu.vo.SubjectNestedVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author Helen
 * @since 2020-01-05
 */
@RestController
@RequestMapping("/admin/edu/subject")
@CrossOrigin
@Api("课程分类管理")
public class SubjectController {

    private Logger logger = LoggerFactory.getLogger(SubjectController.class);

    @Autowired
    private SubjectService subjectService;

    @PostMapping("/batchImport")
    @ApiOperation("批量导入课程一二级分类")
    public Result batchImport(@ApiParam(name = "file", value = "Excel文件", required = true) MultipartFile file) {

        if (file == null || file.getSize() == 0) {
            throw new GuliException(ResultCodeEnum.PARAM_ERROR);
        }

        try {
            List<String> errorMsg = subjectService.batchImport(file);

            if (errorMsg.size() == 0) {
                return Result.ok().message("批量导入成功");
            } else {
                return Result.ok().message("批量导入部分失败").data(errorMsg);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.info(ExceptionUtil.getMessage(e) + "批量导入数据失败");
            //无论哪种异常，只要是在excel导入时发生的，一律用转成GuliException抛出
            throw new GuliException(ResultCodeEnum.FILE_UPLOAD_ERROR);

        }
    }

    @ApiOperation(value = "依照等级获取所有的课程分类（树形结构）")
    @GetMapping
    public Result getsubjectList() {
        List<SubjectNestedVo> list = subjectService.getsubjectList();
        return Result.ok().data(list);
    }


}

