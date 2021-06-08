package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author 刘昱江
 * 时间 2021/3/3
 */
@TableName("role_rights")
@Data
@Accessors(chain = true)
public class RoleRights {
    private Integer roleId;
    private Integer rightsId;
}
