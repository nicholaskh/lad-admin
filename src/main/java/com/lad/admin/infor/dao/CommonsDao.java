package com.lad.admin.infor.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.lad.admin.infor.model.InforClassesBo;
import com.lad.admin.infor.model.ResultBo;
import com.lad.admin.utils.CommUtils;
import com.lad.admin.vo.SearchVo;
import com.mongodb.WriteResult;

@Repository("commonDao")
public class CommonsDao extends InforBaseDao<InforClassesBo> {

	@Autowired
	@Qualifier("inforMongo")
	private MongoTemplate inforMongoTemplate;

	// 查找所有分类
	public List<InforClassesBo> findClasses(Integer level, Integer type) {

		Query query = new Query();
		Criteria criteria = null;
		if (type == 0) {
			criteria = Criteria.where("level").in(level);

		} else {
			criteria = new Criteria();
			criteria.andOperator(Criteria.where("level").is(level), Criteria.where("type").is(type));

		}

		query.addCriteria(criteria);

		List<InforClassesBo> find = inforMongoTemplate.find(query, getClz(), "commons");

		return find;
	}

	// 根据数据库查找资源列表
	public List<String> findSource(String collection) {
		return inforMongoTemplate.getCollection(collection).distinct("source");
	}

	public List<ResultBo> searchList(SearchVo searchVo) {
		Integer page = searchVo.getPage();
		Integer limit = searchVo.getLimit();
		Integer type = searchVo.getType();
		String keyword = searchVo.getKeyword()==""?null:searchVo.getKeyword();
		String className = searchVo.getClassName()==""?null:searchVo.getClassName();
		String source = searchVo.getSource()==""?null:searchVo.getSource();
		List<ResultBo> list = new ArrayList<ResultBo>();
		
		if (type != null) {
			Map<String, String> map = CommUtils.selectCollection(type);
			String collectionName = map.get("collectionName");
			Query query = new Query();
			Criteria criteria = new Criteria();
			
			if (keyword != null && className != null && source != null) {
				// 都不为null
				Pattern pattern = Pattern.compile("^.*" + keyword + ".*$", Pattern.CASE_INSENSITIVE);
				criteria.andOperator(Criteria.where("title").regex(pattern), Criteria.where("className").is(className),
						Criteria.where("source").is(source));
			}
			if (keyword == null && className == null && source == null) {
				// 都为null,既不设置条件

			}
			if (keyword == null && className != null && source != null) {
				// 一个为null -- keyword
				criteria.andOperator(Criteria.where("className").is(className), Criteria.where("source").is(source));
			}
			if (keyword != null && className == null && source != null) {
				// 一个为null -- className
				Pattern pattern = Pattern.compile("^.*" + keyword + ".*$", Pattern.CASE_INSENSITIVE);
				criteria.andOperator(Criteria.where("title").regex(pattern), Criteria.where("source").is(source));
			}
			if (keyword != null && className != null && source == null) {
				// 一个为null -- source
				Pattern pattern = Pattern.compile("^.*" + keyword + ".*$", Pattern.CASE_INSENSITIVE);
				criteria.andOperator(Criteria.where("title").regex(pattern), Criteria.where("className").is(className));
			}

			if (keyword != null && className == null && source == null) {
				// 两个为null -- 一个不为null keyword
				Pattern pattern = Pattern.compile("^.*" + keyword + ".*$", Pattern.CASE_INSENSITIVE);
				criteria.andOperator(Criteria.where("title").regex(pattern));
			}
			if (keyword == null && className != null && source == null) {
				// 两个为null -- 一个不为null className
				criteria.andOperator(Criteria.where("className").is(className));
			}
			if (keyword == null && className == null && source != null) {
				// 两个为null -- 一个不为null source
				criteria.andOperator(Criteria.where("source").is(source));
			}

			query.addCriteria(criteria);
			query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "_id")));
			query.skip((page - 1) * limit);
			query.limit(limit);
			list = inforMongoTemplate.find(query, ResultBo.class, collectionName);
		} else {
			if (keyword != null) {
				Query query = new Query();
							
				Pattern pattern = Pattern.compile("^.*" + keyword + ".*$", Pattern.CASE_INSENSITIVE);
				Criteria criteria = Criteria.where("title").regex(pattern);	
				query.addCriteria(criteria);
				query.skip((page - 1) * limit);
				query.limit(limit);
				
				Set<String> collectionNames = inforMongoTemplate.getCollectionNames();
				list = new ArrayList<ResultBo>();
				Iterator<String> iterator = collectionNames.iterator();
				while(iterator.hasNext()&&list.size()<limit){
					List<ResultBo> find = inforMongoTemplate.find(query, ResultBo.class, iterator.next());
					list.addAll(find);
				}
			}
			// 如果keyword为null,则无任何他条件
			/*if(keyword == null){
				
			}*/
		}
		return list;
	}

	public ResultBo getDesById(String id, String collection,Class clazz) {
		Query query = new Query();
		Criteria criteria = Criteria.where("_id").is(id);
		query.addCriteria(criteria);
		ResultBo findOne = (ResultBo) inforMongoTemplate.findOne(query, clazz);
		return findOne;
	}


	public void insert(ResultBo resultBo, String collectionName) {
		inforMongoTemplate.insert(resultBo, collectionName);
		
	}

	public WriteResult saveByParams(String inforid, Map<String, Object> values, Class clazz) {
        Query query = new Query();
        query.addCriteria(new Criteria("_id").is(inforid));
        Update update = new Update();
        values.forEach((key, value) -> {
            update.set(key, value);
        });
        return inforMongoTemplate.updateFirst(query, update, clazz);
	}

	public WriteResult deleteById(String inforid, Class clazz) {
		WriteResult remove = inforMongoTemplate.remove(new Query(new Criteria("_id").is(inforid)), clazz);
		return remove;
	}

	public WriteResult deleteByIds(String[] inforidArr, Class clazz) {
		return inforMongoTemplate.remove(new Query(new Criteria("_id").in(inforidArr)), clazz);
	}

	public InforClassesBo findClass(Integer type) {
		Query query = new Query();
		Criteria criteria = new Criteria();
		criteria.andOperator(Criteria.where("level").is(1),Criteria.where("type").is(type));
		query.addCriteria(criteria);
		InforClassesBo findOne = inforMongoTemplate.findOne(query, InforClassesBo.class, "commons");
		return findOne;
	}

}
