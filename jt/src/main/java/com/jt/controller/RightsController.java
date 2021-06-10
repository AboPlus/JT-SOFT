package com.jt.controller;

import com.jt.service.RightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/rights")
public class RightsController {

    @Autowired
    private RightsService rightsService;
}
