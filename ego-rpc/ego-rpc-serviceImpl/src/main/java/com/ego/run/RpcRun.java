package com.ego.run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RpcRun {

    private final static Logger logger = LoggerFactory.getLogger(RpcRun.class);

    public static void main(String[] args) {
        // 1.加载spring配置文件
        String config = "classpath:spring/applicationContext-*.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(config);
        // 2.启动
        context.start();
        // 3.while循环防止自动关闭
        while (true) {
            try {
                Thread.sleep(10000);
                logger.info("Dubbo服务主进程正在服务！");
            } catch (Exception e) {
                logger.error("Dubbo服务主进程异常：" + e.getMessage());
            }
        }
    }


}
