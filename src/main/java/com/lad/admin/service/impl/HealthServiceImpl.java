package com.lad.admin.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lad.admin.infor.dao.HealthDao;
import com.lad.admin.infor.model.HealthBo;
import com.lad.admin.service.IHealthService;
import com.mongodb.WriteResult;

/**
 * 功能描述：
 * Copyright: Copyright (c) 2018
 * Version: 1.0
 * Time:2018/3/19
 */
@Service("healthService")
public class HealthServiceImpl implements IHealthService {

    @Autowired
    private HealthDao healthDao;

    @Override
    public HealthBo findById(String id) {
        return healthDao.findById(id);
    }

    @Override
    public WriteResult deleteById(String id) {
        return healthDao.deleteById(id);
    }

    @Override
    public WriteResult deleteByIds(String... ids) {
        return healthDao.batchDeleteByIds(ids);
    }

    @Override
    public HealthBo insert(HealthBo healthBo) {
        //初始化APP端查询需要的信息
        healthBo.setLoadTime(new Date());
        healthBo.setShareNum(0);
        healthBo.setThumpsubNum(0);
        healthBo.setCollectNum(0);
        healthBo.setCommnetNum(0);
        return healthDao.insert(healthBo);
    }

    @Override
    public WriteResult saveByParams(String inforid, Map<String, Object> values) {
        return healthDao.saveByParams(inforid, values);
    }

    @Override
    public void updateModule(String oldModule, String newModule) {
         healthDao.updateModule(oldModule, newModule);
    }

    @Override
    public List<HealthBo> findByModuleClassName(String module, String className, int page, int limit) {
        return healthDao.findByModuleClassName(module, className, page, limit);
    }

    @Override
    public List<HealthBo> findByKeyword(String keyword, int page, int limit) {
        return healthDao.findByKeyword(keyword, page, limit);
    }

    @Override
    public List<HealthBo> findByVIPLevel(int level, int page, int limit) {
        return healthDao.findByVIPLevel(level, page, limit);
    }

    @Override
    public List<HealthBo> findAll(int page, int limit) {
        return healthDao.findAll(page, limit);
    }
}
