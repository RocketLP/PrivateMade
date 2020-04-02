package com.pm.www.service.impl;

import com.pm.www.dao.GoodsDao;
import com.pm.www.dao.impl.GoodsDaoImpl;
import com.pm.www.domain.Goods;
import com.pm.www.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsDaoImpl goodsDao;
    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,readOnly = false)
    public int addGood(Goods goods) {
        return goodsDao.addGood(goods);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> queryAllGoodsByMcid(int mcid) {
        return goodsDao.queryAllGoodsByMcid(mcid);
    }

    @Override
    @Transactional(readOnly = true)
    public Goods queryGoodById(int gid) {
        return goodsDao.queryGoodById(gid);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,readOnly = false)
    public int editGoodById(Goods goods) {
        return goodsDao.editGoodById(goods);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,readOnly = false)
    public int deleteGood(int gid) {
        return goodsDao.deleteGood(gid);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> queryAllGoods() {
        return goodsDao.queryAllGoods();
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> queryGoodMerchantsByGoodId(int gid) {
        return goodsDao.queryGoodMerchantsByGoodId(gid);
    }

}
