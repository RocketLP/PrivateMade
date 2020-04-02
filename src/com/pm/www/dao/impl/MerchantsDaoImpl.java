package com.pm.www.dao.impl;

import com.pm.www.dao.MerchantsDao;
import com.pm.www.domain.Merchants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MerchantsDaoImpl implements MerchantsDao {

    @Autowired
    JdbcTemplate template;

    @Override
    public Merchants queryByMcName(String mcName) {
        String str = "select * from tb_merchants where mcName = ?";
        Merchants merchants=template.query(str, new Object[]{mcName}, new ResultSetExtractor<Merchants>() {
            @Override
            public Merchants extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                Merchants merchants1=null;
                if (resultSet.next()) {
                    merchants1 = new Merchants();
                    merchants1.setMcid(resultSet.getInt(1));
                    merchants1.setMcname(resultSet.getString(2));
                    merchants1.setMcpassword(resultSet.getString(3));
                    merchants1.setMclicense(resultSet.getString(4));
                    merchants1.setMccreatetime(resultSet.getString(5));
                    merchants1.setMcphone(resultSet.getString(6));
                    merchants1.setMcstate(resultSet.getInt(7));
                }
                return merchants1;
            }
        });
        return merchants;
    }

    @Override
    public int mcRegister(Merchants merchants) {
        int index = -1;
        String str = "insert into tb_merchants (mcid,mcname,mcpassword,mclicense,mccreatetime,mcphone,mcstate) values(merchantsid.nextval,?,?,?,sysdate,?,2)";
        index=template.update(str,merchants.getMcname(),merchants.getMcpassword(),merchants.getMclicense(),merchants.getMcphone());
        return index;
    }

    @Override
    public List<Map<String, Object>> queryAllMerchants() {
        String str = "select * from tb_merchants";
        List<Map<String, Object>> merchants =template.query(str, new RowMapper<Map<String, Object>>() {
            @Override
            public Map<String, Object> mapRow(ResultSet resultSet, int i) throws SQLException {
                Map<String, Object> map =null;
                if (resultSet.next()) {
                    map=new HashMap<>();
                    map.put("mcid", resultSet.getInt(1));
                    map.put("mcname", resultSet.getString(2));
                    map.put("mcpassword", resultSet.getString(3));
                    map.put("mclicense", resultSet.getString(4));
                    map.put("mccreatetime", resultSet.getString(5));
                    map.put("mcphone", resultSet.getString(6));
                    map.put("mcstate", resultSet.getInt(7));
                }
                return map;
            }
        });
        return merchants;
    }

    @Override
    public int reject(int mcid) {
        int index = -1;
        String str = "update tb_merchants set mcstate = 3 where mcid=?";
        index=template.update(str,mcid);
        return index;
    }

    @Override
    public int pass(int mcid) {
        int index = -1;
        String str = "update tb_merchants set mcstate = 1 where mcid=?";
        index=template.update(str,mcid);
        return index;
    }

    @Override
    public List<Map<String, Object>> queryNoCheckedMerchants() {
        String str = "select * from tb_merchants where mcstate=2";
        List<Map<String, Object>> merchants = template.query(str, new RowMapper<Map<String, Object>>() {
            @Override
            public Map<String, Object> mapRow(ResultSet resultSet, int i) throws SQLException {
                Map<String, Object> map = null;
                if (resultSet.next()) {
                    map=new HashMap<>();
                    map.put("mcid", resultSet.getInt(1));
                    map.put("mcname", resultSet.getString(2));
                    map.put("mcpassword", resultSet.getString(3));
                    map.put("mclicense", resultSet.getString(4));
                    map.put("mccreatetime", resultSet.getString(5));
                    map.put("mcphone", resultSet.getString(6));
                    map.put("mcstate", resultSet.getInt(7));
                }
                return map;
            }
        });
        return merchants;
    }

    @Override
    public String queryMerchantsNameById(int mcid) {
        String str = "select mcname from tb_merchants where mcid=?";
        String mcname =template.query(str, new Object[]{mcid}, new ResultSetExtractor<String>() {
            @Override
            public String extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                String mcname1="";
                if (resultSet.next()){
                    mcname1=resultSet.getString(1);
                }
                return mcname1;
            }
        });
        return mcname;
    }
}
