package com.ego.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局拦截器
 */
@Component("orderCommonInterceptor")
public class OrderCommonInterceptor extends HandlerInterceptorAdapter {

    @Value("${ego.portal.url}")
    private String portalUrl;

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
        String portalURL = (String) request.getSession()
                .getServletContext().getAttribute("orderUrl");
        // 不存在订单url 写入
        if (null == portalURL)
            request.getSession().getServletContext().setAttribute("portalUrl", portalUrl);
        return true;
    }

}
