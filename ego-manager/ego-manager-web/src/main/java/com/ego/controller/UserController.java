package com.ego.controller;

import com.ego.enums.BaseResultEnum;
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

/**
 * 用户Controller
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private SSOServiceI ssoService;

    @Autowired
    private CookieServiceI cookieService;

    /**
     * 用户登录
     */
    @RequestMapping("/login")
    @ResponseBody
    public BaseResult login(Admin admin, String vertify,
                            HttpServletRequest request, HttpServletResponse response) {
        BaseResult result = new BaseResult();
        //校验用户名和密码
        if (null == admin.getUserName() || "".equals(admin.getUserName())
                || null == admin.getPassword() || "".equals(admin.getPassword())) {
            result.setCode(BaseResultEnum.PASS_ERROR_03.getCode());
            result.setMessage(BaseResultEnum.PASS_ERROR_03.getMessage());
            return result;
        }
        //校验验证码是否为空
        if (null == vertify || "".equals(vertify)) {
            result.setCode(BaseResultEnum.PASS_ERROR_04.getCode());
            result.setMessage(BaseResultEnum.PASS_ERROR_04.getMessage());
            return result;
        }

        //校验验证码是否输入正确
        String capText = (String) request.getSession().getAttribute("pictureVerifyKey");
        if (!capText.equals(vertify)) {
            result.setCode(BaseResultEnum.PASS_ERROR_05.getCode());
            result.setMessage(BaseResultEnum.PASS_ERROR_05.getMessage());
            return result;
        }

        //访问认证系统进行登录，登录成功返回票据信息
        String ticket = ssoService.login(admin);
        if (null == ticket) {
            result.setCode(BaseResultEnum.PASS_ERROR_02.getCode());
            result.setMessage(BaseResultEnum.PASS_ERROR_02.getMessage());
            return result;
        }

        //将票据信息存入cookie
        cookieService.setCookie(request,response,"userTicket",ticket);
        //将用户信息存入session
        request.getSession().setAttribute("user",admin);
        return BaseResult.success();
    }

    /**
     * 安全退出
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        //获取ticket
        String ticket = cookieService.getCookie(request,"userTicket");
        //清除Redis
        ssoService.logout(ticket);
        //清除session
        request.getSession().removeAttribute("user");
        //清除cookie
        cookieService.deleteCookie(request,response,"userTicket");
        return "login";
    }

}
