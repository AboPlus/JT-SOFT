package com.jt.controller;

import com.jt.pojo.Item;
import com.jt.service.ItemService;
import com.jt.vo.ItemVO;
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

    /**
     *   业务需求：商品新增业务接口
     * - 请求路径: http://localhost:8091/item/saveItem
     * - 请求类型: post
     * - 前端传递参数分析
     * - 请求参数: 使用ItemVO对象接收
     * - ImageVO参数详解:
     *      - Item对象
     *      - itemDesc对象
     *          - 为了降低商品提交代码的耦合性,将大字段信息详情,采用ItemDesc对象进行封装
     * - 返回值：SysResult对象
     */
    @PostMapping("/saveItem")
    public SysResult saveItem(@RequestBody ItemVO itemVO){
        itemService.saveItem(itemVO);
        return SysResult.success();
    }
}
