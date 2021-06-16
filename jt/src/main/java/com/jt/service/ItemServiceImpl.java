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

    @Override
    public PageResult getItemList(PageResult pageResult) {
        IPage<Item> page = new Page<>(pageResult.getPageNum(), pageResult.getPageSize());
        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        boolean flag = StringUtils.hasLength(pageResult.getQuery());
        queryWrapper.like(flag, "title", pageResult.getQuery());
        IPage<Item> itemIPage = itemMapper.selectPage(page, queryWrapper);
        long total = itemIPage.getTotal();
        List<Item> records = itemIPage.getRecords();
        pageResult.setTotal(total).setRows(records);
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
