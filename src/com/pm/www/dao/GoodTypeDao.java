
package com.pm.www.dao;

import java.util.List;
import java.util.Map;

public interface GoodTypeDao {

    /**
     * 查询所有商品种类
     */

    List<Map<String, Object>> queryAllGoodType();


    /**
     * 添加商品种类
     */

    int addGoodType(String goodTypeName);
}

