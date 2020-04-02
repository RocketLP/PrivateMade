package com.pm.www.service.impl;

import com.pm.www.dao.GoodTypeDao;
import com.pm.www.domain.GoodType;
import com.pm.www.service.GoodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
@Service
public class GoodTypeServiceImpl implements GoodTypeService {
    @Autowired
    GoodTypeDao goodTypeDao;
    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> queryAllGoodType() {
        return goodTypeDao.queryAllGoodType();
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,readOnly = false)
    public int addGoodType(String goodTypeName) {
        return goodTypeDao.addGoodType(goodTypeName);
    }
}
