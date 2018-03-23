package com.lad.admin.service.impl;

import com.lad.admin.infor.dao.RadioDao;
import com.lad.admin.infor.dao.VideoDao;
import com.lad.admin.infor.model.RadioBo;
import com.lad.admin.infor.model.VideoBo;
import com.lad.admin.service.IVideoService;
import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 功能描述：
 * Copyright: Copyright (c) 2018
 * Company: travelsky
 * Author: esdong
 * Version: 1.0
 * Time:2018/3/22
 */
@Service("videoService")
public class VideoServiceImpl implements IVideoService {

    @Autowired
    private VideoDao videoDao;

    @Override
    public VideoBo findById(String id) {
        return videoDao.findById(id);
    }

    @Override
    public WriteResult deleteById(String id) {
        return videoDao.deleteById(id);
    }

    @Override
    public List<VideoBo> findByModuleClassName(String module, String className, int page, int limit) {
        return videoDao.findByModuleClassName(module, className, page, limit);
    }

    @Override
    public WriteResult deleteByIds(String... ids) {
        return videoDao.batchDeleteByIds(ids);
    }

    @Override
    public List<VideoBo> findModules() {
        return videoDao.findModules();
    }

    @Override
    public VideoBo insert(VideoBo videoBo) {
        //初始化APP端查询需要的信息
        videoBo.setLoadTime(new Date());
        videoBo.setShareNum(0);
        videoBo.setThumpsubNum(0);
        videoBo.setCollectNum(0);
        videoBo.setCommnetNum(0);
        return videoDao.insert(videoBo);
    }

    @Override
    public List<VideoBo> findClassByModule(String module) {
        return videoDao.findClassByModule(module);
    }

    @Override
    public void updateModule(String oldModule, String newModule) {
        videoDao.updateModule(oldModule, newModule);
    }

    @Override
    public WriteResult saveByParams(String inforid, Map<String, Object> values) {
        return videoDao.saveByParams(inforid, values);
    }

    @Override
    public List<VideoBo> findByKeyword(String keyword, int page, int limit) {
        return videoDao.findByKeyword(keyword, page, limit);
    }

    @Override
    public List<VideoBo> findByVIPLevel(int level, int page, int limit) {
        return videoDao.findByVIPLevel(level, page, limit);
    }

    @Override
    public List<VideoBo> findAll(int page, int limit) {
        return videoDao.findAll(page, limit);
    }
}
