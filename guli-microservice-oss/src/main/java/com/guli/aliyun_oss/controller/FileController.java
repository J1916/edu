package com.guli.aliyun_oss.controller;

import com.guli.aliyun_oss.service.FileService;
import com.guli.aliyun_oss.util.ConstantPropertiesUtil;
import com.guli.common.constants.ResultCodeEnum;
import com.guli.common.exception.GuliException;
import com.guli.common.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.reflect.misc.ConstructorUtil;

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
 * 文件上传controller
 *
 * @author 1916
 * @create 2020-01-12 17:43
 */
@RestController
@RequestMapping("/admin/oss/file")
@CrossOrigin
@Api("阿里云文件管理")
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result upload(@ApiParam(name="file",value="上传的文件",required = true) @RequestParam( "file") MultipartFile file ,
    @ApiParam(name="host",value="文件上传路径",required = false) @RequestParam(value = "host",required = false) String host){
        if(file == null || StringUtils.isEmpty(file.getOriginalFilename())){
            throw new GuliException(ResultCodeEnum.PARAM_ERROR);
        }

        if(!StringUtils.isEmpty(host)){
            ConstantPropertiesUtil.FILE_HOST = host; //由前端携带的上传路径，设置文件上传路径
        }

            return  Result.ok().message("文件上传成功").data("url",fileService.upload(file));
    }
}
