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
 * 时间 2021/3/3
 */
@TableName
@Data
@Accessors(chain = true)
public class Role extends BasePojo{

    @TableId(type = IdType.AUTO)
    private Integer roleId;     //角色ID
    private String roleName;    //角色名称
    private String roleDuty;    //角色描述
    private Boolean roleStatus; //角色状态
    private Integer sortNum;    //角色排序号

    @TableField(exist = false)
    private List<Rights> rightsList; //获取当前角色的权限信息
}
