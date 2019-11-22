package com.ego.service.impl;

import com.ego.mapper.BrandMapper;
import com.ego.pojo.Brand;
import com.ego.pojo.BrandExample;
import com.ego.service.BrandServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 品牌Service
 */
@Service
public class BrandServiceImpl implements BrandServiceI {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * 查询所有品牌
     * @return
     */
    @Override
    public List<Brand> selectBrandList() {
        return brandMapper.selectByExample(new BrandExample());
    }
}
