package com.pm.www.dao.impl;

import com.pm.www.dao.OrderDao;
import com.pm.www.domain.Order;
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
public class OrderDaoImpl implements OrderDao {

    @Autowired
    JdbcTemplate template;

    @Override
    public int addOrder(Order order) {
        int index = -1;
        String str = "insert into tb_order (oid,mid,gid,ototalmoney,odate,ostate,gquantity) values(orderid.nextval,?,?,?,sysdate,2,?)";
        index = template.update(str, order.getMemberId(), order.getGoodId(), order.getOrderTatolMoney(), order.getGoodQuantity());
        return index;
    }

    @Override
    public int queryOrderId(int mid) {
        String str = "select max(o.oid) from (select oid from tb_order where mid=?) o";
        String index = template.query(str, new Object[]{mid}, new ResultSetExtractor<String>() {
            @Override
            public String extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                String index1 = "";
                if (resultSet.next()) {
                    index1 = resultSet.getString(1);
                }
                return index1;
            }
        });
        return Integer.parseInt(index);
    }

    @Override
    public Map<String, Object> queryOrderById(int oid) {
        String str = "select m8.gtypename,m7.mcname,m7.gname,m7.gdescribe,m7.gprice,m7.gphoto,m7.maccount,m7.gquantity,m7.ototalmoney\n" +
                "from\n" +
                "(select m6.mcname,m5.gname,m5.gtype,m5.gdescribe,m5.gprice,m5.gphoto,m5.maccount,m5.gquantity,m5.ototalmoney from\n" +
                "(select m4.gname,m4.gtype,m4.gdescribe,m4.gprice,m4.gphoto,m4.mcid,m3.maccount,m3.mid,m3.gid,m3.gquantity,m3.ototalmoney\n" +
                "from \n" +
                "(select m2.maccount,m1.mid,m1.gid,m1.gquantity,m1.ototalmoney from \n" +
                "(select m.mid,m.gid,m.gquantity,m.ototalmoney from \n" +
                "(select mid,gid,gquantity,ototalmoney from tb_order where oid=?) m) m1,\n" +
                "tb_member m2 where m1.mid=m2.mid) m3,tb_goods m4 where m3.gid=m4.gid) m5,\n" +
                "tb_merchants m6 where m6.mcid=m5.mcid) m7,tb_goodtype m8 where m7.gtype=m8.gtype";
        Map<String, Object> orderDetail = template.query(str, new Object[]{oid}, new ResultSetExtractor<Map<String, Object>>() {
            @Override
            public Map<String, Object> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                Map<String, Object> map = null;
                if (resultSet.next()) {
                    map = new HashMap<>();
                    map.put("goodTypeName", resultSet.getString(1));
                    map.put("merchantsName", resultSet.getString(2));
                    map.put("goodName", resultSet.getString(3));
                    map.put("goodDescribe", resultSet.getString(4));
                    map.put("goodPrice", resultSet.getDouble(5));
                    map.put("goodPhoto", resultSet.getString(6));
                    map.put("memberName", resultSet.getString(7));
                    map.put("goodQuantity", resultSet.getInt(8));
                    map.put("orderTotalMoney", resultSet.getDouble(9));
                }
                return map;
            }
        });
        return orderDetail;
    }

    @Override
    public List<Map<String, Object>> queryOrdersByMerchantsId(int mcid) {
        String str = "select tb6.maccount,tb5.gid,tb5.gname,tb5.gtypename,tb5.gdescribe,tb5.gprice,tb5.gphoto,tb5.oid,\n" +
                "tb5.mid,tb5.gquantity,tb5.ototalmoney,tb5.odate,tb5.ostate from\n" +
                "(select tb3.gid,tb3.gname,tb3.gtypename,tb3.gdescribe,tb3.gprice,tb3.gphoto,tb4.oid,\n" +
                "tb4.mid,tb4.gquantity,tb4.ototalmoney,tb4.odate,tb4.ostate from\n" +
                "(select tb1.gid,tb1.gname,tb2.gtypename,tb1.gdescribe,tb1.gprice,tb1.gphoto from\n" +
                "(select gid,gname,gtype,gdescribe,gprice,gphoto from tb_goods where mcid=?) tb1,\n" +
                "tb_goodtype tb2 where tb1.gtype=tb2.gtype) tb3 right join tb_order tb4 on tb3.gid=tb4.gid\n" +
                "and tb4.ostate=2) tb5,tb_member tb6 where tb5.mid=tb6.mid and tb5.ostate=2";
        List<Map<String, Object>> ordersManager =template.query(str, new Object[]{mcid}, new RowMapper<Map<String, Object>>() {
            @Override
            public Map<String, Object> mapRow(ResultSet resultSet, int i) throws SQLException {
                Map<String, Object> map=null;
                if (resultSet.next()){
                    map=new HashMap<>();
                    map.put("memberName", resultSet.getString(1));
                    map.put("goodId", resultSet.getInt(2));
                    map.put("goodName", resultSet.getString(3));
                    map.put("goodTypeName", resultSet.getString(4));
                    map.put("goodDescribe", resultSet.getString(5));
                    map.put("goodPrice", resultSet.getString(6));
                    map.put("goodPhoto", resultSet.getString(7));
                    map.put("orderId", resultSet.getString(8));
                    map.put("memberId", resultSet.getString(9));
                    map.put("goodQuantity", resultSet.getString(10));
                    map.put("orderTotalMoney", resultSet.getString(11));
                    map.put("orderCreateTime", resultSet.getString(12));
                    map.put("orderState", resultSet.getString(13));
                }
                return map;
            }
        });
        return ordersManager;
    }

    @Override
    public int acceptOrder(int oid) {
        int index = -1;
        String str = "update tb_order set ostate=1 where oid=?";
        index=template.update(str,oid);
        return index;
    }

    @Override
    public List<Map<String, Object>> queryOrdersDoingByMerchantsId(int mcid) {
        String str = "select tb6.maccount,tb5.gid,tb5.gname,tb5.gtypename,tb5.gdescribe,tb5.gprice,tb5.gphoto,tb5.oid,\n" +
                "tb5.mid,tb5.gquantity,tb5.ototalmoney,tb5.odate,tb5.ostate from\n" +
                "(select tb3.gid,tb3.gname,tb3.gtypename,tb3.gdescribe,tb3.gprice,tb3.gphoto,tb4.oid,\n" +
                "tb4.mid,tb4.gquantity,tb4.ototalmoney,tb4.odate,tb4.ostate from\n" +
                "(select tb1.gid,tb1.gname,tb2.gtypename,tb1.gdescribe,tb1.gprice,tb1.gphoto from\n" +
                "(select gid,gname,gtype,gdescribe,gprice,gphoto from tb_goods where mcid=?) tb1,\n" +
                "tb_goodtype tb2 where tb1.gtype=tb2.gtype) tb3 right join tb_order tb4 on tb3.gid=tb4.gid\n" +
                "and tb4.ostate=1) tb5,tb_member tb6 where tb5.mid=tb6.mid and tb5.ostate=1";
        List<Map<String, Object>> ordersManager =template.query(str, new Object[]{mcid}, new RowMapper<Map<String, Object>>() {
            @Override
            public Map<String, Object> mapRow(ResultSet resultSet, int i) throws SQLException {
                Map<String, Object> map=null;
                if (resultSet.next()){
                    map = new HashMap<>();
                    map.put("memberName", resultSet.getString(1));
                    map.put("goodId", resultSet.getInt(2));
                    map.put("goodName", resultSet.getString(3));
                    map.put("goodTypeName", resultSet.getString(4));
                    map.put("goodDescribe", resultSet.getString(5));
                    map.put("goodPrice", resultSet.getString(6));
                    map.put("goodPhoto", resultSet.getString(7));
                    map.put("orderId", resultSet.getString(8));
                    map.put("memberId", resultSet.getString(9));
                    map.put("goodQuantity", resultSet.getString(10));
                    map.put("orderTotalMoney", resultSet.getString(11));
                    map.put("orderCreateTime", resultSet.getString(12));
                    map.put("orderState", resultSet.getString(13));
                }
                return map;
            }
        });
        return ordersManager;
    }

    @Override
    public int addOrderDetail(int oid, int gid) {
        int index = -1;
        String str = "insert into tb_orderdetail (oid,gid) values(?,?)";
        index=template.update(str,oid,gid);
        return index;
    }

    @Override
    public List<Map<String, Object>> queryOrderByMemberId(int mid) {
        String str = "select tb7.*,tb8.* from \n" +
                "(select tb1.*,tb2.gtype,tb2.gname,tb2.gphoto,tb2.gprice from\n" +
                " (select oid,gid,gquantity,ototalmoney,odate from tb_order where mid=? and ostate=1) tb1,tb_goods tb2 where tb1.gid=tb2.gid) tb7,(select tb5.gid,tb5.mcid,tb6.mcname from \n" +
                "(select tb3.gid,tb4.mcid from\n" +
                " (select distinct gid from tb_order where mid=? and ostate=1) tb3,tb_goods tb4 where tb3.gid=tb4.gid) tb5,tb_merchants tb6 where tb5.mcid=tb6.mcid) tb8 where tb7.gid=tb8.gid";
        List<Map<String, Object>> ordersManager =template.query(str, new Object[]{mid}, new RowMapper<Map<String, Object>>() {
            @Override
            public Map<String, Object> mapRow(ResultSet resultSet, int i) throws SQLException {
                Map<String, Object> map=null;
                if (resultSet.next()) {
                    map = new HashMap<>();
                    map.put("orderId", resultSet.getInt(1));
                    map.put("orderTotalMoney", resultSet.getInt(4));
                    map.put("orderCreateTime", resultSet.getString(5));
                    map.put("goodQuantity", resultSet.getString(3));
                    map.put("merchantsName", resultSet.getString(12));
                    map.put("goodName", resultSet.getString(7));
                    map.put("goodPrice", resultSet.getString(9));
                    map.put("goodPhoto", resultSet.getString(8));
                }
                return map;
            }
        });
        return ordersManager;
    }

    @Override
    public int confirmReceive(int oid) {
        int index = -1;
        String str = "update tb_order set ostate=0 where oid=?";
        index=template.update(str,oid);
        return index;
    }

    @Override
    public List<Map<String, Object>> queryOrdersFinishedByMerchantsId(int mcid) {
        String str = "select tb6.maccount,tb5.gid,tb5.gname,tb5.gtypename,tb5.gdescribe,tb5.gprice,tb5.gphoto,tb5.oid,\n" +
                "tb5.mid,tb5.gquantity,tb5.ototalmoney,tb5.odate,tb5.ostate from\n" +
                "(select tb3.gid,tb3.gname,tb3.gtypename,tb3.gdescribe,tb3.gprice,tb3.gphoto,tb4.oid,\n" +
                "tb4.mid,tb4.gquantity,tb4.ototalmoney,tb4.odate,tb4.ostate from\n" +
                "(select tb1.gid,tb1.gname,tb2.gtypename,tb1.gdescribe,tb1.gprice,tb1.gphoto from\n" +
                "(select gid,gname,gtype,gdescribe,gprice,gphoto from tb_goods where mcid=?) tb1,\n" +
                "tb_goodtype tb2 where tb1.gtype=tb2.gtype) tb3 right join tb_order tb4 on tb3.gid=tb4.gid\n" +
                "and tb4.ostate=0) tb5,tb_member tb6 where tb5.mid=tb6.mid and tb5.ostate=0";
        List<Map<String, Object>> ordersManager =template.query(str, new Object[]{mcid}, new RowMapper<Map<String, Object>>() {
            @Override
            public Map<String, Object> mapRow(ResultSet resultSet, int i) throws SQLException {
                Map<String, Object> map=null;
                if (resultSet.next()) {
                    map = new HashMap<>();
                    map.put("memberName", resultSet.getString(1));
                    map.put("goodId", resultSet.getInt(2));
                    map.put("goodName", resultSet.getString(3));
                    map.put("goodTypeName", resultSet.getString(4));
                    map.put("goodDescribe", resultSet.getString(5));
                    map.put("goodPrice", resultSet.getString(6));
                    map.put("goodPhoto", resultSet.getString(7));
                    map.put("orderId", resultSet.getString(8));
                    map.put("memberId", resultSet.getString(9));
                    map.put("goodQuantity", resultSet.getString(10));
                    map.put("orderTotalMoney", resultSet.getString(11));
                    map.put("orderCreateTime", resultSet.getString(12));
                    map.put("orderState", resultSet.getString(13));
                }
                return map;
            }
        });
        return ordersManager;
    }
}
