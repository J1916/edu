package com.guli.statistics.client;

import com.guli.common.vo.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * feign是基于轻量http协议来调用的
 */
//@FeignClient注解用于指定从哪个服务中调用功能 ，名称与被调用的服务名保持一致。
@Component
@FeignClient("guli-usercenter")
@RequestMapping("/admin/ucenter/member")
public interface UcenterClient {

    /**
     * 在远程调用接口时，restful风格的url参数必须要在@PathVariable("参数名") 注解中添加参数名。否则无法调用
     * @param day
     * @return
     */

    @GetMapping("/count-register/{day}")
    @ApiOperation("获取某天注册人数")
    Result countRegisterByDay(@PathVariable("day") String day);

}
