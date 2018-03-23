package com.lad.admin.infor.dao;

import com.lad.admin.infor.model.HealthBo;
import com.lad.admin.infor.model.SecurityBo;
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
@Repository("securityDao")
public class SecurityDao extends InforBaseDao<SecurityBo> {


    /**
     * 更新model
     * @param oldModule
     * @param newModule
     */
    public void updateModule(String oldModule, String newModule){
        Query query = new Query();
        query.addCriteria(new Criteria("newsType").is(oldModule));
        Update update = new Update();
        update.set("newsType", newModule);
        getInforMongoTemplate().updateMulti(query, update, SecurityBo.class);
    }

    /**
     * 更新model
     * @param oldModule
     * @param newModule
     */
    public void updateCity(String oldCity, String newCity){
        Query query = new Query();
        query.addCriteria(new Criteria("city").is(oldCity));
        Update update = new Update();
        update.set("city", newCity);
        getInforMongoTemplate().updateMulti(query, update, SecurityBo.class);
    }


    public List<SecurityBo> findByNewType(String newsType,  int page, int limit){
        Query query = new Query();
        Pattern pattern = Pattern.compile("^.*"+newsType+".*$", Pattern.CASE_INSENSITIVE);
        query.addCriteria(new Criteria("newsType").regex(pattern));
        return findByPages(query, page, limit);
    }

    public List<SecurityBo> findByCity(String city,  int page, int limit){
        Query query = new Query();
        query.addCriteria(new Criteria("city").is(city));
        return findByPages(query, page, limit);
    }

    public List<SecurityBo> findByKeyword(String keyword, int page, int limit){
        Query query = new Query();
        Criteria criteria = new Criteria();
        Pattern pattern = Pattern.compile("^.*"+keyword+".*$", Pattern.CASE_INSENSITIVE);
        criteria.orOperator(new Criteria("title").regex(pattern));
        criteria.orOperator(new Criteria("newsType").regex(pattern));
        criteria.orOperator(new Criteria("city").regex(pattern));
        query.addCriteria(criteria);
        return findByPages(query, page, limit);
    }

    public List<SecurityBo> findByVIPLevel(int level, int page, int limit){
        Query query = new Query(new Criteria("inforLevel").gte(level));
        return findByPages(query, page, limit);
    }

    public List<SecurityBo> findAll(int page, int limit){
        return findByPages(new Query(), page, limit);
    }

}
