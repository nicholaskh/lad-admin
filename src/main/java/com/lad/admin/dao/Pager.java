package com.lad.admin.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;

/**
 * 功能描述：
 * Version: 1.0
 * Time:2017/7/9
 */
public class Pager<T> implements Pageable, Serializable {

    // 当前页
    private int pageNamber = 1;
    // 当前页面条数
    private int pageSize= 10;
    // 排序条件
    private Sort sort;

    /**
     * 总的记录数
     */
    private long total;
    /**
     * 分页的数据
     */
    private List<T> datas;

    @Override
    public int getPageNumber() {
        return pageNamber;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public int getOffset() {
        return (getPageNumber() - 1) * getPageSize();
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    public void setPageNamber(int pageNamber) {
        this.pageNamber = pageNamber;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    @Override
    public Pageable next() {
        return null;
    }

    @Override
    public Pageable previousOrFirst() {
        return null;
    }

    @Override
    public Pageable first() {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return pageNamber > 0;
    }
}
