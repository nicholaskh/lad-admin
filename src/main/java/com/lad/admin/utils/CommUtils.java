package com.lad.admin.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.yaml.snakeyaml.Yaml;

import com.lad.admin.controller.InforController;

/**
 * 功能描述：
 * Copyright: Copyright (c) 2018
 * Version: 1.0
 * Time:2018/3/20
 */
public class CommUtils {
	public static final String COLLECTION_NAME = "collectionName";
	public static final String ENTITY_NAME = "entity";
    /**
     * 根据逗号将数据列表打散
     * @param ids
     * @return
     */
    public static String[] getSplitValues(String ids){
        String[] idsArr;
        if (ids.indexOf(',') > -1) {
            idsArr = ids.split(",");
        } else {
            idsArr = new String[]{ids};
        }
        return idsArr;
    }
    
    
    /**
     * 根据集合参数获取集合名
     * @param Integer type
     * @return String collectionName 
     */
	public static Map<String,String> selectCollection(Integer type) {
		Map<String,String> map = new HashMap<String,String>();
		String collectionName = null;
		String entity = null;
		try {
			Yaml yaml = new Yaml();
			InputStream url = InforController.class.getResourceAsStream("/application.yaml");
			if (url != null) {
				Map<String, List<HashMap<String, Object>>> obj = (Map) yaml.load(url);
				for (Entry<String, List<HashMap<String, Object>>> iterable_element : obj.entrySet()) {
					if ("collection".equals(iterable_element.getKey())) {
						List<HashMap<String, Object>> value = iterable_element.getValue();
						for (HashMap<String, Object> hashMap : value) {


							if ((Integer) hashMap.get("type") == type) {
								collectionName = (String) hashMap.get("collectionName");
								entity = (String) hashMap.get("class");
							}
						}
					}

				}

			}
			map.put(COLLECTION_NAME, collectionName);
			map.put(ENTITY_NAME, entity);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
    
    /**
     * 向数据库插入二级分类列表
     * @param 
     * @return void
     */
	/*
	 * public void insertlevel2Date(){ List distinct =
	 * inforMongoTemplate.getCollection("video").distinct("className"); for
	 * (Object object : distinct) { InforClassesBo level2 = new
	 * InforClassesBo();
	 * 
	 * level2.setName(object.toString()); level2.setType(4); level2.setLevel(2);
	 * level2.setCreateTime(new Date()); level2.setDeleted(0);
	 * level2.setUpdateuid("59c26cf631f0a51f8c9d2e46");
	 * System.out.println(level2.getName()+"   "+level2.getType()+"   "
	 * +level2.getLevel()+"   "+level2.getCreateTime()+"   "
	 * +level2.getUpdateuid()+"   "+level2.getDeleted());
	 * inforMongoTemplate.insert(level2,"commons"); }
	 * 
	 * }
	 */

}
