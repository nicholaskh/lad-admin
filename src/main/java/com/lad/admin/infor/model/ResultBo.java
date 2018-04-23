package com.lad.admin.infor.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Document(collection = "result")
public class ResultBo implements Serializable {

	@Id
	private String id;

	private String module;

	private String className;
	
	private String source;
	
	private String title;
	
	private static final int status = 1;
}
