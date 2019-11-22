package com.ego.service;

import com.ego.pojo.Admin;

/**
 * 单点登录服务
 */
public interface SSOServiceI {
    /**
     * 登录成功，返回ticket票据信息
     */
    String login(Admin admin);

    /**
     * 验证成功，返回用户信息
     */
    Admin validata(String ticket);

    /**
     * 安全退出
     */
    void logout(String ticket);
}
