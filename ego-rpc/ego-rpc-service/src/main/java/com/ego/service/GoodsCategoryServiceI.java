package com.ego.service;

import com.ego.vo.GoodsCategoryVo;

import java.util.List;

/**
 * 商品分类service
 */
public interface GoodsCategoryServiceI {

    /**
     * 查询商品分类列表
     *
     * @return
     */
    List<GoodsCategoryVo> selectCategoryListForView();

}
