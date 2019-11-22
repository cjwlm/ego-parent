package com.ego.service;

import com.ego.pojo.GoodsCategory;
import com.ego.vo.GoodsCategoryVo;

import java.util.List;

/**
 * 商品分类service
 */
public interface GoodsCategoryServiceI {

    /**
     * 根据parantId查询商品分类列表
     */
    List<GoodsCategory> selectCategoryList(short parentId);

    /**
     * 保存商品分类
     */
    int categorySave(GoodsCategory goodsCategory);

    /**
     * 查询商品分类列表
     */
    List<GoodsCategoryVo> selectCategoryListForView();

    /**
     * 查询所有商品分类
     */
    List<GoodsCategory> selectCategoryList();
}
