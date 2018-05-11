package com.lad.admin.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lad.admin.infor.model.InforClassesBo;
import com.lad.admin.infor.model.ResultBo;
import com.lad.admin.service.CommonsService;
import com.lad.admin.utils.CommUtils;
import com.lad.admin.vo.InsertVo;
import com.lad.admin.vo.SearchVo;
import com.mongodb.WriteResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 功能描述： Copyright: Copyright (c) 2018 Version: 1.0 Time:2018/3/20
 */
@RestController
@RequestMapping("/infor")
@Api("资讯编辑Controller相关api")
@SuppressWarnings("all")
public class InforController extends BaseController {
	
	@Autowired
	private CommonsService commonsService;
	
	@ApiOperation(value = "查找分类列表或资讯来源", notes = "查找分类或资讯来源")
	@GetMapping(value={"/classes-search/{level}/{type}","/classes-search"})
	public String findSecClasses(
			@PathVariable(required=false) Integer level,
			@PathVariable(required=false) Integer type) {
		// level:2,请求二级分类;3,请求资源位来源
		List list = new ArrayList();
		if(level == null ){
			level=1;
			type=0;
			list = commonsService.findClasses(level, type);
		}else if(level == 2){
			list = commonsService.findClasses(level, type);
		}else if(level == 3){
			Map<String, String> map = CommUtils.selectCollection(type);
			String collectionName = map.get(CommUtils.COLLECTION_NAME);
			list = commonsService.findSource(collectionName);
		}else{
			list.add("请求参数不正确");
		}		
		String json = JSONArray.toJSONString(list);
		return json;
	}



	@ApiOperation(value = "查询资讯列表", notes = "依据条件查询资讯列表")
	@PostMapping("/news-search")
	public String searchList(
			@RequestBody @ApiParam(name = "searchVo", value = "封装前端请求参数的实体", required = true) SearchVo searchVo) {
		
		List<ResultBo> list = new ArrayList<ResultBo>();
		if(searchVo.getPage() != null && searchVo.getLimit()!=null){
			list = commonsService.searchList(searchVo);
		}else{
			return "请设置分页参数";
		}
		String json = JSONArray.toJSONString(list);
		return json;
	}
	
	@ApiOperation(value = "根据id查找咨询详情", notes = "根据id查找咨询详情,需要传入资讯id及所属一级分类的type")
	@GetMapping("/description-search/{id}/{type}")
	public String getDesById(@PathVariable String id,@PathVariable Integer type){
		Map<String, String> map = CommUtils.selectCollection(type);
		String collectionName = map.get(CommUtils.COLLECTION_NAME);
		Class<?> clazz = null;
		try {
			clazz = Class.forName(map.get(CommUtils.ENTITY_NAME));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		ResultBo result = commonsService.getDesById(id,collectionName,clazz);
		String json = JSONObject.toJSONString(result);
		return json;
	}
	
	@ApiOperation(value = "添加资讯", notes = "添加资讯")
	@PostMapping("/add")
	public String insertNews(
			@RequestBody @ApiParam(name = "InsertVo", value = "资讯实体类", required = true) InsertVo insertVo) {
		if(insertVo.getType() == null){
			return setErrorResp(-1, "请选择分类");
		}
		Map<String, String> map = CommUtils.selectCollection(insertVo.getType());
		String entityName = map.get(CommUtils.ENTITY_NAME);
		String collectionName = map.get(CommUtils.COLLECTION_NAME);
		ResultBo resultBo = null;
		try {
			resultBo = (ResultBo) Class.forName(entityName).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(resultBo);
		BeanUtils.copyProperties(insertVo, resultBo);
		InforClassesBo inforClassesBo = commonsService.findClass(insertVo.getType());
		resultBo.setModule(inforClassesBo.getName());
		System.out.println(resultBo);
		commonsService.insert(resultBo,collectionName);
		return setSuccessResp("inforid", resultBo.getId());
	}
	
	@ApiOperation(value = "修改资讯", notes = "修改信息")
	@PutMapping("/update")
	public String update(
			@RequestBody @ApiParam(name = "InsertVo", value = "资讯实体类，只传入需要修改的对象及inforid", required = true) InsertVo insertVo) {
		if (insertVo == null || StringUtils.isEmpty(insertVo.getInforid())) {
			return setErrorResp(-1, "资讯inforid为空");
		}
		if (StringUtils.isEmpty(insertVo.getType())) {
			return setErrorResp(-1, "请选择分类");
		}
		
		JSONObject jsonObject = (JSONObject) JSON.toJSON(insertVo);
		Iterator<Map.Entry<String, Object>> iterator = jsonObject.entrySet().iterator();
		Map<String, Object> params = new LinkedHashMap<>();
		while (iterator.hasNext()) {
			Map.Entry<String, Object> entry = iterator.next();
			if (entry.getValue() != null) {
				params.put(entry.getKey(), entry.getValue());
			}
		}
		if (params.size() > 0) {
			Map<String, String> map = CommUtils.selectCollection(insertVo.getType());
			String string = map.get(CommUtils.ENTITY_NAME);
			Class clazz=null;
			try {
				clazz = Class.forName(string);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			commonsService.saveByParams(insertVo.getInforid(), params,clazz);
		}
		return setSuccessResp("inforid", insertVo.getInforid());
	}
	
	@ApiOperation(value = "删除指定资讯")
	@ApiImplicitParam(name = "inforid", value = "资讯ID", required = true, paramType = "path", dataType = "string")
	@DeleteMapping("/delete/{type}/{inforid}")
	public String delete(@PathVariable Integer type,@PathVariable String inforid) {
		
		Map<String, String> map = CommUtils.selectCollection(type);
		String string = map.get(CommUtils.ENTITY_NAME);
		Class clazz=null;
		try {
			clazz = Class.forName(string);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		WriteResult result = commonsService.deleteById(inforid,clazz);
		if (result.getN() == 0) {
			return setErrorResp(-1, "删除失败");
		}
		return setErrorResp(0, "删除成功");
		
	}

	@ApiOperation(value = "删除指定资讯")
	@ApiImplicitParam(name = "inforids", value = "资讯ID，多个以逗号隔开", required = true, paramType = "path", dataType = "string")
	@DeleteMapping("/batchDelete/{type}/{inforids}")
	public String batchDelete(@PathVariable Integer type,@PathVariable String inforids) {
		if (StringUtils.isEmpty(inforids)) {
			return setErrorResp(-1, "删除资讯ID为空");
		}
		Map<String, String> map = CommUtils.selectCollection(type);
		String string = map.get(CommUtils.ENTITY_NAME);
		Class clazz=null;
		try {
			clazz = Class.forName(string);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String[] inforidArr = CommUtils.getSplitValues(inforids);
		WriteResult result = commonsService.deleteByIds(inforidArr,clazz);
		if (result.isUpdateOfExisting()) {
			return COM_RESP;
		}
		return setErrorResp(-1, "删除失败");
	}
}
