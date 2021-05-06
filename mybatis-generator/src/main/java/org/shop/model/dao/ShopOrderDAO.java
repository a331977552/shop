package org.shop.model.dao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.annotation.Generated;

public class ShopOrderDAO {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.956144+08:00", comments="Source field: shop_order.id")
    private String id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.956616+08:00", comments="Source field: shop_order.customer_id")
    private Integer customerId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.957408+08:00", comments="Source field: shop_order.total_price")
    private BigDecimal totalPrice;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.957629+08:00", comments="Source field: shop_order.created_time")
    private LocalDateTime createdTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.957667+08:00", comments="Source field: shop_order.updated_time")
    private LocalDateTime updatedTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.956351+08:00", comments="Source field: shop_order.id")
    public String getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.956595+08:00", comments="Source field: shop_order.id")
    public void setId(String id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.956689+08:00", comments="Source field: shop_order.customer_id")
    public Integer getCustomerId() {
        return customerId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.957385+08:00", comments="Source field: shop_order.customer_id")
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.9576+08:00", comments="Source field: shop_order.total_price")
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.957617+08:00", comments="Source field: shop_order.total_price")
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.957643+08:00", comments="Source field: shop_order.created_time")
    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.957657+08:00", comments="Source field: shop_order.created_time")
    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.957678+08:00", comments="Source field: shop_order.updated_time")
    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.957689+08:00", comments="Source field: shop_order.updated_time")
    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }
}