package com.lad.admin.service.impl;

import com.lad.admin.dao.Pager;
import com.lad.admin.dao.UserBoDao;
import com.lad.admin.model.UserBo;
import com.lad.admin.service.IUserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 功能描述：
 * Copyright: Copyright (c) 2017
 * Version: 1.0
 * Time:2017/7/11
 */
@Service("userService")
public class UserServiceImpl implements IUserSerivce {

    @Autowired
    private UserBoDao userBoDao;


    @Override
    public UserBo getUser(String id) {
        return userBoDao.findById(id);
    }

    @Override
    public void delete(String id) {
        UserBo userBo = userBoDao.findById(id);
        if (userBo != null) {
            userBoDao.deleteById(id);
        }
    }

    @Override
    public void betchDelete(String... ids) {
        for (String id : ids) {
            delete(id);
        }
    }

    @Override
    public List<UserBo> findAll() {
        return userBoDao.finaAll();
    }

    @Override
    public Pager<UserBo> findByPages(Pager pager, Map<String, Object> params) {
        return null;
    }
}
