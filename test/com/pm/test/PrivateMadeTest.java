package com.pm.test;

import com.pm.www.domain.Member;
import com.pm.www.service.impl.MemberServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PrivateMadeTest {

    @Test
    public void testQueryMember(){
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        MemberServiceImpl service=context.getBean(MemberServiceImpl.class);
        Member member=service.queryByAccount("lipin");
        System.out.println(member);
    }
}
