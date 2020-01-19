package com.guli.sysuser.controller;

import com.guli.common.vo.Result;
import com.guli.sysuser.entity.Sysuser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

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
 * @create 2020-01-12 15:04
 */
@RestController
@RequestMapping("/admin/sysuser")
@CrossOrigin
@Api("系统用户管理")
public class SysUserController {

    /**
     * 用户登陆
     * @param sysuser
     * @return
     */
    @PostMapping("/login")
    @ApiOperation("用户登陆")
    public Result login(@ApiParam(name="sysuser",value = "用户对象",required = true) @RequestBody Sysuser sysuser){
        return  Result.ok().data("token","admin");
    }

    /**
     * 退出登陆
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation("用户退出登陆")
    public Result logout(){
        return  Result.ok();
    }

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    @GetMapping("/info")
    @ApiOperation("获取用户信息")
    public Result getUserInfo(@ApiParam(name="token",value = "令牌",required = true) @RequestParam  String token){
        HashMap<String, Object> map = new HashMap<>();
        map.put("roles",new String[]{"admin"});
        map.put("name","admin");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return  Result.ok().data(map);
    }

}
