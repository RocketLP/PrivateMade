package com.pm.www.dao.impl;

import com.pm.www.dao.GoodTypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class GoodTypeDaoImpl implements GoodTypeDao {

    @Autowired
    JdbcTemplate template;

    @Override
    public List<Map<String, Object>> queryAllGoodType() {
        String str="select * from tb_goodtype";
        List<Map<String,Object>> goodType=template.query(str, new RowMapper<Map<String, Object>>() {
            @Override
            public Map<String, Object> mapRow(ResultSet resultSet, int i) throws SQLException {
                Map<String, Object> map = null;
                if (resultSet.next()){
                    map=new HashMap<>();
                    map.put("goodType", resultSet.getInt(1));
                    map.put("goodTypeName", resultSet.getString(2));
                }
                return map;
            }
        });
        return goodType;
    }

    @Override
    public int addGoodType(String goodTypeName) {
        int index=-1;
        String str="insert into tb_goodType (gtype,gtypename) values(goodtypeid.nextval,?)";
        index=template.update(str,goodTypeName);
        return index;
    }
}
