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

    /**
     *   业务需求：修改商品分类状态
     * - 请求路径: /itemCat/status/{id}/{status}
     * - 请求类型: put
     * - 请求参数:
     * - 返回值: SysResult对象
     */
    @PutMapping("/status/{id}/{status}")
    public SysResult updateStatus(ItemCat itemCat){
        Boolean flag = itemCatService.updateStatus(itemCat);
        return flag?SysResult.success():SysResult.fail();
    }

    /**
     *   需求分析：商品分类新增
     * - 请求路径: /itemCat/saveItemCat
     * - 请求类型: post
     * - 请求参数: 表单数据
     * - 返回值: SysResult对象
     */
    @PostMapping("/saveItemCat")
    public SysResult saveItemCat(@RequestBody ItemCat itemCat){
        Boolean flag = itemCatService.saveItemCat(itemCat);
        return flag?SysResult.success():SysResult.fail();
    }

    /**
     *   需求分析：商品分类修改
     * - 请求路径: /itemCat/updateItemCat
     * - 请求类型: put
     * - 请求参数: 表单数据 ItemCat对象
     * - 返回值: SysResult对象
     */
    @PutMapping("/updateItemCat")
    public SysResult updateItemCat(@RequestBody ItemCat itemCat){
        Boolean flag = itemCatService.updateItemCat(itemCat);
        return flag?SysResult.success():SysResult.fail();
    }

    /**
     *   需求分析：商品分类删除
     * - 请求路径: /itemCat/deleteItemCat
     * - 请求类型: delete
     * - 业务描述: 当删除节点为父级时,应该删除自身和所有的子节点
     * - 请求参数:id level
     * - 返回值结果 SysResult对象
     */
    @DeleteMapping("/deleteItemCat")
    public SysResult deleteItemCat(ItemCat itemCat){
        Boolean flag = itemCatService.deleteItemCat(itemCat);
        return flag?SysResult.success():SysResult.fail();
    }
}
