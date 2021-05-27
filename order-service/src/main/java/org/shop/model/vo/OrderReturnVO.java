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

	private Integer customerId;

	private BigDecimal totalPrice;

	private LocalDateTime createdTime;

	private LocalDateTime updatedTime;

	private List<OrderItemReturnVO> items;


}
