package com.guli.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.edu.entity.Teacher;
import com.guli.edu.mapper.TeacherMapper;
import com.guli.edu.query.TeacherQuery;
import com.guli.edu.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2020-01-05
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    //条件查询
    @Override
    public void pageQuery(Page<Teacher> pageParam, TeacherQuery teacherQuery) {
        QueryWrapper<Teacher> query = new QueryWrapper<Teacher>();
        query.orderByAsc("sort");

        //没有条件查询则查询全部
        if (teacherQuery == null) {
            baseMapper.selectPage(pageParam, query);
            return;
        }

        //设置查询条件
        if (StringUtils.isNoneBlank(teacherQuery.getName())) {
            query.like("name", teacherQuery.getName());//根据名字模糊搜索
        }
        if (teacherQuery.getLevel() != null) {
            query.eq("level", teacherQuery.getLevel());
        }
        if (StringUtils.isNotEmpty(teacherQuery.getBegin())) {
            query.gt("gmt_create", teacherQuery.getBegin()); //创建时间
        }
        if (StringUtils.isNotEmpty(teacherQuery.getEnd())) {
            query.lt("gmt_create", teacherQuery.getEnd()); //结束时间
        }

        baseMapper.selectPage(pageParam, query);
    }
}
