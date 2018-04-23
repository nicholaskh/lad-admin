package com.lad.admin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lad.admin.infor.dao.CommonsDao;
import com.lad.admin.infor.model.InforClassesBo;
import com.lad.admin.infor.model.ResultBo;
import com.lad.admin.service.CommonsService;
import com.lad.admin.vo.SearchVo;
import com.mongodb.WriteResult;

@Service("CommonService")
public class CommonsServiceImpl implements CommonsService {

	@Autowired
	private CommonsDao commonDao;

	@Override
	public List<InforClassesBo> findClasses(Integer level, Integer type) {

		return commonDao.findClasses(level, type);
	}

	@Override
	public List<String> findSource(String collection) {
		// TODO Auto-generated method stub
		return commonDao.findSource(collection);
	}
	
	@Override
	public List<ResultBo> searchList(SearchVo searchVo) {
		// TODO Auto-generated method stub
		return commonDao.searchList(searchVo);
	}

	@Override
	public ResultBo getDesById(String id, String collection,Class clazz ) {	
		return commonDao.getDesById(id,collection,clazz);
	}

	@Override
	public void insert(ResultBo resultBo,String collectionName) {
		// TODO Auto-generated method stub
		commonDao.insert(resultBo,collectionName);
	}
	
	@Override
	public WriteResult saveByParams(String inforid, Map<String, Object> params, Class clazz) {
		// TODO Auto-generated method stub
		return commonDao.saveByParams(inforid,params,clazz);
	}
	
	@Override
	public WriteResult deleteById(String inforid, Class clazz) {
		// TODO Auto-generated method stub
		return commonDao.deleteById(inforid, clazz);
	}

	@Override
	public WriteResult deleteByIds(String[] inforidArr, Class clazz) {
		// TODO Auto-generated method stub
		return commonDao.deleteByIds(inforidArr, clazz);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 插入二级分类
	/*
	 * @Override public void insertlevel2Date() { // TODO Auto-generated method
	 * stub commonDao.insertlevel2Date(); }
	 */

	@Override
	public InforClassesBo findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WriteResult deleteById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WriteResult deleteByIds(String... ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InforClassesBo insert(InforClassesBo t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateModule(String oldModule, String newModule) {
		// TODO Auto-generated method stub

	}

	@Override
	public WriteResult saveByParams(String inforid, Map<String, Object> values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InforClassesBo> findByKeyword(String keyword, int page, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InforClassesBo> findByVIPLevel(int level, int page, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InforClassesBo> findAll(int page, int limit) {
		// TODO Auto-generated method stub
		return null;
	}













}
