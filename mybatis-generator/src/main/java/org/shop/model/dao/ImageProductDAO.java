package org.shop.model.dao;

import javax.annotation.Generated;

public class ImageProductDAO {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.885365+08:00", comments="Source field: image_product.id")
    private String id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.886038+08:00", comments="Source field: image_product.product_id")
    private String productId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.886116+08:00", comments="Source field: image_product.img_id")
    private String imgId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.885447+08:00", comments="Source field: image_product.id")
    public String getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.885907+08:00", comments="Source field: image_product.id")
    public void setId(String id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.886076+08:00", comments="Source field: image_product.product_id")
    public String getProductId() {
        return productId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.886098+08:00", comments="Source field: image_product.product_id")
    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.886132+08:00", comments="Source field: image_product.img_id")
    public String getImgId() {
        return imgId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.886147+08:00", comments="Source field: image_product.img_id")
    public void setImgId(String imgId) {
        this.imgId = imgId;
    }
}