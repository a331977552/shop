package org.shop.model;

import javax.annotation.Generated;

public class ShoppingCart {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.134713+08:00", comments="Source field: shopping_cart.id")
    private String id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.134919+08:00", comments="Source field: shopping_cart.customer_id")
    private Integer customerId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.135118+08:00", comments="Source field: shopping_cart.total_price")
    private Long totalPrice;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.134782+08:00", comments="Source field: shopping_cart.id")
    public String getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.134878+08:00", comments="Source field: shopping_cart.id")
    public void setId(String id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.135088+08:00", comments="Source field: shopping_cart.customer_id")
    public Integer getCustomerId() {
        return customerId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.135106+08:00", comments="Source field: shopping_cart.customer_id")
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.135129+08:00", comments="Source field: shopping_cart.total_price")
    public Long getTotalPrice() {
        return totalPrice;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.135141+08:00", comments="Source field: shopping_cart.total_price")
    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }
}