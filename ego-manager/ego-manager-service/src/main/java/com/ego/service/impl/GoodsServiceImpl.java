package com.ego.service.impl;

import com.ego.mapper.GoodsMapper;
import com.ego.pojo.Goods;
import com.ego.pojo.GoodsExample;
import com.ego.result.BaseResult;
import com.ego.service.GoodsServiceI;
import com.ego.util.JsonUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class GoodsServiceImpl implements GoodsServiceI {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 保存商品
     * @param goods
     * @return
     */
    @Override
    public BaseResult productSave(Goods goods) {
        //写操作中，清除Redis缓存数据，再次查询时新的数据将会放入缓存
        redisTemplate.delete(redisTemplate.keys("goods*"));

        //判断是否有商品详情描述信息
        if (null != goods.getGoodsContent() && goods.getGoodsContent().length()>0) {
            String goodsContent = HtmlUtils.htmlEscape(goods.getGoodsContent(),"UTF-8");
            goods.setGoodsContent(goodsContent);
        }
        int result = goodsMapper.insertSelective(goods);
        //将商品主键返回页面
        BaseResult baseResult = BaseResult.error();
        if (result > 0) {
            baseResult =BaseResult.success();
            baseResult.setMessage(String.valueOf(goods.getGoodsId()));
        }
        return baseResult;
    }

    /**
     * 分页查询
     * @param goods
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public BaseResult productListForPage(Goods goods, Integer pageNum, Integer pageSize) {
        //构建分页对象
        PageHelper.startPage(pageNum,pageSize);

        //创建查询对象
        GoodsExample example = new GoodsExample();
        GoodsExample.Criteria criteria = example.createCriteria();

        //商品列表Redis Key
        String goodsKeyArr[] =new String[]{"goods:pageNum_" + pageNum + "pageSize_" + pageSize + ":",
                "catId_:",
                "brandId_:",
                "goodsName_:"};

        //设置查询参数
        //分类参数
        if (null != goods.getCatId() && 0 != goods.getCatId()) {
            criteria.andCatIdEqualTo(goods.getCatId());
            goodsKeyArr[1] = "catId_" +goods.getCatId() + ":";
        }
        //品牌参数
        if (null != goods.getBrandId() && 0 !=goods.getBrandId()) {
            criteria.andBrandIdEqualTo(goods.getBrandId());
            goodsKeyArr[2] = "brandId_" + goods.getBrandId() + ":";
        }
        //商品名称参数
        if (null != goods.getGoodsName() && goods.getGoodsName().trim().length() >0) {
            criteria.andGoodsNameLike("%"+goods.getGoodsName()+"%");
            goodsKeyArr[3] = "goodsName_" + goods.getGoodsName() +":";
        }

        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();

        //循环拼接Redis Key
        String goodsListKey = "";
        for (String key : goodsKeyArr) {
            goodsListKey += key;
        }

        //查询缓存，如果缓存中存在数据，直接返回
        String pageInfoGoodsJson = valueOperations.get(goodsListKey);
        if (null != pageInfoGoodsJson && pageInfoGoodsJson.length() > 0) {
            return BaseResult.success(JsonUtil.jsonStr2Object(pageInfoGoodsJson, PageInfo.class));
        }

        //缓存中不存在数据，查询关系数据库，并放入缓存
        //查询商品列表设置至分页对象
        List<Goods> goodsList = goodsMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(goodsList) && goodsList.size() >0){
            PageInfo<Goods> goodsPageInfo = new PageInfo<>(goodsList);
            //放入缓存
            valueOperations.set(goodsListKey,JsonUtil.object2JsonStr(goodsPageInfo));
            return BaseResult.success(goodsPageInfo);
        } else {
            //没有，将空数据放入缓存，设置失效时间为60秒
            valueOperations.set(goodsListKey,
                    JsonUtil.object2JsonStr(new PageInfo<>(new ArrayList<Goods>())),
                    60, TimeUnit.SECONDS);
        }
        return BaseResult.error();
    }

    public static void main(String[] args) {
        String htmlEscape = HtmlUtils.htmlEscape("<p><span>测试html文本转义与反转义！</span></p>", "UTF-8");
        System.out.println(htmlEscape);
        String htmlUnescape = HtmlUtils.htmlUnescape(htmlEscape);
        System.out.println(htmlUnescape);
    }
}
