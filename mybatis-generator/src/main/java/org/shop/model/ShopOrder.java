package org.shop.model;

import java.util.Date;
import javax.annotation.Generated;

public class ShopOrder {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.115719+08:00", comments="Source field: shop_order.id")
    private String id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.115901+08:00", comments="Source field: shop_order.customer_id")
    private Integer customerId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.116727+08:00", comments="Source field: shop_order.total_price")
    private Long totalPrice;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.117301+08:00", comments="Source field: shop_order.created_time")
    private Date createdTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.117665+08:00", comments="Source field: shop_order.updated_time")
    private Date updatedTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.115793+08:00", comments="Source field: shop_order.id")
    public String getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.115849+08:00", comments="Source field: shop_order.id")
    public void setId(String id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.116178+08:00", comments="Source field: shop_order.customer_id")
    public Integer getCustomerId() {
        return customerId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.116683+08:00", comments="Source field: shop_order.customer_id")
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.117198+08:00", comments="Source field: shop_order.total_price")
    public Long getTotalPrice() {
        return totalPrice;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.117282+08:00", comments="Source field: shop_order.total_price")
    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.117628+08:00", comments="Source field: shop_order.created_time")
    public Date getCreatedTime() {
        return createdTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.11765+08:00", comments="Source field: shop_order.created_time")
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.117691+08:00", comments="Source field: shop_order.updated_time")
    public Date getUpdatedTime() {
        return updatedTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.117705+08:00", comments="Source field: shop_order.updated_time")
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}