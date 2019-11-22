package com.ego.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie Service
 */
public interface CookieServiceI {

    /**
     * 获取Cookie
     *
     * @param request
     * @param cookieName
     * @return
     */
    String getCookie(HttpServletRequest request, String cookieName);

    /**
     * 设置Cookie
     *
     * @param request
     * @param response
     * @param cookieName
     * @param cookieValue
     */
    void setCookie(HttpServletRequest request, HttpServletResponse response,
                   String cookieName, String cookieValue);

    /**
     * 删除Cookie
     *
     * @param request
     * @param response
     * @param cookieName
     */
    void deleteCookie(HttpServletRequest request, HttpServletResponse response, String cookieName);

}
