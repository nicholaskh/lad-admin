package com.lad.admin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * 功能描述：
 * Copyright: Copyright (c) 2018
 * Version: 1.0
 * Time:2018/3/19
 */
@Setter
@Getter
@ToString
@ApiModel(value="videoVo",description="视频资讯对象")
public class VideoVo implements Serializable {

    @ApiModelProperty(value="资讯id",name="inforid")
    private String inforid;

    @ApiModelProperty(value="资讯来源网址")
    private String sourceUrl;

    @ApiModelProperty(value="资讯标题")
    private String title;

    @ApiModelProperty(value="资讯来源")
    private String source;

    @ApiModelProperty(value="视频资讯播放url")
    private String url;

    @ApiModelProperty(value="视频资讯缩略图地址")
    private String poster;

    @ApiModelProperty(value="资讯一级分类")
    private String module;

    @ApiModelProperty(value="资讯二级分类")
    private String className;

}
