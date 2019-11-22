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
import java.util.concurrent.TimeUnit;

/**
 * 登录拦截器
 */
@Component("orderLoginInterceptor")
public class OrderLoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private SSOServiceI ssoService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Value("${user.ticket}")
    private String userTicket;

    @Value("${ego.portal.url}")
    private String portalUrl;

    /**
     * 登录拦截
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        // 获取 ticket 票据信息
        String ticket = CookieUtil.getCookieValue(request, "userTicket");
        // 票据不存在，跳转前台门户系统登录页面，登录成功回跳订单系统
        if (null == ticket || ticket.length() <= 0) {
            response.sendRedirect(portalUrl + "login/?redirectUrl=" +
                    request.getRequestURL());
            return false;
        }
        // 票据存在，验证用户信息
        Admin admin = ssoService.validata(ticket);
        // 用户不存在，跳转前台门户系统登录页面，登录成功回跳订单系统
        if (null == admin) {
            response.sendRedirect(portalUrl + "login/?redirectUrl=" +
                    request.getRequestURL());
            return false;
        }
        // 用户存在，将用户信息存入 session
        request.getSession().setAttribute("user", admin);
        // 用户存在，免登陆，重新设置 redis 失效时间
        redisTemplate.expire(userTicket + ticket, 30, TimeUnit.MINUTES);
        return true;
    }

}
