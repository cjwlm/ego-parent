package com.ego.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CookieServiceI {

    /**
     * 获取Cookie
     */
    String getCookie(HttpServletRequest request, String cookieName);

    /**
     * 设置Cookie
     */
    void setCookie(HttpServletRequest request, HttpServletResponse response,
                   String cookieName, String cookieValue);

    /**
     * 删除cookie
     */
    void deleteCookie(HttpServletRequest request, HttpServletResponse response,String cookieName);
}
