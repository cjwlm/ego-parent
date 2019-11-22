package com.ego.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderGoods implements Serializable {
    private Integer recId;

    private Integer orderId;

    private Integer goodsId;

    private String goodsName;

    private String goodsSn;

    private Short goodsNum;

    private BigDecimal marketPrice;

    private BigDecimal goodsPrice;

    private BigDecimal costPrice;

    private BigDecimal memberGoodsPrice;

    private Integer giveIntegral;

    private String specKey;

    private String specKeyName;

    private String barCode;

    private Byte isComment;

    private Byte promType;

    private Integer promId;

    private Byte isSend;

    private Integer deliveryId;

    private String sku;

    private static final long serialVersionUID = 1L;

    public Integer getRecId() {
        return recId;
    }

    public void setRecId(Integer recId) {
        this.recId = recId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getGoodsSn() {
        return goodsSn;
    }

    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn == null ? null : goodsSn.trim();
    }

    public Short getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Short goodsNum) {
        this.goodsNum = goodsNum;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getMemberGoodsPrice() {
        return memberGoodsPrice;
    }

    public void setMemberGoodsPrice(BigDecimal memberGoodsPrice) {
        this.memberGoodsPrice = memberGoodsPrice;
    }

    public Integer getGiveIntegral() {
        return giveIntegral;
    }

    public void setGiveIntegral(Integer giveIntegral) {
        this.giveIntegral = giveIntegral;
    }

    public String getSpecKey() {
        return specKey;
    }

    public void setSpecKey(String specKey) {
        this.specKey = specKey == null ? null : specKey.trim();
    }

    public String getSpecKeyName() {
        return specKeyName;
    }

    public void setSpecKeyName(String specKeyName) {
        this.specKeyName = specKeyName == null ? null : specKeyName.trim();
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode == null ? null : barCode.trim();
    }

    public Byte getIsComment() {
        return isComment;
    }

    public void setIsComment(Byte isComment) {
        this.isComment = isComment;
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

    public Byte getIsSend() {
        return isSend;
    }

    public void setIsSend(Byte isSend) {
        this.isSend = isSend;
    }

    public Integer getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Integer deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku == null ? null : sku.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", recId=").append(recId);
        sb.append(", orderId=").append(orderId);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", goodsSn=").append(goodsSn);
        sb.append(", goodsNum=").append(goodsNum);
        sb.append(", marketPrice=").append(marketPrice);
        sb.append(", goodsPrice=").append(goodsPrice);
        sb.append(", costPrice=").append(costPrice);
        sb.append(", memberGoodsPrice=").append(memberGoodsPrice);
        sb.append(", giveIntegral=").append(giveIntegral);
        sb.append(", specKey=").append(specKey);
        sb.append(", specKeyName=").append(specKeyName);
        sb.append(", barCode=").append(barCode);
        sb.append(", isComment=").append(isComment);
        sb.append(", promType=").append(promType);
        sb.append(", promId=").append(promId);
        sb.append(", isSend=").append(isSend);
        sb.append(", deliveryId=").append(deliveryId);
        sb.append(", sku=").append(sku);
        sb.append("]");
        return sb.toString();
    }
}