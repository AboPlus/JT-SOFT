package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 刘昱江
 * 时间 2021/4/17
 */
@Data
@Accessors(chain = true)
@TableName("report")
public class Report {
    private Integer id;
    private String name;
    private Integer count;
}
