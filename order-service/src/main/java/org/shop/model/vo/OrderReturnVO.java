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

	private String username;

	private BigDecimal totalPrice;

	private String status;

	private LocalDateTime createdTime;

	private LocalDateTime updatedTime;

	private String payMethod;

	private String orderSource;

	private String orderNum;

	private String buyerComment;

	private String sellerComment;

	private Integer autoConfirmDays;

	private List<OrderItemReturnVO> items;

	private ShippingAddressReturnVO address;


}
