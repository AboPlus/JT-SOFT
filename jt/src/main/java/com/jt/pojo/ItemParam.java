package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.jt.vo.ItemParamVO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 刘昱江
 * 时间 2021/4/16
 */
@TableName("item_param")
@Data
@Accessors(chain = true)
public class ItemParam extends BasePojo{
    private Integer id;
    private String dynamicArgs;
    private String staticArgs;
    //为了实现参数封装添加的数据
    @TableField(exist = false)
    private ItemParamVO[] dynamicArray;
    @TableField(exist = false)
    private ItemParamVO[] staticArray;
}
