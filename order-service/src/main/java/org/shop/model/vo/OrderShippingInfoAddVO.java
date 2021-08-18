package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;
import org.shop.common.validator.IDValid;

import javax.validation.constraints.NotEmpty;

@Data
@ToString
public class OrderShippingInfoAddVO {

    @NotEmpty
    private String trackingNum;

    @NotEmpty
    private Integer deliveryNameId;


    @IDValid
    private String sOrderId;



}