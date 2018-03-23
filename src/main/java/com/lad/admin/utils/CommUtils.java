package com.lad.admin.utils;

/**
 * 功能描述：
 * Copyright: Copyright (c) 2018
 * Version: 1.0
 * Time:2018/3/20
 */
public class CommUtils {


    /**
     * 根据逗号将数据列表打散
     * @param ids
     * @return
     */
    public static String[] getSplitValues(String ids){
        String[] idsArr;
        if (ids.indexOf(',') > -1) {
            idsArr = ids.split(",");
        } else {
            idsArr = new String[]{ids};
        }
        return idsArr;
    }

}
