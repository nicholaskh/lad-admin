package com.lad.admin.service;

import com.lad.admin.infor.model.SecurityBo;
import com.mongodb.WriteResult;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 功能描述：
 * Copyright: Copyright (c) 2018
 * Version: 1.0
 * Time:2018/3/22
 */
public interface ISecuritySerivce extends IBaseService<SecurityBo> {

    /**
     * 更新City
     * @param oldModule
     * @param newModule
     */
    public void updateCity(String oldCity, String newCity);


    List<SecurityBo> findByNewType(String newsType, int page, int limit);
    

    List<SecurityBo> findByCity(String city,  int page, int limit);


}
