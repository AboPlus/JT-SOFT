package com.jt.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 刘昱江
 * 时间 2021/4/16
 */
@Data
@Accessors(chain = true)
public class ItemParamVO {
    private Integer paramId;
    private String paramVals;
}
