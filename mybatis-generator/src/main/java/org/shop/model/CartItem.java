package org.shop.model;

import java.util.Date;
import javax.annotation.Generated;

public class CartItem {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.918922+08:00", comments="Source field: cart_item.id")
    private String id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.922587+08:00", comments="Source field: cart_item.customer_id")
    private Integer customerId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.922972+08:00", comments="Source field: cart_item.product_id")
    private Integer productId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.923147+08:00", comments="Source field: cart_item.cart_id")
    private Integer cartId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.923261+08:00", comments="Source field: cart_item.quantity")
    private Integer quantity;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.923695+08:00", comments="Source field: cart_item.total_price")
    private Long totalPrice;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.92392+08:00", comments="Source field: cart_item.created_time")
    private Date createdTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.924067+08:00", comments="Source field: cart_item.updated_time")
    private Date updatedTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.921228+08:00", comments="Source field: cart_item.id")
    public String getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.922119+08:00", comments="Source field: cart_item.id")
    public void setId(String id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.922787+08:00", comments="Source field: cart_item.customer_id")
    public Integer getCustomerId() {
        return customerId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.922883+08:00", comments="Source field: cart_item.customer_id")
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.923038+08:00", comments="Source field: cart_item.product_id")
    public Integer getProductId() {
        return productId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.923109+08:00", comments="Source field: cart_item.product_id")
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.923184+08:00", comments="Source field: cart_item.cart_id")
    public Integer getCartId() {
        return cartId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.923225+08:00", comments="Source field: cart_item.cart_id")
    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.92332+08:00", comments="Source field: cart_item.quantity")
    public Integer getQuantity() {
        return quantity;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.923491+08:00", comments="Source field: cart_item.quantity")
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.923817+08:00", comments="Source field: cart_item.total_price")
    public Long getTotalPrice() {
        return totalPrice;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.923872+08:00", comments="Source field: cart_item.total_price")
    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.923988+08:00", comments="Source field: cart_item.created_time")
    public Date getCreatedTime() {
        return createdTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.924029+08:00", comments="Source field: cart_item.created_time")
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.924108+08:00", comments="Source field: cart_item.updated_time")
    public Date getUpdatedTime() {
        return updatedTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.924302+08:00", comments="Source field: cart_item.updated_time")
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}