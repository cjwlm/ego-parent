package com.ego.service.impl;

import com.ego.mapper.GoodsImagesMapper;
import com.ego.pojo.GoodsImages;
import com.ego.result.BaseResult;
import com.ego.service.GoodsImagesServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsImagesServiceImpl implements GoodsImagesServiceI {

    @Autowired
    private GoodsImagesMapper goodsImagesMapper;
    /**
     * 商品相册保存
     * @param goodsImages
     * @return
     */
    @Override
    public BaseResult goodsImagesSave(GoodsImages goodsImages) {
        int result = goodsImagesMapper.insertSelective(goodsImages);
        return result >0 ? BaseResult.success() : BaseResult.error();
    }
}
