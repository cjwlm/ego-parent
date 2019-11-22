package com.ego.service;

import com.ego.mapper.GoodsCategoryMapper;
import com.ego.pojo.GoodsCategory;
import com.ego.pojo.GoodsCategoryExample;
import com.ego.service.GoodsCategoryServiceI;
import com.ego.util.JsonUtil;
import com.ego.vo.GoodsCategoryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品分类service
 */
@Service
public class GoodsCategoryServiceImpl implements GoodsCategoryServiceI {

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    // 商品分类列表的Redis Key
    @Value("${goods.category.list.key}")
    private String goodsCategoryListKey;

    /**
     * 查询商品分类列表
     *
     * @return
     */
    @Override
    public List<GoodsCategoryVo> selectCategoryListForView() {
        // 查询缓存，如果缓存中存在数据，直接返回
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String gcListJson = valueOperations.get(goodsCategoryListKey);
        if (null != gcListJson && gcListJson.length() > 0)
            return JsonUtil.jsonToList(gcListJson, GoodsCategoryVo.class);

        // 缓存中不存在数据，查询关系数据库，并放入缓存
        // 创建example对象
        GoodsCategoryExample example = new GoodsCategoryExample();
        // 创建查询对象并设置查询参数
        example.createCriteria().andParentIdEqualTo((short) 0).andLevelEqualTo((byte) 1);
        // 查询一级分类
        List<GoodsCategory> gcList01 = goodsCategoryMapper.selectByExample(example);
        // 处理一级分类查询下级分类
        List<GoodsCategoryVo> gcvList01 = new ArrayList<>();
        for (GoodsCategory gc01 : gcList01) {
            // 将对象转换成vo对象
            GoodsCategoryVo gcv01 = new GoodsCategoryVo();
            BeanUtils.copyProperties(gc01, gcv01);
            // 清空查询参数(查询对象清除)
            example.clear();
            // 设置查询参数
            example.createCriteria().andParentIdEqualTo(gc01.getId()).andLevelEqualTo((byte) 2);
            // 查询二级分类
            List<GoodsCategory> gcList02 = goodsCategoryMapper.selectByExample(example);
            // 处理二级分类查询下级分类
            List<GoodsCategoryVo> gcvList02 = new ArrayList<>();
            for (GoodsCategory gc02 : gcList02) {
                // 将对象转换成vo对象
                GoodsCategoryVo gcv02 = new GoodsCategoryVo();
                BeanUtils.copyProperties(gc02, gcv02);
                // 清空查询参数
                example.clear();
                // 设置查询参数
                example.createCriteria().andParentIdEqualTo(gc02.getId()).andLevelEqualTo((byte) 3);
                // 查询三级分类
                List<GoodsCategory> gcList03 = goodsCategoryMapper.selectByExample(example);
                // 处理三级分类添加至二级分类vo对象
                List<GoodsCategoryVo> gcvList03 = new ArrayList<>();
                for (GoodsCategory gc03 : gcList03) {
                    // 将对象转换成vo对象
                    GoodsCategoryVo gcv03 = new GoodsCategoryVo();
                    BeanUtils.copyProperties(gc03, gcv03);
                    // 将对象添加至三级分类vo集合
                    gcvList03.add(gcv03);
                }
                // 添加至二级分类vo对象
                gcv02.setChildren(gcvList03);
                // 将对象添加至二级分类vo集合
                gcvList02.add(gcv02);
            }
            // 添加至一级分类vo对象
            gcv01.setChildren(gcvList02);
            // 将对象添加至一级分类vo集合
            gcvList01.add(gcv01);
        }
        // 放入缓存
        valueOperations.set(goodsCategoryListKey, JsonUtil.object2JsonStr(gcvList01));
        return gcvList01;
    }

}
