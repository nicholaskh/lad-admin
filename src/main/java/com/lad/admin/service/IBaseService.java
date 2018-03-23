package com.lad.admin.service;

import com.lad.admin.infor.model.HealthBo;
import com.lad.admin.infor.model.RadioBo;
import com.mongodb.WriteResult;

import java.util.List;
import java.util.Map;

/**
 * 功能描述：
 * Copyright: Copyright (c) 2018
 * Company: travelsky
 * Author: esdong
 * Version: 1.0
 * Time:2018/3/22
 */
public interface IBaseService<T> {

    /**
     * 根据ID查找
     * @param id
     * @return
     */
    T findById(String id);

    /**
     * 删除
     * @param id
     * @return
     */
    WriteResult deleteById(String id);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    WriteResult deleteByIds(String... ids);

    /**
     * 插入
     * @param t
     * @return
     */
    T insert(T t);


    /**
     * 更新model
     * @param oldModule
     * @param newModule
     */
    void updateModule(String oldModule, String newModule);

    /**
     * 根据参数内容更新
     * @param inforid
     * @param values
     * @return
     */
    WriteResult saveByParams(String inforid, Map<String, Object> values);


    /**
     * 根据关键字查找
     * @param keyword
     * @param page
     * @param limit
     * @return
     */
    List<T> findByKeyword(String keyword, int page, int limit);

    /**
     * 根据vip等级查找
     * @param level
     * @param page
     * @param limit
     * @return
     */
    List<T> findByVIPLevel(int level, int page, int limit);

    /**
     * 查找所有
     * @param page
     * @param limit
     * @return
     */
    List<T> findAll(int page, int limit);

}
