package com.lad.admin.service.impl;

import com.lad.admin.infor.dao.SecurityDao;
import com.lad.admin.infor.model.SecurityBo;
import com.lad.admin.service.ISecuritySerivce;
import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 功能描述：
 * Copyright: Copyright (c) 2018
 * Version: 1.0
 * Time:2018/3/22
 */
@Service("securitySerivce")
public class SecurityServiceImpl implements ISecuritySerivce {

    @Autowired
    private SecurityDao securityDao;

    @Override
    public SecurityBo findById(String id) {
        return securityDao.findById(id);
    }

    @Override
    public WriteResult deleteById(String id) {
        return securityDao.deleteById(id);
    }

    @Override
    public WriteResult deleteByIds(String... ids) {
        return securityDao.batchDeleteByIds(ids);
    }

    @Override
    public SecurityBo insert(SecurityBo securityBo) {
        //初始化APP端查询需要的信息
        securityBo.setLoadTime(new Date());
        securityBo.setShareNum(0);
        securityBo.setThumpsubNum(0);
        securityBo.setCollectNum(0);
        securityBo.setCommnetNum(0);
        return securityDao.insert(securityBo);
    }

    @Override
    public void updateCity(String oldCity, String newCity) {
        securityDao.updateCity(oldCity, newCity);
    }

    @Override
    public void updateModule(String oldModule, String newModule) {
        securityDao.updateModule(oldModule, newModule);
    }

    @Override
    public WriteResult saveByParams(String inforid, Map<String, Object> values) {
        return securityDao.saveByParams(inforid, values);
    }

    @Override
    public List<SecurityBo> findByNewType(String newsType, int page, int limit) {
        return securityDao.findByNewType(newsType, page, limit);
    }

    @Override
    public List<SecurityBo> findByCity(String city, int page, int limit) {
        return securityDao.findByCity(city, page, limit);
    }

    @Override
    public List<SecurityBo> findByKeyword(String keyword, int page, int limit) {
        return securityDao.findByKeyword(keyword, page, limit);
    }

    @Override
    public List<SecurityBo> findByVIPLevel(int level, int page, int limit) {
        return securityDao.findByVIPLevel(level, page, limit);
    }

    @Override
    public List<SecurityBo> findAll(int page, int limit) {
        return securityDao.findAll(page, limit);
    }
}
