package com.ego.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转Controller
 */
@Controller
public class PageController {

    /**
     * rest页面跳转
     * 例如：welcome  跳转welcome.jsp
     * 例如：index    跳转index.jsp
     * @param page
     * @return
     */
    @RequestMapping("/{page}")
    public String page(@PathVariable String page) {
        return page;
    }

    /**
     * 精确匹配登录请求
     */
    @RequestMapping("/login")
    public String login(Model model, String redirectUrl) {
        model.addAttribute("redirectUrl", redirectUrl);
        return "login";
    }

}
