package com.jt.service;

import com.jt.mapper.ItemCatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemCatServiceImpl implements ItemCatService{
    @Autowired
    private ItemCatMapper itemCatMapper;
}
