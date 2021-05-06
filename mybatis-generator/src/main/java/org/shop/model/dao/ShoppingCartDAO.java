package org.shop.model.dao;

import java.math.BigDecimal;
import javax.annotation.Generated;

public class ShoppingCartDAO {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.968265+08:00", comments="Source field: shopping_cart.id")
    private String id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.968319+08:00", comments="Source field: shopping_cart.customer_id")
    private Integer customerId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.968353+08:00", comments="Source field: shopping_cart.total_price")
    private BigDecimal totalPrice;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.968289+08:00", comments="Source field: shopping_cart.id")
    public String getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.968306+08:00", comments="Source field: shopping_cart.id")
    public void setId(String id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.968332+08:00", comments="Source field: shopping_cart.customer_id")
    public Integer getCustomerId() {
        return customerId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.968344+08:00", comments="Source field: shopping_cart.customer_id")
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.968365+08:00", comments="Source field: shopping_cart.total_price")
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.968381+08:00", comments="Source field: shopping_cart.total_price")
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}