package com.lad.admin.service;

import com.lad.admin.infor.model.HealthBo;
import com.mongodb.WriteResult;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 功能描述：
 * Copyright: Copyright (c) 2018
 * Version: 1.0
 * Time:2018/3/19
 */
public interface IHealthService extends IBaseService<HealthBo> {

    List<HealthBo> findByModuleClassName(String module, String className,int page, int limit);

}
