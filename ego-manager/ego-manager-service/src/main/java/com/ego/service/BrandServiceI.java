package com.ego.service;

import com.ego.pojo.Brand;

import java.util.List;

/**
 * 品牌Service
 */
public interface BrandServiceI {

    /**
     * 查询品牌列表
     */
    List<Brand> selectBrandList();

}
