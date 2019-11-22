package com.ego.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

public class Goods implements Serializable {
    private Integer goodsId;

    private Integer catId;

    private Integer extendCatId;

    private String goodsSn;

    private String goodsName;

    private Integer clickCount;

    private Short brandId;

    private Short storeCount;

    private Short commentCount;

    private Integer weight;

    private BigDecimal marketPrice;

    private BigDecimal shopPrice;

    private BigDecimal costPrice;

    private String keywords;

    private String goodsRemark;

    private String originalImg;

    private Byte isReal;

    private Byte isOnSale;

    private Byte isFreeShipping;

    private Integer onTime;

    private Short sort;

    private Byte isRecommend;

    private Byte isNew;

    private Byte isHot;

    private Integer lastUpdate;

    private Short goodsType;

    private Short specType;

    private Integer giveIntegral;

    private Integer exchangeIntegral;

    private Short suppliersId;

    private Integer salesSum;

    private Byte promType;

    private Integer promId;

    private BigDecimal commission;

    private String spu;

    private String sku;

    private String goodsContent;

    private static final long serialVersionUID = 1L;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public Integer getExtendCatId() {
        return extendCatId;
    }

    public void setExtendCatId(Integer extendCatId) {
        this.extendCatId = extendCatId;
    }

    public String getGoodsSn() {
        return goodsSn;
    }

    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn == null ? null : goodsSn.trim();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public Integer getClickCount() {
        return clickCount;
    }

    public void setClickCount(Integer clickCount) {
        this.clickCount = clickCount;
    }

    public Short getBrandId() {
        return brandId;
    }

    public void setBrandId(Short brandId) {
        this.brandId = brandId;
    }

    public Short getStoreCount() {
        return storeCount;
    }

    public void setStoreCount(Short storeCount) {
        this.storeCount = storeCount;
    }

    public Short getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Short commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(BigDecimal shopPrice) {
        this.shopPrice = shopPrice;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    public String getGoodsRemark() {
        return goodsRemark;
    }

    public void setGoodsRemark(String goodsRemark) {
        this.goodsRemark = goodsRemark == null ? null : goodsRemark.trim();
    }

    public String getOriginalImg() {
        return originalImg;
    }

    public void setOriginalImg(String originalImg) {
        this.originalImg = originalImg == null ? null : originalImg.trim();
    }

    public Byte getIsReal() {
        return isReal;
    }

    public void setIsReal(Byte isReal) {
        this.isReal = isReal;
    }

    public Byte getIsOnSale() {
        return isOnSale;
    }

    public void setIsOnSale(Byte isOnSale) {
        this.isOnSale = isOnSale;
    }

    public Byte getIsFreeShipping() {
        return isFreeShipping;
    }

    public void setIsFreeShipping(Byte isFreeShipping) {
        this.isFreeShipping = isFreeShipping;
    }

    public Integer getOnTime() {
        return onTime;
    }

    public void setOnTime(Integer onTime) {
        this.onTime = onTime;
    }

    public Short getSort() {
        return sort;
    }

    public void setSort(Short sort) {
        this.sort = sort;
    }

    public Byte getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Byte isRecommend) {
        this.isRecommend = isRecommend;
    }

    public Byte getIsNew() {
        return isNew;
    }

    public void setIsNew(Byte isNew) {
        this.isNew = isNew;
    }

    public Byte getIsHot() {
        return isHot;
    }

    public void setIsHot(Byte isHot) {
        this.isHot = isHot;
    }

    public Integer getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Integer lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Short getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Short goodsType) {
        this.goodsType = goodsType;
    }

    public Short getSpecType() {
        return specType;
    }

    public void setSpecType(Short specType) {
        this.specType = specType;
    }

    public Integer getGiveIntegral() {
        return giveIntegral;
    }

    public void setGiveIntegral(Integer giveIntegral) {
        this.giveIntegral = giveIntegral;
    }

    public Integer getExchangeIntegral() {
        return exchangeIntegral;
    }

    public void setExchangeIntegral(Integer exchangeIntegral) {
        this.exchangeIntegral = exchangeIntegral;
    }

    public Short getSuppliersId() {
        return suppliersId;
    }

    public void setSuppliersId(Short suppliersId) {
        this.suppliersId = suppliersId;
    }

    public Integer getSalesSum() {
        return salesSum;
    }

    public void setSalesSum(Integer salesSum) {
        this.salesSum = salesSum;
    }

    public Byte getPromType() {
        return promType;
    }

    public void setPromType(Byte promType) {
        this.promType = promType;
    }

    public Integer getPromId() {
        return promId;
    }

    public void setPromId(Integer promId) {
        this.promId = promId;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public String getSpu() {
        return spu;
    }

    public void setSpu(String spu) {
        this.spu = spu == null ? null : spu.trim();
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku == null ? null : sku.trim();
    }

    public String getGoodsContent() {
        return goodsContent;
    }

    public void setGoodsContent(String goodsContent) {
        this.goodsContent = goodsContent == null ? null : goodsContent.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", goodsId=").append(goodsId);
        sb.append(", catId=").append(catId);
        sb.append(", extendCatId=").append(extendCatId);
        sb.append(", goodsSn=").append(goodsSn);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", clickCount=").append(clickCount);
        sb.append(", brandId=").append(brandId);
        sb.append(", storeCount=").append(storeCount);
        sb.append(", commentCount=").append(commentCount);
        sb.append(", weight=").append(weight);
        sb.append(", marketPrice=").append(marketPrice);
        sb.append(", shopPrice=").append(shopPrice);
        sb.append(", costPrice=").append(costPrice);
        sb.append(", keywords=").append(keywords);
        sb.append(", goodsRemark=").append(goodsRemark);
        sb.append(", originalImg=").append(originalImg);
        sb.append(", isReal=").append(isReal);
        sb.append(", isOnSale=").append(isOnSale);
        sb.append(", isFreeShipping=").append(isFreeShipping);
        sb.append(", onTime=").append(onTime);
        sb.append(", sort=").append(sort);
        sb.append(", isRecommend=").append(isRecommend);
        sb.append(", isNew=").append(isNew);
        sb.append(", isHot=").append(isHot);
        sb.append(", lastUpdate=").append(lastUpdate);
        sb.append(", goodsType=").append(goodsType);
        sb.append(", specType=").append(specType);
        sb.append(", giveIntegral=").append(giveIntegral);
        sb.append(", exchangeIntegral=").append(exchangeIntegral);
        sb.append(", suppliersId=").append(suppliersId);
        sb.append(", salesSum=").append(salesSum);
        sb.append(", promType=").append(promType);
        sb.append(", promId=").append(promId);
        sb.append(", commission=").append(commission);
        sb.append(", spu=").append(spu);
        sb.append(", sku=").append(sku);
        sb.append(", goodsContent=").append(goodsContent);
        sb.append("]");
        return sb.toString();
    }
}