package com.jack.admin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("index")
public class MyController {

    @RequestMapping
    public Object index() {
        return "halo";
    }
}
