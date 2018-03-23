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
@ApiModel(value="securityVo",description="安防资讯对象")
public class SecurityVo implements Serializable {

    @ApiModelProperty(value="资讯id")
    private String inforid;

    @ApiModelProperty(value="资讯级别")
    private Integer inforLevel;

    @ApiModelProperty(value="安防资讯标题")
    private String title;

    @ApiModelProperty(value="安防资讯分类")
    private String newsType;

    @ApiModelProperty(value="安防资讯发布时间")
    private String time;

    @ApiModelProperty(value="安防资讯内容")
    private String text;

    @ApiModelProperty(value="安防资讯来源")
    private String city;

    @ApiModelProperty(value="资讯来源地址")
    private String sourceUrl;

    @ApiModelProperty(value="资讯采集时间")
    private Date loadTime;

}
