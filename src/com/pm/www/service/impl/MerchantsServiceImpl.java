package com.pm.www.service.impl;

import com.pm.www.dao.MerchantsDao;
import com.pm.www.dao.impl.MerchantsDaoImpl;
import com.pm.www.domain.Merchants;
import com.pm.www.service.MerchantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
@Service
public class MerchantsServiceImpl implements MerchantsService {
    @Autowired
    MerchantsDaoImpl merchantsDao;
    @Override
    @Transactional(readOnly = true)
    public Merchants queryByMcName(String mcName) {
        return merchantsDao.queryByMcName(mcName);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,readOnly = false)
    public int mcRegister(Merchants merchants) {
        return merchantsDao.mcRegister(merchants);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,readOnly = false)
    public List<Map<String, Object>> queryAllMerchants() {
        return merchantsDao.queryAllMerchants();
    }

    @Override
    @Transactional(readOnly = true)
    public int reject(int mcid) {
        return merchantsDao.reject(mcid);
    }

    @Override
    @Transactional(readOnly = true)
    public int pass(int mcid) {
        return merchantsDao.pass(mcid);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,readOnly = false)
    public List<Map<String, Object>> queryNoCheckedMerchants() {
        return merchantsDao.queryNoCheckedMerchants();
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,readOnly = false)
    public String queryMerchantsNameById(int mcid) {
        return merchantsDao.queryMerchantsNameById(mcid);
    }
}
