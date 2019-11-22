package com.ego.service;

import com.ego.service.HelloServiceI;

/**
 * Hello服务实现
 */
public class HelloServiceImpl implements HelloServiceI {

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }

}
