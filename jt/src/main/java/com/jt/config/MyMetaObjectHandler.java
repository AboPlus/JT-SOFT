package com.jt.config;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component  //交给Spring容器管理，无色无味的注解
public class MyMetaObjectHandler implements MetaObjectHandler {
    // 新增时自动填充
    @Override
    public void insertFill(MetaObject metaObject) {
        // this表示当前对象的引用(MyMetaObjectHandler对象)
        Date date = new Date();
        // "created"和"updated"是BasePojo中被注解@TableField(fill = xxx)标识的字段名
        // date是声明的当前时间
        // metaObject是传递过来的参数
        this.setFieldValByName("created", date, metaObject);
        this.setFieldValByName("updated", date, metaObject);
    }

    // 更新时自动填充
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updated", new Date(), metaObject);
    }
}
