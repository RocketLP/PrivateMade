
package com.pm.www.service;

import com.pm.www.domain.Merchants;

import java.util.List;
import java.util.Map;

public interface MerchantsService {

    /**
     * 账号查找
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
     * 查询待审核商家
     */

    List<Map<String, Object>> queryNoCheckedMerchants();


    /**
     * id查询商家名
     */

    String queryMerchantsNameById(int mcid);
}

