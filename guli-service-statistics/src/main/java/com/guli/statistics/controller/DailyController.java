package com.guli.statistics.controller;


import com.guli.common.vo.Result;
import com.guli.statistics.service.DailyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author Helen
 * @since 2019-07-01
 */
@CrossOrigin
@RestController
@RequestMapping("/admin/statistics/daily")
@Api("网站统计日数据")
public class DailyController {

	@Autowired
	private DailyService dailyService;

	@GetMapping("/{day}")
	@ApiOperation("创建统计数据")
	public Result createStatisticsByDay(@ApiParam(name="day",value="统计日期",required = true) @PathVariable String day){
	  	dailyService.createStatisticsByDay(day);
	    return Result.ok();
	}

}

