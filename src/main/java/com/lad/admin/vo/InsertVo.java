package com.lad.admin.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;

import org.springframework.data.annotation.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * 功能描述：
 * Copyright: Copyright (c) 2018
 * Version: 1.0 
 * Time:2018/3/19
 */
@Setter
@Getter
@ToString
@ApiModel(value="InsertVo",description="插入资讯实体")
public class InsertVo implements Serializable {
	
	@ApiModelProperty(value="资讯id",name="inforid")
    private String inforid;

    @ApiModelProperty(value="资讯来源网址")
    private String sourceUrl;

    @ApiModelProperty(value="采集时间")
    private Date loadTime;
    
    @ApiModelProperty(value="资讯标题")
    private String title;

    @ApiModelProperty(value="资讯来源")
    private String source;	

    @ApiModelProperty(value="资讯一级type",required=true)
    private Integer type;    
    
    @ApiModelProperty(value="资讯一级分类",required=true)
    private String module;
    

    @ApiModelProperty(value="资讯二级分类",required=true)
    private String className;
    
    @ApiModelProperty(value="资讯发布时间")
    private String time;

    @ApiModelProperty(value="资讯内容")
    private String text;
    
    @ApiModelProperty(value="资讯地址")
    private LinkedList<String> imageUrls;

    @ApiModelProperty(value="广播资讯简介")
    private String intro;

    @ApiModelProperty(value="广播播放url")
    private String broadcast_url;

    @ApiModelProperty(value="广播资讯集数")
    private String edition;

    @ApiModelProperty(value="安防资讯分类")
    private String newsType;
    
    @ApiModelProperty(value="安防资讯来源发布城市")
    private String city;

    @ApiModelProperty(value="视频资讯播放url")
    private String url;

    @ApiModelProperty(value="视频资讯缩略图地址")
    private String poster;

}
