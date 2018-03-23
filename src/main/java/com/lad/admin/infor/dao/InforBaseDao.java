package com.lad.admin.infor.dao;

import com.lad.admin.dao.Pager;
import com.lad.admin.infor.model.HealthBo;
import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
 * Copyright: Copyright (c) 2018
 * Version: 1.0
 * Time:2018/3/19
 */
public class InforBaseDao<T extends Serializable> {

    @Autowired
    @Qualifier("inforMongo")
    private MongoTemplate inforMongoTemplate;


    public MongoTemplate getInforMongoTemplate() {
        return inforMongoTemplate;
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
        return inforMongoTemplate.findAll(getClz());
    }

    /**
     * 按照主键查询结果
     * @param id
     * @return
     */
    public T findById(String id){
        Query query = new Query();
        query.addCriteria(new Criteria("_id").is(id));
        return inforMongoTemplate.findOne(query, getClz());
    }

    /**
     * 插入
     * @param t
     * @return
     */
    public T insert(T t){
        inforMongoTemplate.insert(t);
        return t;
    }

    /**
     * 插入
     * @param t
     * @return
     */
    public T save(T t){
        inforMongoTemplate.save(t);
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
        return inforMongoTemplate.updateFirst(query, update, getClz());
    }

    /**
     * 删除数据
     * @param id
     */
    public WriteResult deleteById(String id) {
        return inforMongoTemplate.remove(new Query(new Criteria("_id").is(id)), getClz());
    }

    /**
     * 删除数据
     * @param id
     */
    public WriteResult batchDeleteByIds(String... ids) {
        return inforMongoTemplate.remove(new Query(new Criteria("_id").in(ids)), getClz());
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
        return inforMongoTemplate.updateFirst(query, update, getClz());
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
        return inforMongoTemplate.find(query, getClz());
    }

    /**
     * 根据条件查询，只适合等于条件,主键降序排列
     * @param query
     * @param pager
     * @return
     */
    public Pager<T> findByPages(Query query, Pager pager) {
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "_id")));
        query.skip((pager.getPageNumber()-1)*pager.getPageSize());
        query.limit(pager.getPageSize());
        long total = inforMongoTemplate.count(query, getClz());
        pager.setDatas(inforMongoTemplate.find(query, getClz()));
        pager.setTotal(total);
        return pager;
    }


    /**
     * 根据条件查询，只适合等于条件,主键降序排列
     * @param query
     * @param pager
     * @return
     */
    public List<T> findByPages(Query query, int page, int limit) {
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "_id")));
        query.skip((page-1)*limit);
        query.limit(limit);
        return inforMongoTemplate.find(query, getClz());
    }

    /**
     * 更新model
     * @param oldModule
     * @param newModule
     */
    public void updateModule(String oldModule, String newModule){
        Query query = new Query();
        query.addCriteria(new Criteria("module").is(oldModule));
        Update update = new Update();
        update.set("module", newModule);
        inforMongoTemplate.updateMulti(query, update, getClz());
    }


    /**
     * 更新二级分类名称
     * @param oldName
     * @param newName
     */
    public void updateClassName(String oldName, String newName){
        Query query = new Query();
        query.addCriteria(new Criteria("className").is(oldName));
        Update update = new Update();
        update.set("className", newName);
        inforMongoTemplate.updateMulti(query, update, getClz());
    }

    /**
     * 根据inforid修改参数信息
     * @param inforid
     * @param values
     * @return
     */
    public WriteResult saveByParams(String inforid, Map<String, Object> values){
        Query query = new Query();
        query.addCriteria(new Criteria("_id").is(inforid));
        Update update = new Update();
        values.forEach((key, value) -> {
            update.set(key, value);
        });
        return inforMongoTemplate.updateFirst(query, update, getClz());
    }

}
