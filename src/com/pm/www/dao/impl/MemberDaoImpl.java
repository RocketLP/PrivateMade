package com.pm.www.dao.impl;
import com.pm.www.dao.MemberDao;
import com.pm.www.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
@Repository
public class MemberDaoImpl implements MemberDao {
    @Autowired
    private JdbcTemplate template;

    @Override
    public Member queryByAccount(String mAccount) {
        Member member = null;
        String str = "select * from tb_member where maccount=?";
        member=template.query(str, new Object[]{mAccount}, new ResultSetExtractor<Member>() {
            @Override
            public Member extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                Member member1=null;
                if (resultSet.next()){
                    member1 = new Member();
                    member1.setMid(resultSet.getInt(1));
                    member1.setMrole(resultSet.getInt(2));
                    member1.setMpassword(resultSet.getString(3));
                    member1.setMAccount(resultSet.getString(4));
                    member1.setMbrithday(resultSet.getString(5));
                    member1.setMgender(resultSet.getString(6));
                    member1.setMemail(resultSet.getString(7));
                    member1.setUserlevel(resultSet.getInt(8));
                    member1.setMcreatetime(resultSet.getString(9));
                    member1.setMphone(resultSet.getString(10));
                }
                return member1;
            }
        });
        return member;
    }

    @Override
    public int registerMember(Member member) {
        int index = -1;
        String str = "insert into tb_member (mid,mrole,mpassword,maccount,mbrithday,mgender,memail,memberlevel,mcreatetime,mphone) values(memberid.nextval,2,?,?,?,?,?,1,sysdate,?)";
        index=template.update(str,member.getMpassword(),member.getMAccount(),member.getMbrithday(),member.getMgender(),member.getMemail(),member.getMphone());
        return index;
    }
}
