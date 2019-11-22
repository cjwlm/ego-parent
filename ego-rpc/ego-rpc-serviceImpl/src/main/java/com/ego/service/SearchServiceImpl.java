package com.ego.service;

import com.ego.result.EgoPageInfo;
import com.ego.service.SearchServiceI;
import com.ego.vo.GoodsVo;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchServiceI {

    @Autowired
    private TransportClient client;

    /**
     * 搜索
     * @param searchStr
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public EgoPageInfo<GoodsVo> doSearch(String searchStr, Integer currentPage, Integer pageSize) {
        //指定查询的索引库和类型
        SearchRequestBuilder builder = client.prepareSearch("ego").setTypes("goods");
        //设置分页
        builder.setFrom((currentPage-1) * pageSize).setSize(pageSize);
        //设置高亮前缀
        builder.setHighlighterPreTags("<span style='color: red;'>");
        //设置高亮后缀
        builder.setHighlighterPostTags("</span>");
        //设置高亮字段名称
        builder.addHighlightedField("goodsName");
        //指定查询的列
        builder.setQuery(QueryBuilders.multiMatchQuery(searchStr,"goodsName"));
        //开始查询
        SearchResponse response = builder.get();
        //获取命中数据
        SearchHits searchHits = response.getHits();
        //获取命中数据总条数
        Long total = searchHits.getTotalHits();
        if (0 >total) {
            return null;
        }
        List<GoodsVo> goodsVoList = new ArrayList<>();
        for (SearchHit hit : searchHits) {
            //构建项目中所需的数据结果集
            String highlightMessage = String.valueOf(hit.getHighlightFields().get("goodsName").fragments()[0]);
            Integer goodsId = (Integer) hit.getSource().get("goodsId");
            String goodsName = String.valueOf(hit.getSource().get("goodsName"));
            BigDecimal marketPrice = new BigDecimal(String.valueOf(hit.getSource().get("marketPrice")));
            String originalImg = String.valueOf(hit.getSource().get("originalImg"));

            GoodsVo goodsVo = new GoodsVo(goodsId, goodsName,highlightMessage,marketPrice,originalImg);
            goodsVoList.add(goodsVo);
        }
        //构建分页对象
        EgoPageInfo<GoodsVo> egoPageInfo = new EgoPageInfo<>(currentPage,pageSize,total.intValue());
        egoPageInfo.setResult(goodsVoList);
        return egoPageInfo;
    }
}
