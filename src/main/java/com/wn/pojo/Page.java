package com.wn.pojo;


/*
分页，条件 */
public class Page {
    private Integer page=1;//当前页,默认
    private Integer pageSize=3;//每页所显示的行数,默认
    private Integer total;//数据总数
    private Integer pageTotal;//总页数
    private  Integer f=0;

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getF() {

        return f;
    }

    public void setF(Integer f) {
        this.f = f;
    }

    private int pageStart;//当前页的第一条数据 是第几条--当前页从第几条数据开始，(page-1)*pagesize---
                            //需要时int类型否则，无法识别0，自动变成null
    private Integer pageEnd;//当前页的最后一条数据 是第几条--当前页在第几条数据结束，(pageToday)*pagesize

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public Integer getPageEnd() {
        return pageEnd;
    }

    public void setPageEnd(Integer pageEnd) {
        this.pageEnd = pageEnd;
    }
}
