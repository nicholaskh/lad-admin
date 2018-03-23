package com.lad.admin.service;

import com.lad.admin.infor.model.RadioBo;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.regex.Pattern;

/**
 * 功能描述：
 * Copyright: Copyright (c) 2018
 * Version: 1.0
 * Time:2018/3/22
 */
public interface IRadioService extends IBaseService<RadioBo> {

    public List<RadioBo> findByModuleClassName(String module, String className, int page, int limit);

    public List<RadioBo> findModules();

    public List<RadioBo> findClassByModule(String module);

}
