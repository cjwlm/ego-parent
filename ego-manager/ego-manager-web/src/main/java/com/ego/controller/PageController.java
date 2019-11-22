package com.ego.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    /**
     * rest页面跳转
     */
    @RequestMapping("/{page}")
    public String page(@PathVariable String page) {
        return page;
    }
}
