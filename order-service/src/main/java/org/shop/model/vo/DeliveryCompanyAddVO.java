package org.shop.model.vo;

import javax.validation.constraints.NotEmpty;

public class DeliveryCompanyAddVO {

    @NotEmpty(message = "delivery company cannot be empty")
    private String name;

    private String info;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}