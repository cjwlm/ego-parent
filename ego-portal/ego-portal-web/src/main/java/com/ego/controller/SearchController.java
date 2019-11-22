package com.ego.controller;

import com.ego.result.EgoPageInfo;
import com.ego.service.SearchServiceI;
import com.ego.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchServiceI searchService;

    /**
     * 跳转搜索页
     */
    @RequestMapping("/index")
    public String index(String searchStr, Model model) {
        model.addAttribute("searchStr",searchStr);
        return "search/search-index";
    }

    /**
     * 搜索
     */
    @RequestMapping("/doSearch")
    @ResponseBody
    public EgoPageInfo<GoodsVo> doSearch(String searchStr,Integer currentPage,Integer pageSize) {
        return searchService.doSearch(searchStr,currentPage,pageSize);
    }
}
