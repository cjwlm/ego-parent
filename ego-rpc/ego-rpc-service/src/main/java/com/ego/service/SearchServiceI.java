package com.ego.service;

import com.ego.result.EgoPageInfo;
import com.ego.vo.GoodsVo;

/**
 * 搜索服务
 */
public interface SearchServiceI {
    /**
     * 搜索
     */
    EgoPageInfo<GoodsVo> doSearch(String searchStr, Integer currentPage, Integer pageSize);
}
