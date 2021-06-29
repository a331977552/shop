package org.shop.model.vo;

import lombok.Data;

@Data
public class OrderQueryVO {

	private String receiverName;
	private String username;
	private String orderNum;
	private String orderSource;
	private String status;

	public OrderQueryVO() {
	}

	public OrderQueryVO(String receiverName, String username, String orderNum, String orderSource, String status) {
		this.receiverName = receiverName;
		this.username = username;
		this.orderNum = orderNum;
		this.orderSource = orderSource;
		this.status = status;
	}
}
