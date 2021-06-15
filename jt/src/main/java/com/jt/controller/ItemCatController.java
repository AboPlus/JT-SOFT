package com.jt.controller;

import com.jt.mapper.ItemCatMapper;
import com.jt.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/itemCat")
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;
}
