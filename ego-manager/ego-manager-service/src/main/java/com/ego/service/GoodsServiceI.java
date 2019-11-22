package com.ego.service;

import com.ego.pojo.Goods;
import com.ego.result.BaseResult;

/**
 * 商品Service
 */
public interface GoodsServiceI {

    /**
     * 保存商品
     */
    BaseResult productSave(Goods goods);

    /**
     * 分页查询
     */
    BaseResult productListForPage(Goods goods,Integer pageNum,Integer pageSize);
}
