package com.ego.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户Controller
 */
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 安全退出
     *
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        return "redirect:" +
                request.getSession().getServletContext().getAttribute("portalUrl") +
                "user/logout";
    }

}
