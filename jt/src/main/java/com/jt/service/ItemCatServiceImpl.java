package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemCatServiceImpl implements ItemCatService{
    @Autowired
    private ItemCatMapper itemCatMapper;

    /**
     * type共有三种结果 ———— 1、2、3
     */
    Integer parentId = 0;
    @Override
    public List<ItemCat> findItemCatList(Integer type) {
        // 递归实现
        QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", parentId);
        List<ItemCat> itemCats = itemCatMapper.selectList(queryWrapper);
        for (ItemCat itemCat : itemCats){
            if (itemCat.getLevel() == type) {
                break;
            }
            parentId = itemCat.getId();
            itemCat.setChildren(findItemCatList(type));
        }
        parentId = 0;
        return itemCats;
    }
}
