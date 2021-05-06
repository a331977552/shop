package org.shop.model.dao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.annotation.Generated;

public class SnapshotProductDAO {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.973132+08:00", comments="Source field: snapshot_product.id")
    private String id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.973292+08:00", comments="Source field: snapshot_product.product_id")
    private Integer productId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.973331+08:00", comments="Source field: snapshot_product.name")
    private String name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.973362+08:00", comments="Source field: snapshot_product.price")
    private BigDecimal price;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.973396+08:00", comments="Source field: snapshot_product.category")
    private Integer category;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.973427+08:00", comments="Source field: snapshot_product.thumbnail_img")
    private Integer thumbnailImg;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.973455+08:00", comments="Source field: snapshot_product.standard_img")
    private Integer standardImg;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.973484+08:00", comments="Source field: snapshot_product.created_time")
    private LocalDateTime createdTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.973516+08:00", comments="Source field: snapshot_product.updated_time")
    private LocalDateTime updatedTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.973857+08:00", comments="Source field: snapshot_product.description")
    private String description;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.973155+08:00", comments="Source field: snapshot_product.id")
    public String getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.973274+08:00", comments="Source field: snapshot_product.id")
    public void setId(String id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.973306+08:00", comments="Source field: snapshot_product.product_id")
    public Integer getProductId() {
        return productId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.97332+08:00", comments="Source field: snapshot_product.product_id")
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.973342+08:00", comments="Source field: snapshot_product.name")
    public String getName() {
        return name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.973353+08:00", comments="Source field: snapshot_product.name")
    public void setName(String name) {
        this.name = name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.973374+08:00", comments="Source field: snapshot_product.price")
    public BigDecimal getPrice() {
        return price;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.973384+08:00", comments="Source field: snapshot_product.price")
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.973408+08:00", comments="Source field: snapshot_product.category")
    public Integer getCategory() {
        return category;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.973418+08:00", comments="Source field: snapshot_product.category")
    public void setCategory(Integer category) {
        this.category = category;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.973436+08:00", comments="Source field: snapshot_product.thumbnail_img")
    public Integer getThumbnailImg() {
        return thumbnailImg;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.973447+08:00", comments="Source field: snapshot_product.thumbnail_img")
    public void setThumbnailImg(Integer thumbnailImg) {
        this.thumbnailImg = thumbnailImg;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.973465+08:00", comments="Source field: snapshot_product.standard_img")
    public Integer getStandardImg() {
        return standardImg;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.973475+08:00", comments="Source field: snapshot_product.standard_img")
    public void setStandardImg(Integer standardImg) {
        this.standardImg = standardImg;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.973497+08:00", comments="Source field: snapshot_product.created_time")
    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.973507+08:00", comments="Source field: snapshot_product.created_time")
    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.973614+08:00", comments="Source field: snapshot_product.updated_time")
    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.973834+08:00", comments="Source field: snapshot_product.updated_time")
    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.976445+08:00", comments="Source field: snapshot_product.description")
    public String getDescription() {
        return description;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.977877+08:00", comments="Source field: snapshot_product.description")
    public void setDescription(String description) {
        this.description = description;
    }
}