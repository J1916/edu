package com.guli.usercenter.service.impl;

import com.guli.usercenter.entity.Member;
import com.guli.usercenter.mapper.MemberMapper;
import com.guli.usercenter.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2019-07-01
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

	@Override
	public Integer countRegisterByDay(String day) {
		return baseMapper.selectRegisterCount(day);
	}
}
