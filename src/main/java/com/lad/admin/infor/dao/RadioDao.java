package com.lad.admin.infor.dao;

import com.lad.admin.infor.model.RadioBo;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 功能描述：
 * Copyright: Copyright (c) 2018
 * Version: 1.0
 * Time:2018/3/22
 */
@Repository("radioDao")
public class RadioDao extends InforBaseDao<RadioBo> {


    public List<RadioBo> findByModuleClassName(String module, String className, int page, int limit){
        Query query = new Query();
        Criteria criteria = new Criteria();
        List<Criteria> criteriaList = new ArrayList<>();
        if (!StringUtils.isEmpty(module)) {
            Pattern pattern = Pattern.compile("^.*"+module+".*$", Pattern.CASE_INSENSITIVE);
            criteriaList.add(new Criteria("module").regex(pattern));
        }
        if (!StringUtils.isEmpty(className)) {
            Pattern pattern = Pattern.compile("^.*"+className+".*$", Pattern.CASE_INSENSITIVE);
            criteriaList.add(new Criteria("className").regex(pattern));
        }
        criteria.orOperator(criteriaList.toArray(new Criteria[]{}));
        query.addCriteria(criteria);
        return findByPages(query, page, limit);
    }


    public List<RadioBo> findModules() {
        ProjectionOperation project = Aggregation.project("_id","module");
        GroupOperation groupOperation = Aggregation.group("module").count().as("nums");
        Aggregation aggregation = Aggregation.newAggregation(project, groupOperation,
                Aggregation.sort(Sort.Direction.DESC, "_id"));
        AggregationResults<RadioBo> results = getInforMongoTemplate().aggregate(aggregation, "broadcast", RadioBo
                .class);
        return results != null ? results.getMappedResults() : null;
    }

    public List<RadioBo> findClassByModule(String module){
        Criteria criteria = new Criteria("module").is(module);
        MatchOperation match = Aggregation.match(criteria);
        GroupOperation groupOperation = Aggregation.group("className","source");
        Aggregation aggregation = Aggregation.newAggregation(match, groupOperation,
                Aggregation.sort(Sort.Direction.ASC, "className"));
        AggregationResults<RadioBo> results = getInforMongoTemplate().aggregate(aggregation, "broadcast", RadioBo.class);
        return results != null ? results.getMappedResults() : null;
    }


    public List<RadioBo> findByKeyword(String keyword, int page, int limit){
        Query query = new Query();
        Criteria criteria = new Criteria();
        Pattern pattern = Pattern.compile("^.*"+keyword+".*$", Pattern.CASE_INSENSITIVE);
        criteria.orOperator(new Criteria("title").regex(pattern), new Criteria("intro").regex(pattern));
        query.addCriteria(criteria);
        return findByPages(query, page, limit);
    }

    public List<RadioBo> findByVIPLevel(int level, int page, int limit){
        Query query = new Query(new Criteria("inforLevel").gte(level));
        return findByPages(query, page, limit);
    }

    public List<RadioBo> findAll(int page, int limit){
        return findByPages(new Query(), page, limit);
    }

}
