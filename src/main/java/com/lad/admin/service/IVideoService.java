package com.lad.admin.service;

import com.lad.admin.infor.model.RadioBo;
import com.lad.admin.infor.model.VideoBo;

import java.util.List;

/**
 * 功能描述：
 * Copyright: Copyright (c) 2018
 * Version: 1.0
 * Time:2018/3/22
 */
public interface IVideoService extends IBaseService<VideoBo> {

    public List<VideoBo> findByModuleClassName(String module, String className, int page, int limit);

    public List<VideoBo> findModules();

    public List<VideoBo> findClassByModule(String module);

}
