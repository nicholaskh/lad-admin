package com.lad.admin.infor.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;

/**
 * 功能描述： 咨询
 * Copyright: Copyright (c) 2017
 * Version: 1.0
 * Time:2017/7/31
 */
@Setter
@Getter
@Document(collection = "health")
@ToString
public class HealthBo extends ResultBo implements Serializable {
    
    // 消息来源链接
    private String sourceUrl;

    private int classNum;

    private LinkedList<String> imageUrls;

    private String time;

    private String text;

    private Date loadTime = new Date();

    private int num;
    //阅读
    private int visitNum;
    //分享转发
    private int shareNum;
    //评论
    private int commnetNum;
    //点赞
    private int thumpsubNum;
    //收藏
    private int collectNum;

}
