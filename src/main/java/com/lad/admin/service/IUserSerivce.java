package com.lad.admin.service;

import com.lad.admin.dao.Pager;
import com.lad.admin.model.UserBo;

import java.util.List;
import java.util.Map;

/**
 * 功能描述：
 * Copyright: Copyright (c) 2017
 * Version: 1.0
 * Time:2017/7/11
 */
public interface IUserSerivce {

    UserBo getUser(String id);


    void delete(String id);


    void betchDelete(String... ids);
    

    List<UserBo> findAll();


    Pager<UserBo> findByPages(Pager pager, Map<String, Object> params);

}
