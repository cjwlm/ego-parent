package com.ego.controller;

import com.ego.pojo.Admin;
import com.ego.result.BaseResult;
import com.ego.service.CookieServiceI;
import com.ego.service.SSOServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private SSOServiceI ssoService;

    @Autowired
    private CookieServiceI cookieService;

    /**
     * 用户登录
     *
     * @param admin
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public BaseResult login(Admin admin, HttpServletRequest request, HttpServletResponse response) {
        // 访问认证系统进行登录，登录成功返回票据信息
        String ticket = ssoService.login(admin);
        if (null == ticket)
            return BaseResult.error();
        // 将票据信息存入 cookie
        cookieService.setCookie(request, response, "userTicket", ticket);
        // 将用户信息存入 session
        request.getSession().setAttribute("user", admin);
        return BaseResult.success();
    }

    /**
     * 安全退出
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // 获取ticket
        String ticket = cookieService.getCookie(request, "userTicket");
        // 清除redis
        ssoService.logout(ticket);
        // 清除cookie
        cookieService.deleteCookie(request, response, "userTicket");
        // 清除session
        request.getSession().removeAttribute("user");
        return "login";
    }

}
