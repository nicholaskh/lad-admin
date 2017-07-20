package com.lad.admin.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 功能描述： 申请加好友或加圈子时的理由
 * Copyright: Copyright (c) 2017
 * Version: 1.0
 * Time:2017/7/5
 */

@Document(collection = "reason")
public class ReasonBo extends BaseBo {
    //申请加入的圈子
    private String circleid;
    //添加理由
    private String reason;
    //拒绝理由
    private String refues;
    //状态，0 表示申请； 1 表示通过， -1表示b拒绝
    private int status;


    public String getCircleid() {
        return circleid;
    }

    public void setCircleid(String circleid) {
        this.circleid = circleid;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRefues() {
        return refues;
    }

    public void setRefues(String refues) {
        this.refues = refues;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
