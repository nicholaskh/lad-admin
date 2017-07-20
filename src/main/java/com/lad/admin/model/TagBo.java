package com.lad.admin.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;

@Document(collection = "tag")
public class TagBo extends BaseBo {
	
	private String userid;
	private HashSet<String> friendsIds = new HashSet<String>();
	private String name;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public HashSet<String> getFriendsIds() {
		return friendsIds;
	}
	public void setFriendsIds(HashSet<String> friendsIds) {
		this.friendsIds = friendsIds;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
