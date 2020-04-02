package com.pm.www.dao;

import com.pm.www.domain.Merchants;

import java.util.List;
import java.util.Map;

public interface MerchantsDao {

    /**
     * 账号查询
     */

    Merchants queryByMcName(String mcName);


    /**
     * 商家注册
     */

    int mcRegister(Merchants merchants);


    /**
     * 查询所有商家
     */

    List<Map<String, Object>> queryAllMerchants();


    /**
     * 驳回
     */

    int reject(int mcid);


    /**
     * 通过
     */

    int pass(int mcid);


    /**
     * 查询待审核商家信息
     */

    List<Map<String, Object>> queryNoCheckedMerchants();


    /**
     * id查询商家名
     */

    String queryMerchantsNameById(int mcid);
}

