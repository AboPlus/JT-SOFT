package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 刘昱江
 * 时间 2021/3/31
 */
//定义商品分类参数
@TableName("item_cat_param")
@Data
@Accessors(chain = true)
public class ItemCatParam extends BasePojo{
    @TableId(type = IdType.AUTO)
    private Integer paramId;    //分类参数ID
    private String paramName;   //分类参数名称
    private Integer itemCatId;  //商品分类id
    private Integer paramType;  //参数类型  1动态参数   2.静态属性
    private String paramVals;   //参数值
}
