package com.ego.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局拦截器
 */
@Component("portalCommonInterceptor")
public class PortalCommonInterceptor extends HandlerInterceptorAdapter {

    @Value("${ego.order.url}")
    private String orderUrl;

    /**
     * 初始化整个项目所需要的资源
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
        // 判断是否已存在订单url
        String orderURL = (String) request.getSession()
                .getServletContext().getAttribute("orderUrl");
        // 不存在订单url 写入
        if (null == orderURL)
            request.getSession().getServletContext().setAttribute("orderUrl", orderUrl);
        return true;
    }

}
