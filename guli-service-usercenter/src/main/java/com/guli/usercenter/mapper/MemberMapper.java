package com.guli.usercenter.mapper;

import com.guli.usercenter.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2019-07-01
 */
public interface MemberMapper extends BaseMapper<Member> {

	Integer selectRegisterCount(String day);
}
