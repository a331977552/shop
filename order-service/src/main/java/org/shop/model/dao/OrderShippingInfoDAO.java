package org.shop.model.dao;

public class OrderShippingInfoDAO {
    private String id;

    private String trackingNum;

    private Integer deliveryCompanyId;

    private String deliveryCompanyName;

    private String sOrderId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrackingNum() {
        return trackingNum;
    }

    public void setTrackingNum(String trackingNum) {
        this.trackingNum = trackingNum;
    }

    public Integer getDeliveryCompanyId() {
        return deliveryCompanyId;
    }

    public void setDeliveryCompanyId(Integer deliveryCompanyId) {
        this.deliveryCompanyId = deliveryCompanyId;
    }

    public String getDeliveryCompanyName() {
        return deliveryCompanyName;
    }

    public void setDeliveryCompanyName(String deliveryCompanyName) {
        this.deliveryCompanyName = deliveryCompanyName;
    }

    public String getsOrderId() {
        return sOrderId;
    }

    public void setsOrderId(String sOrderId) {
        this.sOrderId = sOrderId;
    }
}