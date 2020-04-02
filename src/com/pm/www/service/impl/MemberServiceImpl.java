package com.pm.www.service.impl;
import com.pm.www.dao.impl.MemberDaoImpl;
import com.pm.www.domain.Member;
import com.pm.www.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberDaoImpl memberDao;

    @Override
    @Transactional(readOnly = true)
    public Member queryByAccount(String mAccount) {
        return memberDao.queryByAccount(mAccount);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation= Propagation.REQUIRED,readOnly = false)
    public int registerMember(Member member) {
        return memberDao.registerMember(member);
    }
}
