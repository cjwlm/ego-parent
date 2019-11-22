package com.ego;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @see(功能介绍) : SpringDataRedis测试类
 * @version(版本号) : 1.0
 * @author(创建人) : Dylan
 * @since : JDK 1.8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-*.xml")
public class SpringDataRedisTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 测试生成redis自增key
     */
    @Test
    public void testGetIncrement() {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong("order:increment", redisTemplate.getConnectionFactory());
        Long increment = entityIdCounter.getAndIncrement();
        System.out.println(increment);
    }

}
