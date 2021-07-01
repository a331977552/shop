package org.shop.model.vo;

import lombok.Data;

@Data
public class OrderShippingInfoReturnVO {
	private String id;

	private String trackingNum;

	private Integer deliveryNameId;

	private String deliveryCompanyName;

	private String orderId;

}
