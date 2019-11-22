package com.ego.service;

import com.ego.util.JsonUtil;
import com.ego.vo.GoodsCategoryVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/*
 * @RunWith就是一个运行器
 * @RunWith(JUnit4.class)就是指用JUnit4来运行
 * @RunWith(SpringJUnit4ClassRunner.class)，让测试运行于Spring测试环境
 */
@RunWith(SpringJUnit4ClassRunner.class)
/*
 * @ContextConfiguration Spring整合JUnit4测试时，使用注解引入多个配置文件
 * 单个文件 @ContextConfiguration(locations = "classpath:spring/applicationContext-*.xml")
 * 多个文件 @ContextConfiguration(locations = {"classpath:spring/spring1.xml", "classpath:mybatis/config.xml"})
 */
@ContextConfiguration(locations = "classpath:spring/applicationContext-*.xml")
public class GoodsCategoryServiceITest {

    @Autowired
    private GoodsCategoryServiceI goodsCategoryService;

    @Test
    public void testSelectCategoryListForView() {
        List<GoodsCategoryVo> gcvList = goodsCategoryService.selectCategoryListForView();
        System.out.println(JsonUtil.object2JsonStr(gcvList));
    }

}
