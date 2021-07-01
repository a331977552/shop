package org.shop.model.dao;

public class OrderShippingInfoDAO {
    private String id;

    private String trackingNum;

    private Integer deliveryNameId;

    private String deliveryCompanyName;

    private String orderId;

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

    public Integer getDeliveryNameId() {
        return deliveryNameId;
    }

    public void setDeliveryNameId(Integer deliveryNameId) {
        this.deliveryNameId = deliveryNameId;
    }

    public String getDeliveryCompanyName() {
        return deliveryCompanyName;
    }

    public void setDeliveryCompanyName(String deliveryCompanyName) {
        this.deliveryCompanyName = deliveryCompanyName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}