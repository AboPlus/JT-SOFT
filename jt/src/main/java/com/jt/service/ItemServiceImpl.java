package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jt.mapper.ItemMapper;
import com.jt.pojo.Item;
import com.jt.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    private ItemMapper itemMapper;

    /**
     * PageResult {query.pageNum,pageSize,rows分页数据,total} :业务数据的VO对象
     * page:MP中的分页对象
     */
    @Override
    public PageResult getItemList(PageResult pageResult) {
        IPage<Item> page = new Page<>(pageResult.getPageNum(), pageResult.getPageSize());
        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        boolean flag = StringUtils.hasLength(pageResult.getQuery());
        queryWrapper.like(flag, "title", pageResult.getQuery());
        // 经过程序的分页，其中的数据全部获取(传来的page只有两条属性，现在有了五条)
        page = itemMapper.selectPage(page, queryWrapper);
        long total = page.getTotal();
        List<Item> records = page.getRecords();
        pageResult.setTotal(total).setRows(records);//MP获取
        return pageResult;
    }

    @Override
    public Boolean updateItemStatus(Item item) {
        int rows = itemMapper.updateById(item);
        if (rows == 0) return false;
        return true;
    }

    @Override
    public Boolean deleteItemById(Integer id) {
        int rows = itemMapper.deleteById(id);
        if (rows == 0) return false;
        return true;
    }
}
