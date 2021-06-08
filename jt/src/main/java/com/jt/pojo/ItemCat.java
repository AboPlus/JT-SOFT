package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author 刘昱江
 * 时间 2021/3/26
 */
@TableName("item_cat")
@Data
@Accessors(chain = true)
public class ItemCat extends BasePojo{

    @TableId(type = IdType.AUTO)
    private Integer id;         //定义主键
    private Integer parentId;   //定义父级菜单
    private String name;        //分类名称
    private Boolean status;     //分类状态 0 停用 1 正常
    private Integer level;      //商品分类等级  1 2 3
    @TableField(exist = false)
    private List<ItemCat> children;
}
