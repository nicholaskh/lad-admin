package com.lad.admin.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "thumbsup")
public class ThumbsupBo extends BaseBo {

	private static final long serialVersionUID = 1L;
	private String homepage_id;
	private String owner_id;
	private String visitor_id;
	public String getHomepage_id() {
		return homepage_id;
	}
	public void setHomepage_id(String homepage_id) {
		this.homepage_id = homepage_id;
	}
	public String getOwner_id() {
		return owner_id;
	}
	public void setOwner_id(String owner_id) {
		this.owner_id = owner_id;
	}
	public String getVisitor_id() {
		return visitor_id;
	}
	public void setVisitor_id(String visitor_id) {
		this.visitor_id = visitor_id;
	}
	
}
