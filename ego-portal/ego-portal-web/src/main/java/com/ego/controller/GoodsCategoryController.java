package com.ego.controller;

import com.ego.service.GoodsCategoryServiceI;
import com.ego.vo.GoodsCategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 商品分类Controller
 */
@Controller
@RequestMapping("/goodsCategory")
public class GoodsCategoryController {

    @Autowired
    private GoodsCategoryServiceI goodsCategoryService;// 商品分类服务

    /**
     * 查询商品分类列表
     *
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<GoodsCategoryVo> list() {
        return goodsCategoryService.selectCategoryListForView();
    }

}
