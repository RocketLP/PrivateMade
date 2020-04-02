package com.pm.www.service;

import com.pm.www.domain.Member;

public interface MemberService {
    /**
     * 按账号查找
     */
    Member queryByAccount(String mAccount);
    /**
     * 注册
     */
    int registerMember(Member member);
    /**
     *
     */
}
