package org.shop.model.dao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.annotation.Generated;

public class CartItemDAO {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.785532+08:00", comments="Source field: cart_item.id")
    private String id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.788971+08:00", comments="Source field: cart_item.customer_id")
    private Integer customerId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.789167+08:00", comments="Source field: cart_item.product_id")
    private Integer productId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.789352+08:00", comments="Source field: cart_item.cart_id")
    private Integer cartId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.789618+08:00", comments="Source field: cart_item.quantity")
    private Integer quantity;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.79004+08:00", comments="Source field: cart_item.total_price")
    private BigDecimal totalPrice;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.790225+08:00", comments="Source field: cart_item.created_time")
    private LocalDateTime createdTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.790354+08:00", comments="Source field: cart_item.updated_time")
    private LocalDateTime updatedTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.78764+08:00", comments="Source field: cart_item.id")
    public String getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.788812+08:00", comments="Source field: cart_item.id")
    public void setId(String id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.789069+08:00", comments="Source field: cart_item.customer_id")
    public Integer getCustomerId() {
        return customerId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.78913+08:00", comments="Source field: cart_item.customer_id")
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.789206+08:00", comments="Source field: cart_item.product_id")
    public Integer getProductId() {
        return productId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.789288+08:00", comments="Source field: cart_item.product_id")
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.789443+08:00", comments="Source field: cart_item.cart_id")
    public Integer getCartId() {
        return cartId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.789531+08:00", comments="Source field: cart_item.cart_id")
    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.789731+08:00", comments="Source field: cart_item.quantity")
    public Integer getQuantity() {
        return quantity;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.78989+08:00", comments="Source field: cart_item.quantity")
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.790124+08:00", comments="Source field: cart_item.total_price")
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.790174+08:00", comments="Source field: cart_item.total_price")
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.790277+08:00", comments="Source field: cart_item.created_time")
    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.79032+08:00", comments="Source field: cart_item.created_time")
    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.790398+08:00", comments="Source field: cart_item.updated_time")
    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.790668+08:00", comments="Source field: cart_item.updated_time")
    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }
}