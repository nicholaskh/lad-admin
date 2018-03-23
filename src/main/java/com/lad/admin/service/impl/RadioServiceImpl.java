package com.lad.admin.service.impl;

import com.lad.admin.infor.dao.RadioDao;
import com.lad.admin.infor.model.RadioBo;
import com.lad.admin.service.IBaseService;
import com.lad.admin.service.IRadioService;
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
@Service("radioService")
public class RadioServiceImpl implements IRadioService {

    @Autowired
    private RadioDao radioDao;

    @Override
    public RadioBo findById(String id) {
        return radioDao.findById(id);
    }

    @Override
    public WriteResult deleteById(String id) {
        return radioDao.deleteById(id);
    }

    @Override
    public List<RadioBo> findByModuleClassName(String module, String className, int page, int limit) {
        return radioDao.findByModuleClassName(module, className, page, limit);
    }

    @Override
    public WriteResult deleteByIds(String... ids) {
        return radioDao.batchDeleteByIds(ids);
    }

    @Override
    public List<RadioBo> findModules() {
        return radioDao.findModules();
    }

    @Override
    public RadioBo insert(RadioBo radioBo) {
        //初始化APP端查询需要的信息
        radioBo.setLoadTime(new Date());
        radioBo.setShareNum(0);
        radioBo.setThumpsubNum(0);
        radioBo.setCollectNum(0);
        radioBo.setCommnetNum(0);
        return radioDao.insert(radioBo);
    }

    @Override
    public List<RadioBo> findClassByModule(String module) {
        return radioDao.findClassByModule(module);
    }

    @Override
    public void updateModule(String oldModule, String newModule) {
        radioDao.updateModule(oldModule, newModule);
    }

    @Override
    public WriteResult saveByParams(String inforid, Map<String, Object> values) {
        return radioDao.saveByParams(inforid, values);
    }

    @Override
    public List<RadioBo> findByKeyword(String keyword, int page, int limit) {
        return radioDao.findByKeyword(keyword, page, limit);
    }

    @Override
    public List<RadioBo> findByVIPLevel(int level, int page, int limit) {
        return radioDao.findByVIPLevel(level, page, limit);
    }

    @Override
    public List<RadioBo> findAll(int page, int limit) {
        return radioDao.findAll(page, limit);
    }
}
