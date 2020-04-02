package com.pm.www.service;

import com.pm.www.domain.Goods;

import java.util.List;
import java.util.Map;

public interface GoodsService {

    /**
     * 添加商品
     */

    int addGood(Goods goods);


    /**
     * 查询对应商家的所有商品
     */

    List<Map<String, Object>> queryAllGoodsByMcid(int mcid);


    /**
     * 通过id查询商品
     */

    Goods queryGoodById(int gid);


    /**
     * 编辑商品信息
     */

    int editGoodById(Goods goods);


    /**
     * 删除商品信息
     */

    int deleteGood(int gid);


    /**
     * 查询所有商品
     */

    List<Map<String, Object>> queryAllGoods();


    /**
     * 联合查询单个商品的所有信息包括所属商家
     */

    Map<String, Object> queryGoodMerchantsByGoodId(int gid);

}

