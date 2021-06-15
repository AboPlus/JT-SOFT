package com.jt.controller;

import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import com.jt.service.ItemCatService;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/itemCat")
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    /**
     *   业务需求：查询3级商品分类信息
     * - 请求路径: /itemCat/findItemCatList
     * - 请求类型: get
     * - 请求参数: type (表示请求级别，等同于level字段)
     * - 业务说明: 查询3级分类菜单数据 要求三层结构嵌套
     * - 返回值: SysResult对象
     */
    @GetMapping("/findItemCatList/{type}")
    public SysResult findItemCatList(@PathVariable Integer type){
        // 要求实现数据的嵌套
        List<ItemCat> catList = itemCatService.findItemCatList(type);
        return SysResult.success(catList);
    }
}
