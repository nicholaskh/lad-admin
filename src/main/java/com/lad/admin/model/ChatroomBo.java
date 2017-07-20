package com.lad.admin.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;

@Document(collection = "chatroom")
public class ChatroomBo extends BaseBo {

	private String name;
	private HashSet<String> users = new HashSet<String>();
	//1 表示一对一聊天室，2表示群聊，3表示面对面建群
	private int type;
	private String userid;
	private String friendid;
	private int seq;
	private int expire = 1;

	private double[] position;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HashSet<String> getUsers() {
		return users;
	}

	public void setUsers(HashSet<String> users) {
		this.users = users;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getFriendid() {
		return friendid;
	}

	public void setFriendid(String friendid) {
		this.friendid = friendid;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getExpire() {
		return expire;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}

	public double[] getPosition() {
		return position;
	}

	public void setPosition(double[] postion) {
		this.position = postion;
	}
}
