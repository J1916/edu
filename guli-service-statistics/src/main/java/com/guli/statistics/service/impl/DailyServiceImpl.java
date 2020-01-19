package com.guli.statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.guli.common.vo.Result;
import com.guli.statistics.client.UcenterClient;
import com.guli.statistics.entity.Daily;
import com.guli.statistics.mapper.DailyMapper;
import com.guli.statistics.service.DailyService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2019-07-01
 */
@Service
public class DailyServiceImpl extends ServiceImpl<DailyMapper, Daily> implements DailyService {


	@Autowired
	private UcenterClient ucenterClient;

	@Autowired
	private BaseMapper baseMapper;


	@Override
	public void createStatisticsByDay(String day) {

		//删除已经存在的统计对象
		QueryWrapper<Daily> query = new QueryWrapper();
		query.eq("date_calculated",day);
		baseMapper.delete(query);

		//获取统计数据
		Integer registerNum =(Integer) ucenterClient.countRegisterByDay(day).getData();
		Integer loginNum = RandomUtils.nextInt(100, 200);//TODO 虚拟数据
		Integer videoViewNum = RandomUtils.nextInt(100, 200);//TODO 虚拟数据
		Integer courseNum = RandomUtils.nextInt(100, 200);//TODO 虚拟数据

		//创建统计对象
		Daily daily = new Daily();
		daily.setCourseNum(courseNum);
		daily.setLoginNum(loginNum);
		daily.setVideoViewNum(videoViewNum);
		daily.setRegisterNum(registerNum); //今天注册人数
		daily.setDateCalculated(day);

		baseMapper.insert(daily);
	}


	@Override
	public Map<String, Object> getChartData(String begin, String end, String type) {

		QueryWrapper query = new QueryWrapper();
		query.select(type,"date_calculated");
		query.between("date_calculated",begin,end);

		List<Daily> daylist = baseMapper.selectList(query);

		Map<String, Object> map = new HashMap<>();
		List<Integer> dataList = new ArrayList<Integer>();
		List<String> dateList = new ArrayList<String>();

		map.put("dataList",dataList);
		map.put("dateList",dateList);

		for (Daily daily: daylist) {
			dateList.add(daily.getDateCalculated());
			switch (type){
				case "login_num" :
					   dataList.add(daily.getLoginNum()); //登录人数
					break;
				case "register_num" :
					dataList.add(daily.getRegisterNum());//注册人数
					break;
				case "video_view_num" :
					dataList.add(daily.getVideoViewNum()); //每日播放视频数
					break;
				case "course_num" :
					dataList.add(daily.getCourseNum()); //每日新增课程数
					break;
				default:
					break;
			}
		}
		return map;
	}
}
