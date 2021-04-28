package org.shop.model;

import javax.annotation.Generated;

public class ImageProduct {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.036351+08:00", comments="Source field: image_product.id")
    private String id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.03689+08:00", comments="Source field: image_product.customer_id")
    private String customerId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.037038+08:00", comments="Source field: image_product.img_id")
    private String imgId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.03653+08:00", comments="Source field: image_product.id")
    public String getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.036854+08:00", comments="Source field: image_product.id")
    public void setId(String id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.036989+08:00", comments="Source field: image_product.customer_id")
    public String getCustomerId() {
        return customerId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.037016+08:00", comments="Source field: image_product.customer_id")
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.037058+08:00", comments="Source field: image_product.img_id")
    public String getImgId() {
        return imgId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.037075+08:00", comments="Source field: image_product.img_id")
    public void setImgId(String imgId) {
        this.imgId = imgId;
    }
}