package com.guli.usercenter.controller;

import com.guli.common.vo.Result;
import com.guli.usercenter.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
 * @create 2020-01-18 21:06
 */
@RestController
@RequestMapping("/admin/ucenter/member")
@Api("用户中心")
@CrossOrigin
public class MemberAdminController {

    @Autowired
    private MemberService memberService;

    @GetMapping("count-register/{day}")
    @ApiOperation("获取某天注册人数")
    public Result getRegisterCount(@ApiParam(name = "day",value="统计日期",required = true) @PathVariable String day){
        return new Result().ok(memberService.countRegisterByDay(day));
    }


}
