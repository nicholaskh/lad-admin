package com.lad.admin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;

/**
 * 功能描述：
 * Copyright: Copyright (c) 2018
 * Version: 1.0 
 * Time:2018/3/19
 */
@Setter
@Getter
@ToString
@ApiModel(value="healthVo",description="健康资讯对象")
public class HealthVo implements Serializable {

    @ApiModelProperty(value="资讯id",name="inforid")
    private String inforid;

    @ApiModelProperty(value="资讯一级分类")
    private String module;

    @ApiModelProperty(value="资讯二级分类")
    private String className;

    @ApiModelProperty(value="资讯标题")
    private String title;

    @ApiModelProperty(value="资讯来源")
    private String source;

    @ApiModelProperty(value="资讯来源地址")
    private String sourceUrl;

    @ApiModelProperty(value="健康资讯图片")
    private LinkedList<String> imageUrls;

    @ApiModelProperty(value="原始资讯发布时间")
    private String time;

    @ApiModelProperty(value="资讯采集时间")
    private Date loadTime;

    @ApiModelProperty(value="健康资讯内容")
    private String text;

    @ApiModelProperty(value="资讯级别")
    private Integer inforLevel = 0;

}
