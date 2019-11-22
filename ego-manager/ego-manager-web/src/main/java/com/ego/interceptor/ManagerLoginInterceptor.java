package com.ego.interceptor;

import com.ego.pojo.Admin;
import com.ego.service.SSOServiceI;
import com.ego.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 登录拦截器
 */
@Component("managerLoginInterceptor")
public class ManagerLoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private SSOServiceI ssoService;

    @Value("${user.ticket}")
    private String userTicket;

    /**
     * 登录拦截
     */
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws IOException {
        //获取票据信息
        String ticket = CookieUtil.getCookieValue(request,"userTicket");
        //票据不存在，跳转登录页面
        if (null == ticket || ticket.length() <= 0) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
        //票据存在，验证用户信息
        Admin admin = ssoService.validata(ticket);
        //用户不存在，跳转登录页面
        if (null == admin) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
        //用户存在，将用户信息存入session
        request.setAttribute("user",admin);
        //用户不存在，免登陆，重新设置Redis失效时间
        redisTemplate.expire(userTicket + ticket,30, TimeUnit.MINUTES);
        return true;
    }

}
