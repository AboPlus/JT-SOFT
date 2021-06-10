package com.jt.controller;

import com.jt.pojo.Rights;
import com.jt.service.RightsService;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/rights")
public class RightsController {

    @Autowired
    private RightsService rightsService;

    /**
     * 测试权限数据
     */
    @GetMapping("/list")
    public List<Rights> list(){
        return rightsService.list();
    }

    /**
     * 查询左侧权限列表数据，要求查询2级列表数据
     * 请求路径 /rights/getRightsList
     * 请求类型 GET
     * 请求参数 无
     * 响应数据 SysResult对象
     */
    @GetMapping("/getRightsList")
    public SysResult getRightsList(){
        List<Rights> list = rightsService.getRightsList();
        return SysResult.success(list);
    }
}
