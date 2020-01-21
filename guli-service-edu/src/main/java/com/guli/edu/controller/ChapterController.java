package com.guli.edu.controller;


import com.guli.common.vo.Result;
import com.guli.edu.entity.Chapter;
import com.guli.edu.service.ChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Helen
 * @since 2020-01-05
 */
@RestController
@RequestMapping("/edu/chapter")
@Api("课程章节管理")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @ApiOperation("新增章节")
    @PostMapping
    public Result saveChapter(@ApiParam(name="chapter",value = "章节对象",required = true) @RequestBody Chapter chapter){
        chapterService.save(chapter);
        return Result.ok();
    }


    @ApiOperation(" 根据id删除章节")
    @DeleteMapping("/{id}")
    public Result deleteChapterById(@ApiParam(name = "id" ,value = "章节id",required = true)@PathVariable String id){
        chapterService.removeById(id);
        return Result.ok();
    }

    @ApiOperation("根据id更改章节")
    @PutMapping("/{id}")
    public Result updateChapterById(
            @ApiParam(name = "id" ,value = "章节id",required = true)@PathVariable String id,
            @ApiParam(name="chapter",value = "章节对象",required = true) @RequestBody Chapter chapter){
        chapterService.updateById(chapter);
        return Result.ok();
    }

    @ApiOperation("根据id获取章节")
    @GetMapping("/{id}")
    public Result getChapterById(@ApiParam(name = "id" ,value = "章节id",required = true)@PathVariable String id){
        Chapter chapter = chapterService.getById(id);
        return Result.ok().data(chapter);
    }


    @GetMapping("/chapterNodeList/{courseId}")
    @ApiOperation("根据课程id嵌套查询章节数据列表")
    public Result getChapterNodeList(@ApiParam(name = "courseId" ,value = "课程id",required = true)@PathVariable String courseId){

//       List<Result> list = chapterService.getChapterNodeList(courseId);
        return Result.ok();
    }


}

