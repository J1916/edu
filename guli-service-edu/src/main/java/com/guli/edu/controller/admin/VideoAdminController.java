package com.guli.edu.controller.admin;


import com.guli.common.vo.Result;
import com.guli.edu.entity.Chapter;
import com.guli.edu.entity.Video;
import com.guli.edu.form.VideoInfoForm;
import com.guli.edu.service.ChapterService;
import com.guli.edu.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author Helen
 * @since 2020-01-05
 */
@RestController
@RequestMapping("/admin/edu/video")
@CrossOrigin
@Api("课时管理")
public class VideoAdminController {
    @Autowired
    private VideoService videoService;

    @ApiOperation("新增课时")
    @PostMapping
    public Result saveVideoInfo(@ApiParam(name="videoInfoForm",value = "课时基本信息",required = true) @RequestBody VideoInfoForm videoInfoForm){
        videoService.saveVideoInfo(videoInfoForm);
        return Result.ok();
    }


    @ApiOperation(" 根据id删除课时")
    @DeleteMapping("/{id}")
    public Result deleteVideoInfoById(@ApiParam(name = "id" ,value = "课时id",required = true)@PathVariable String id){
        videoService.removeById(id);
        return Result.ok();
    }

    @ApiOperation("更新课时")
    @PutMapping("/{id}")
    public Result updateVideoInfoById(
            @ApiParam(name = "id" ,value = "课时id",required = true)@PathVariable String id,
            @ApiParam(name="videoInfoForm",value = "课时基本信息",required = true) @RequestBody VideoInfoForm videoInfoForm){
        videoService.updateVideoInfoById(videoInfoForm);
        return Result.ok();
    }

    @ApiOperation("根据id获取课时")
    @GetMapping("/{id}")
    public Result getVideoInfoById(@ApiParam(name = "id" ,value = "课时id",required = true)@PathVariable String id){
        VideoInfoForm video = videoService.getVideoInfoById(id);
        return Result.ok().data(video);
    }

}

