package com.ego.service;

import com.ego.mapper.AdminMapper;
import com.ego.pojo.Admin;
import com.ego.pojo.AdminExample;
import com.ego.util.JsonUtil;
import com.ego.util.Md5Util;
import com.ego.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * 单点登录服务实现
 */
public class SSOServiceImpl implements SSOServiceI {

    private static Logger logger = LoggerFactory.getLogger(SSOServiceImpl.class);

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Value("${user.ticket}")
    private String userTicket;

    /**
     * 登录成功，返回ticket票据信息
     * @param admin
     * @return
     */
    @Override
    public String login(Admin admin) {
        //校验参数的合法性
        String username = admin.getUserName().trim();
        String password = admin.getPassword().trim();

        //用户名是否存在
        if (null == username || username.length() <= 0) {
            logger.error("用户名为空！");
            return null;
        }

        //密码是否存在
        if (null == password || password.length() <= 0) {
            logger.error("密码为空！");
            return null;
        }

        //创建example对象
        AdminExample example = new AdminExample();
        //创建查询对象并设置查询参数
        example.createCriteria().andUserNameEqualTo(username);
        //查询
        List<Admin> adminList = adminMapper.selectByExample(example);

        //用户是否存在
        if (CollectionUtils.isEmpty(adminList) || adminList.size() <= 0) {
            logger.error("该用户名无用户信息，用户名为：" +username);
        }

        //是否存在多个用户
        if (adminList.size() > 1) {
            logger.error("该用户名存在多名用户，用户名为：" + username);
            return null;
        }

        //校验密码
        Admin a = adminList.get(0);
        if (!a.getPassword().equals(Md5Util.getMd5WithSalt(password,a.getEcSalt()))) {
            logger.error("密码输入错误！");
            return null;
        }

        //登录成功，生成ticket票据，Redis存储ticket-admin
        //生成ticket票据
        String ticket = UUIDUtil.getUUID();
        //存储Redis 设置30分钟失效时间
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(userTicket + ticket, JsonUtil.object2JsonStr(a),30, TimeUnit.MINUTES);
        return ticket;
    }

    /**
     * 验证成功， 返回用户信息
     * @param ticket
     * @return
     */
    @Override
    public Admin validata(String ticket) {
        if (null == ticket || ticket.length() <= 0) {
            logger.error("票据信息不存在");
            return null;
        }
        //根据票据信息查询用户信息
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String adminJson = valueOperations.get(userTicket + ticket);
        if (null == adminJson || adminJson.length() <= 0) {
            logger.error("票据无用户信息， 票据为： " + ticket);
            return null;
        }
        return JsonUtil.jsonStr2Object(adminJson,Admin.class);
    }

    /**
     * 安全退出
     * @param ticket
     */
    @Override
    public void logout(String ticket) {
        if (null == ticket || ticket.length() <= 0) {
            return;
        }
        //根据票据信息查询用户信息
        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
        String adminJson = valueOperations.get(userTicket + ticket);
        if (null == adminJson || adminJson.length() <= 0) {
            return;
        }
        //票据存在用户信息，清除用户信息
        redisTemplate.delete(userTicket + ticket);
    }
}
