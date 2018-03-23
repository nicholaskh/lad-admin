package com.lad.admin.infor.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * 功能描述：
 * Copyright: Copyright (c) 2017
 * Version: 1.0
 * Time:2017/9/27
 */
@Setter
@Getter
@ToString
@Document(collection = "video")
public class VideoBo implements Serializable {

    @Id
    private String id;

    private String sourceUrl;

    private String title;

    private String source;

    private String url;

    private String poster;

    private String module;

    private String className;

    private int num;

    private int visitNum;

    private int shareNum;

    private int commnetNum;

    private int thumpsubNum;

    private int collectNum;

    //合集第一条信息展示
    private String firstUrl;

    private String firstId;

    private int firstShare;

    private int firstComment;

    private int firstThump;

    private Date loadTime = new Date();

}
