package com.lad.admin.dao;

import com.lad.admin.model.UserBo;
import com.mongodb.WriteResult;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功能描述：
 * Version: 1.0
 * Time:2017/7/7
 */
@Repository("userBoDao")
public class UserBoDao extends BaseDao<UserBo> {

    public UserBo getUser(String id){
        return super.findById(id);
    }
    
    public WriteResult deleteById(String id){
        return deleteById(id);
    }

    public List<UserBo> findAll(){
        return finaAll();
    }
    

}
