package com.guli.usercenter.service;

import com.guli.usercenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author Helen
 * @since 2019-07-01
 */
@Service
public interface MemberService extends IService<Member> {
	Integer countRegisterByDay(String day);
}
