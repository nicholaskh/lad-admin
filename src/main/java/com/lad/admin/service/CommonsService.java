package com.lad.admin.service;

import java.util.List;
import java.util.Map;

import com.lad.admin.infor.model.InforClassesBo;
import com.lad.admin.infor.model.ResultBo;
import com.lad.admin.vo.SearchVo;
import com.mongodb.WriteResult;

public interface CommonsService extends IBaseService<InforClassesBo> {
	
	List<InforClassesBo> findClasses(Integer level,Integer type);

	List<String> findSource(String collection);

	List<ResultBo> searchList(SearchVo searchBo);

	ResultBo getDesById(String id, String collection, Class clazz);

	void insert(ResultBo resultBo, String collectionName);

	WriteResult saveByParams(String inforid, Map<String, Object> params, Class clazz);


	WriteResult deleteByIds(String[] inforidArr, Class clazz);

	WriteResult deleteById(String inforid, Class clazz);

//	void insertlevel2Date();
}
