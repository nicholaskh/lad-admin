package com.lad.admin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 功能描述：
 * Copyright: Copyright (c) 2018
 * Version: 1.0
 * Time:2018/3/19
 */
@Setter
@Getter
@ToString
@ApiModel(value="radioVo",description="广播资讯对象")
public class RadioVo implements Serializable {

    @ApiModelProperty(value="资讯id", name="inforid")
    private String inforid;

    @ApiModelProperty(value="资讯来源网址")
    private String sourceUrl;

    @ApiModelProperty(value="资讯标题")
    private String title;

    @ApiModelProperty(value="资讯一级分类")
    private String module;

    @ApiModelProperty(value="资讯二级分类")
    private String className;

    @ApiModelProperty(value="资讯来源")
    private String source;

    @ApiModelProperty(value="广播资讯简介")
    private String intro;

    @ApiModelProperty(value="广播播放url")
    private String broadcast_url;

    @ApiModelProperty(value="广播资讯集数")
    private String edition;

    @ApiModelProperty(value="资讯采集时间")
    private Date loadTime;

    @ApiModelProperty(value="资讯级别")
    private Integer inforLevel;

}
