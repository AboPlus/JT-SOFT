package com.jt.controller;

import com.jt.pojo.Item;
import com.jt.service.ItemService;
import com.jt.vo.PageResult;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    /**
     *   需求分析：商品列表展现
     * - 请求路径: /item/getItemList?query=&pageNum=1&pageSize=10
     * - 请求类型: get
     * - 请求参数: 使用pageResult对象接收
     * - 返回值结果:SysResult(data)
     */
    @GetMapping("/getItemList")
    public SysResult getItemList(PageResult pageResult){
        pageResult = itemService.getItemList(pageResult);
        return SysResult.success(pageResult);
    }

    /**
     *   需求分析：商品状态修改
     * - 请求路径: /item/updateItemStatus
     * - 请求类型: put
     * - 请求参数: 使用对象接收
     * - 返回值结果:SysResult
     */
    @PutMapping("/updateItemStatus")
    public SysResult updateItemStatus(@RequestBody Item item){
        Boolean flag = itemService.updateItemStatus(item);
        return flag?SysResult.success():SysResult.fail();
    }

    /**
     *   需求分析：商品数据删除
     * - 请求路径: /item/deleteItemById
     * - 请求类型: delete
     * - 请求参数:id
     * - 返回值结果:SysResult
     */
    @DeleteMapping("/deleteItemById")
    public SysResult deleteItemById(Integer id){
        Boolean flag = itemService.deleteItemById(id);
        return flag ? SysResult.success() : SysResult.fail();
    }
}
