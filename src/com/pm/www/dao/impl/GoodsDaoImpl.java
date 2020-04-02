package com.pm.www.dao.impl;

import com.pm.www.dao.GoodsDao;
import com.pm.www.domain.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GoodsDaoImpl implements GoodsDao {
    @Autowired
    private JdbcTemplate template;

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public int addGood(Goods goods) {
        int index = -1;
        String str = "insert into tb_goods (gid,gname,gtype,gdescribe,gprice,gphoto,gcreatetime,mcid) values(goodid.nextval,?,?,?,?,?,sysdate,?)";
        index = template.update(str, goods.getGoodName(), goods.getGoodType(), goods.getGoodDescribe(), goods.getGoodPrice(), goods.getGoodPhoto(), goods.getMerchantsId());
        return index;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> queryAllGoodsByMcid(int mcid) {
        String str = "select * from (select tb1.*,tb2.gtypename from tb_goods tb1 left join tb_goodtype tb2 on tb1.gtype=tb2.gtype) goods where goods.mcid=?";
        List<Map<String, Object>> goods = template.query(str, new Object[]{mcid}, new RowMapper<Map<String, Object>>() {
            @Override
            public Map<String, Object> mapRow(ResultSet resultSet, int i) throws SQLException {
                Map<String, Object> map = null;
                while (resultSet.next()) {
                    map = new HashMap<>();
                    map.put("goodid", resultSet.getInt(1));
                    map.put("goodName", resultSet.getString(2));
                    map.put("goodType", resultSet.getString(3));
                    map.put("goodDescribe", resultSet.getString(4));
                    map.put("goodPrice", resultSet.getString(5));
                    map.put("goodPhoto", resultSet.getString(6));
                    map.put("goodCreateTime", resultSet.getString(7));
                    map.put("mcid", resultSet.getInt(8));
                    map.put("goodTypeName", resultSet.getString(9));
                }
                return map;
            }
        });
        return goods;
    }

    @Override
    @Transactional(readOnly = true)
    public Goods queryGoodById(int gid) {
        String str = "select * from tb_goods where gid=?";
        Goods goods = template.query(str, new Object[]{gid}, new ResultSetExtractor<Goods>() {
            @Override
            public Goods extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                Goods goods1 = null;
                if (resultSet.next()) {
                    goods1 = new Goods();
                    goods1.setGoodId(resultSet.getInt(1));
                    goods1.setGoodName(resultSet.getString(2));
                    goods1.setGoodType(resultSet.getInt(3));
                    goods1.setGoodDescribe(resultSet.getString(4));
                    goods1.setGoodPrice(resultSet.getDouble(5));
                    goods1.setGoodPhoto(resultSet.getString(6));
                    goods1.setGoodCreateTime(resultSet.getString(7));
                    goods1.setMerchantsId(resultSet.getInt(8));
                }
                return goods1;
            }
        });
        return goods;
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public int editGoodById(Goods goods) {
        int index = -1;
        String str = "update tb_goods set gname=?,gtype=?,gdescribe=?,gprice=?,gphoto=? where gid=?";
        index = template.update(str, goods.getGoodName(), goods.getGoodType(), goods.getGoodDescribe(), goods.getGoodPrice(), goods.getGoodPhoto(), goods.getGoodId());
        return index;
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public int deleteGood(int gid) {
        int index = -1;
        String str = "delete from tb_goods where gid=?";
        index = template.update(str, gid);
        return index;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> queryAllGoods() {
        String str = "select * from (select tb1.*,tb2.mcname from tb_goods tb1 left join tb_merchants tb2 on tb1.mcid=tb2.mcid) goods";
        List<Map<String, Object>> goods = template.query(str, new RowMapper<Map<String, Object>>() {
            @Override
            public Map<String, Object> mapRow(ResultSet resultSet, int i) throws SQLException {
                Map<String, Object> map = null;
                if (resultSet.next()) {
                    map = new HashMap<>();
                    map.put("goodid", resultSet.getInt(1));
                    map.put("goodName", resultSet.getString(2));
                    map.put("goodType", resultSet.getString(3));
                    map.put("goodDescribe", resultSet.getString(4));
                    map.put("goodPrice", resultSet.getString(5));
                    map.put("goodPhoto", resultSet.getString(6));
                    map.put("goodCreateTime", resultSet.getString(7));
                    map.put("merchantsId", resultSet.getInt(8));
                    map.put("merchantsName", resultSet.getString(9));
                }
                return map;
            }
            });
        return goods;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> queryGoodMerchantsByGoodId(int gid) {
        String str = "select * from (select tb1.*,tb2.mcname from tb_goods tb1 left join tb_merchants tb2 on tb1.mcid=tb2.mcid) goods where goods.gid=?";
        Map<String, Object> map =template.query(str, new Object[]{gid}, new ResultSetExtractor<Map<String, Object>>() {
            @Override
            public Map<String, Object> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                Map<String, Object> map1 = null;
                if (resultSet.next()) {
                    map1 = new HashMap<>();
                    map1.put("goodid", resultSet.getInt(1));
                    map1.put("goodName", resultSet.getString(2));
                    map1.put("goodType", resultSet.getString(3));
                    map1.put("goodDescribe", resultSet.getString(4));
                    map1.put("goodPrice", resultSet.getString(5));
                    map1.put("goodPhoto", resultSet.getString(6));
                    map1.put("goodCreateTime", resultSet.getString(7));
                    map1.put("merchantsId", resultSet.getInt(8));
                    map1.put("merchantsName", resultSet.getString(9));
                }
                return map1;
            }
        });
        return map;
    }
}
