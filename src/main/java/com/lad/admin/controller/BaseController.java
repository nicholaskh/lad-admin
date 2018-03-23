package com.lad.admin.controller;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 功能描述：
 * Copyright: Copyright (c) 2018
 * Version: 1.0
 * Time:2018/3/20
 */
public class BaseController {


    public static final String COM_RESP = "{\"ret\": 0}";


    /**
     * 单结果失败返回
     * @param param
     * @param value
     * @return
     */
    public String setFailResp(String param, String value){
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("ret", -1);
        map.put(param, value);
        return JSON.toJSONString(map);
    }


    /**
     * 单结果成功返回
     * @param param
     * @param value
     * @return
     */
    public String setSuccessResp(String param, String value){
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("ret", 0);
        map.put(param, value);
        return JSON.toJSONString(map);
    }


    public static String setErrorResp(int ret, String error) {
        HashMap<String, Object> map = new LinkedHashMap<>();
        map.put("ret", ret);
        map.put("error", error);
        return JSON.toJSONString(map);
    }

}
