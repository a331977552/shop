package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString
public class OrderReturnVO {

	private String id;

	private String customerId;

	private BigDecimal totalPrice;

	private String status;

	private String postCode;

	private String homeAddress;

	private String phoneNumber;

	private LocalDateTime createdTime;

	private LocalDateTime updatedTime;

	private List<OrderItemReturnVO> items;


}
