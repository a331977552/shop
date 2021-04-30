package org.shop.model.dao;

import javax.annotation.Generated;
import java.util.Date;

public class CustomerAddressDAO {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.008701+08:00", comments="Source field: customer_address.id")
    private String id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.009834+08:00", comments="Source field: customer_address.customer_id")
    private Integer customerId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.010532+08:00", comments="Source field: customer_address.post_code")
    private String postCode;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.010856+08:00", comments="Source field: customer_address.home_address")
    private String homeAddress;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.011104+08:00", comments="Source field: customer_address.phone_number")
    private String phoneNumber;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.01121+08:00", comments="Source field: customer_address.created_time")
    private Date createdTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.011395+08:00", comments="Source field: customer_address.updated_time")
    private Date updatedTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.009206+08:00", comments="Source field: customer_address.id")
    public String getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.009781+08:00", comments="Source field: customer_address.id")
    public void setId(String id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.009906+08:00", comments="Source field: customer_address.customer_id")
    public Integer getCustomerId() {
        return customerId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.010083+08:00", comments="Source field: customer_address.customer_id")
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.010695+08:00", comments="Source field: customer_address.post_code")
    public String getPostCode() {
        return postCode;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.010787+08:00", comments="Source field: customer_address.post_code")
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.010968+08:00", comments="Source field: customer_address.home_address")
    public String getHomeAddress() {
        return homeAddress;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.01105+08:00", comments="Source field: customer_address.home_address")
    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.011131+08:00", comments="Source field: customer_address.phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.011155+08:00", comments="Source field: customer_address.phone_number")
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.011328+08:00", comments="Source field: customer_address.created_time")
    public Date getCreatedTime() {
        return createdTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.011364+08:00", comments="Source field: customer_address.created_time")
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.011432+08:00", comments="Source field: customer_address.updated_time")
    public Date getUpdatedTime() {
        return updatedTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.011458+08:00", comments="Source field: customer_address.updated_time")
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}