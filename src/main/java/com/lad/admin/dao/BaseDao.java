package com.lad.admin.dao;

import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 功能描述：
 * Copyright: Copyright (c) 2017
 * Version: 1.0
 * Time:2017/7/8
 */
public class BaseDao<T extends Serializable> {

    @Autowired
    private MongoTemplate mongoTemplate;

    
    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    /**
     * 创建一个Class的对象来获取泛型的class
     */
    private Class<T> clz;

    /**
     * 获取当前对象的类型
     * @return 类型
     */
    public Class<T> getClz() {
        if(clz==null) {
            //获取泛型的Class对象
            clz = ((Class<T>)
                    (((ParameterizedType)(this.getClass()
                            .getGenericSuperclass())).getActualTypeArguments()[0]));
        }
        return clz;
    }

    /**
     * 查找所有
     * @return
     */
    public List<T> finaAll(){
       return mongoTemplate.findAll(getClz());
    }

    /**
     * 按照主键查询结果
     * @param id
     * @return
     */
    public T findById(String id){
        Query query = new Query();
        query.addCriteria(new Criteria("_id").is(id));
        return mongoTemplate.findOne(query, getClz());
    }

    /**
     * 插入
     * @param t
     * @return
     */
    public T insert(T t){
        mongoTemplate.insert(t);
        return t;
    }

    /**
     * 单个更新
     * @param id 主键
     * @param params 参数
     */
    public WriteResult update(String id, Map<String, Object> params) {
        Query query = new Query();
        query.addCriteria(new Criteria("_id").is(id));
        Update update = new Update();
        if (params != null) {
            Set<Map.Entry<String, Object>> entrys = params.entrySet();
            for (Map.Entry<String, Object> entry : entrys) {
                update.set(entry.getKey(), entry.getValue());
            }
        }
        return mongoTemplate.updateFirst(query, update, getClz());
    }

    /**
     * 删除数据
     * @param id
     */
    public WriteResult deleteById(String id) {
        return mongoTemplate.remove(new Query(new Criteria("_id").is(id)), getClz());
    }

    /**
     * 注销数据
     * @param id
     */
    public WriteResult inActiveeById(String id) {
        Query query = new Query();
        query.addCriteria(new Criteria("_id").is(id));
        Update update = new Update();
        update.set("deleted", 1);
        return mongoTemplate.updateFirst(query, update, getClz());
    }

    /**
     * 根据条件查询，只适合等于条件,主键降序排列
     * @param params
     * @return
     */
    public List<T> findByItems(Map<String, Object> params) {
        Query query = new Query();
        if (params != null) {
            Set<Map.Entry<String, Object>> entrys = params.entrySet();
            for (Map.Entry<String, Object> entry : entrys) {
                query.addCriteria(new Criteria(entry.getKey()).is(entry.getValue()));
            }
        }
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "_id")));
        return mongoTemplate.find(query, getClz());
    }

    /**
     * 根据条件查询，只适合等于条件,主键降序排列
     * @param query
     * @param pager
     * @return
     */
    public Pager<T> findByPages(Query query, Pager pager) {
        query.skip((pager.getPageNumber()-1)*pager.getPageSize());
        query.limit(pager.getPageSize());
        query.addCriteria(new Criteria("deleted").is(0));
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "_id")));
        long total = mongoTemplate.count(query, getClz());
        pager.setDatas(mongoTemplate.find(query, getClz()));
        pager.setTotal(total);
        return pager;
    }

}
