package com.ego.vo;

import java.io.Serializable;
import java.util.List;

public class GoodsCategoryVo implements Serializable {

    // 商品下级分类列表
    private List<GoodsCategoryVo> children;

    private Short id;

    private String name;

    private String mobileName;

    private Short parentId;

    private String parentIdPath;

    private Byte level;

    private Byte sortOrder;

    private Byte isShow;

    private String image;

    private Byte isHot;

    private Byte catGroup;

    private Byte commissionRate;

    private static final long serialVersionUID = 1L;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMobileName() {
        return mobileName;
    }

    public void setMobileName(String mobileName) {
        this.mobileName = mobileName == null ? null : mobileName.trim();
    }

    public Short getParentId() {
        return parentId;
    }

    public void setParentId(Short parentId) {
        this.parentId = parentId;
    }

    public String getParentIdPath() {
        return parentIdPath;
    }

    public void setParentIdPath(String parentIdPath) {
        this.parentIdPath = parentIdPath == null ? null : parentIdPath.trim();
    }

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }

    public Byte getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Byte sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Byte getIsShow() {
        return isShow;
    }

    public void setIsShow(Byte isShow) {
        this.isShow = isShow;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Byte getIsHot() {
        return isHot;
    }

    public void setIsHot(Byte isHot) {
        this.isHot = isHot;
    }

    public Byte getCatGroup() {
        return catGroup;
    }

    public void setCatGroup(Byte catGroup) {
        this.catGroup = catGroup;
    }

    public Byte getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(Byte commissionRate) {
        this.commissionRate = commissionRate;
    }

    public List<GoodsCategoryVo> getChildren() {
        return children;
    }

    public void setChildren(List<GoodsCategoryVo> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", mobileName=").append(mobileName);
        sb.append(", parentId=").append(parentId);
        sb.append(", parentIdPath=").append(parentIdPath);
        sb.append(", level=").append(level);
        sb.append(", sortOrder=").append(sortOrder);
        sb.append(", isShow=").append(isShow);
        sb.append(", image=").append(image);
        sb.append(", isHot=").append(isHot);
        sb.append(", catGroup=").append(catGroup);
        sb.append(", commissionRate=").append(commissionRate);
        sb.append("]");
        return sb.toString();
    }
}