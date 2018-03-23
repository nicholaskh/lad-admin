package com.lad.admin.infor.dao;

import com.lad.admin.infor.model.HealthBo;
import com.mongodb.WriteResult;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
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
@Repository("healthDao")
public class HealthDao extends InforBaseDao<HealthBo> {


    public List<HealthBo> findByModuleClassName(String module, String className,int page, int limit){
        Query query = new Query();
        Criteria criteria = new Criteria();
        if (!StringUtils.isEmpty(module)) {
            Pattern pattern = Pattern.compile("^.*"+module+".*$", Pattern.CASE_INSENSITIVE);
            criteria.orOperator(new Criteria("module").regex(pattern));
        }
        query.addCriteria(criteria);
        return findByPages(query, page, limit);
    }


    public List<HealthBo> findByKeyword(String keyword, int page, int limit){
        Query query = new Query();
        Criteria criteria = new Criteria();
        Pattern pattern = Pattern.compile("^.*"+keyword+".*$", Pattern.CASE_INSENSITIVE);
        criteria.orOperator(new Criteria("title").regex(pattern));
        criteria.orOperator(new Criteria("module").regex(pattern));
        criteria.orOperator(new Criteria("className").regex(pattern));
        query.addCriteria(criteria);
        return findByPages(query, page, limit);
    }

    public List<HealthBo> findByVIPLevel(int level, int page, int limit){
        Query query = new Query(new Criteria("inforLevel").gte(level));
        return findByPages(query, page, limit);
    }

    public List<HealthBo> findAll(int page, int limit){
        return findByPages(new Query(), page, limit);
    }
}
