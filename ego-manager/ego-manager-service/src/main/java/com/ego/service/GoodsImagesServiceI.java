package com.ego.service;

import com.ego.pojo.GoodsImages;
import com.ego.result.BaseResult;

/**
 * 商品相册Service
 */
public interface GoodsImagesServiceI {

    /**
     * 商品相册保存
     */
    BaseResult goodsImagesSave(GoodsImages goodsImages);
}
