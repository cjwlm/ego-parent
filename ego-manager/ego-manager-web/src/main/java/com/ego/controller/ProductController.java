package com.ego.controller;

import com.ego.pojo.Goods;
import com.ego.pojo.GoodsCategory;
import com.ego.pojo.GoodsImages;
import com.ego.result.BaseResult;
import com.ego.result.FileResult;
import com.ego.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private static Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private GoodsCategoryServiceI goodsCategoryService;

    @Autowired
    private BrandServiceI brandService;

    @Autowired
    private GoodsServiceI goodsService;

    @Autowired
    private GoodsImagesServiceI goodsImagesService;

    @Autowired
    private FileUploadServiceI fileUploadService;

    /**
     * 跳转商品分类列表页
     */
    @RequestMapping("/category/list")
    public String categoryList(Model model) {
        //商品分类信息
        model.addAttribute("gcvList",goodsCategoryService.selectCategoryListForView());
        return "product/category/category-list";
    }

    /**
     * 跳转商品分类新增页
     */
    @RequestMapping("/category/add")
    public String categoryAdd(Model model) {
        //查询商品分类顶级菜单
        model.addAttribute("gcList",goodsCategoryService.selectCategoryList((short) 0));
        return "product/category/category-add";
    }

    /**
     * 根据父id查询商品分类子类列表
     */
    @RequestMapping(value = "/category/{parentId}",method = RequestMethod.GET)
    @ResponseBody
    public List<GoodsCategory> categoryListByParentId(@PathVariable short parentId) {
        //查询商品分类
        return goodsCategoryService.selectCategoryList(parentId);
    }

    /**
     * 保存商品分类
     */
    @RequestMapping("/category/save")
    @ResponseBody
    public BaseResult categorySave(GoodsCategory goodsCategory) {
        int result = goodsCategoryService.categorySave(goodsCategory);
        return result > 0 ? BaseResult.success() : BaseResult.error();
    }

    /**
     * 商品列表
     */
    @RequestMapping("/list")
    public String productList(Model model){
        //返回所有商品分类
        model.addAttribute("categoryList",goodsCategoryService.selectCategoryList());
        //返回所有商品品牌
        model.addAttribute("brandList",brandService.selectBrandList());
        return "product/product-list";
    }

    /**
     * 商品列表分页查询
     */
    @RequestMapping("/listForPage")
    @ResponseBody
    public BaseResult productListForPage(Goods goods,Integer pageNum,Integer pageSize) {
        return goodsService.productListForPage(goods,pageNum,pageSize);
    }

    /**
     * 商品列表-新增商品-页面跳转
     */
    @RequestMapping("/add")
    public String productAdd(Model model) {
        //查询所有顶级分类
        model.addAttribute("gcList",goodsCategoryService.selectCategoryList((short) 0));
        //查询品牌列表
        model.addAttribute("brandList",brandService.selectBrandList());
        return "product/product-add";
    }

    /**
     * 保存商品
     */
    @RequestMapping("/save")
    @ResponseBody
    public BaseResult productSave(Goods goods){
        return goodsService.productSave(goods);
    }

    /**
     * 商品相册保存
     */
    @RequestMapping("/goodsImages/save")
    @ResponseBody
    public BaseResult goodsImagesSave(MultipartFile file, Integer goodsId) {
        try {
            FileResult fileResult = fileUploadService.fileUpload(file.getOriginalFilename(),file.getInputStream());
            //判断图片是否上传成功
            if (null != fileResult.getFileUrl() && fileResult.getFileUrl().length() >0) {
                GoodsImages goodsImages = new GoodsImages();
                goodsImages.setImageUrl(fileResult.getFileUrl());
                goodsImages.setGoodsId(goodsId);
                return goodsImagesService.goodsImagesSave(goodsImages);
            }
        } catch (IOException e) {
            logger.error("文件上传失败，失败原因："+e.getMessage());
        }
        return BaseResult.error();
    }
}
