package org.shop.model.dao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.annotation.Generated;

public class OrderItemDAO {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.900921+08:00", comments="Source field: order_item.id")
    private String id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.901221+08:00", comments="Source field: order_item.customer_id")
    private Integer customerId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.901272+08:00", comments="Source field: order_item.order_id")
    private Integer orderId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.90131+08:00", comments="Source field: order_item.quantity")
    private Integer quantity;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.901346+08:00", comments="Source field: order_item.product_id")
    private Integer productId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.90138+08:00", comments="Source field: order_item.snapshot_product_id")
    private Integer snapshotProductId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.901429+08:00", comments="Source field: order_item.price")
    private BigDecimal price;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.901467+08:00", comments="Source field: order_item.created_time")
    private LocalDateTime createdTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.901851+08:00", comments="Source field: order_item.updated_time")
    private LocalDateTime updatedTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.901106+08:00", comments="Source field: order_item.id")
    public String getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.901135+08:00", comments="Source field: order_item.id")
    public void setId(String id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.901242+08:00", comments="Source field: order_item.customer_id")
    public Integer getCustomerId() {
        return customerId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.901258+08:00", comments="Source field: order_item.customer_id")
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.901285+08:00", comments="Source field: order_item.order_id")
    public Integer getOrderId() {
        return orderId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.901298+08:00", comments="Source field: order_item.order_id")
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.901322+08:00", comments="Source field: order_item.quantity")
    public Integer getQuantity() {
        return quantity;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.901335+08:00", comments="Source field: order_item.quantity")
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.901357+08:00", comments="Source field: order_item.product_id")
    public Integer getProductId() {
        return productId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.901369+08:00", comments="Source field: order_item.product_id")
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.901391+08:00", comments="Source field: order_item.snapshot_product_id")
    public Integer getSnapshotProductId() {
        return snapshotProductId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.901415+08:00", comments="Source field: order_item.snapshot_product_id")
    public void setSnapshotProductId(Integer snapshotProductId) {
        this.snapshotProductId = snapshotProductId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.901444+08:00", comments="Source field: order_item.price")
    public BigDecimal getPrice() {
        return price;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.901456+08:00", comments="Source field: order_item.price")
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.901485+08:00", comments="Source field: order_item.created_time")
    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.901499+08:00", comments="Source field: order_item.created_time")
    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.901945+08:00", comments="Source field: order_item.updated_time")
    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.901996+08:00", comments="Source field: order_item.updated_time")
    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }
}