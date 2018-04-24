package com.lad.admin.vo;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

/**
 * 功能描述： 封装前端请求数据
 * Copyright: Copyright (c) 2018
 * Version: 1.0
 * Time:2018/4/20
 */
@Setter
@Getter
public class SearchVo implements Serializable {
	
	private Integer type;
	
	private String className;
	
	private String source;
	
	private String keyword;
	
	private Integer page;
	
	private Integer limit;
	
}
