package com.guli.edu.mapper;

import com.guli.edu.entity.CourseDescription;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 课程简介 Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2020-01-05
 */
@Component
public interface CourseDescriptionMapper extends BaseMapper<CourseDescription> {

}
