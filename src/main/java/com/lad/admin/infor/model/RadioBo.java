package com.lad.admin.infor.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * 功能描述：广播
 * Copyright: Copyright (c) 2017
 * Version: 1.0
 * Time:2017/9/29
 */
@Setter
@Getter
@ToString
@Document(collection = "broadcast")
public class RadioBo extends ResultBo implements Serializable {



    private String sourceUrl;

    private String play_times;

    private String intro;

    private String broadcast_url;

    private int random_num;

    private String edition;

    private int visitNum;

    private int shareNum;

    private int commnetNum;

    private int thumpsubNum;

    private int collectNum;

    private Date loadTime = new Date();
}
