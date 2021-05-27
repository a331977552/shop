package org.shop.model.dao;

import javax.annotation.Generated;
import java.time.LocalDateTime;

public class CustomerAddressDAO {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String customerId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String postCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String homeAddress;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String phoneNumber;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private LocalDateTime createdTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private LocalDateTime updatedTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getId() {
        return id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setId(String id) {
        this.id = id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getCustomerId() {
        return customerId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getPostCode() {
        return postCode;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getHomeAddress() {
        return homeAddress;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }
}