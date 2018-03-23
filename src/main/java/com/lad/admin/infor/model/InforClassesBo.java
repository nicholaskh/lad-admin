package com.lad.admin.infor.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * 功能描述：资讯分类表
 * Copyright: Copyright (c) 2018
 * Version: 1.0
 * Time:2018/3/19
 */
@Setter
@Getter
@ToString
@Document(collection = "inforClasses")
public class InforClassesBo implements Serializable {

    public static final int HEALTH = 1;

    public static final int SECURITY = 2;

    public static final int RAIDO = 3;

    public static final int VIDEO = 4;

    @Id
    private String id;
    //分类名称
    private String name;
    //分类级别， 1表示一级， 2表示二级分类
    private int level;
    //分类类型， 1 健康，2 安防，3 广播，4 视频
    private int type;
    //0 未删除  1已删除
    private int deleted;

    private Date createTime = new Date();

    private String updateuid;
    
}
