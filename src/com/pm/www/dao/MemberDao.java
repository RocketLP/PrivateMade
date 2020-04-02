package com.pm.www.dao;

import com.pm.www.domain.Member;

import java.awt.print.Book;

public interface MemberDao {
    /**
     * 账号查询
     */

    Member queryByAccount(String mAccount);

    /**
     * 注册
     */

    int registerMember(Member member);
}
