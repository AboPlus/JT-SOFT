package com.jt.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)        //参数太多不写构造方法
public class PageResult {
    private String query;       //用户查询条件,根据用户名查询
    private Integer pageNum;    //分页的页数
    private Integer PageSize;   //每页几条记录
    private Long total;         //查询的总记录数
    private Object rows;        //分页查询的结果
}
