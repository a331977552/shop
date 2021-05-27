package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ToString
public class OrderItemReturnVO {

	private String id;

	private String customerId;

	private String orderId;

	private Integer quantity;

	private String productId;


	private String snapshotProductId;

	private BigDecimal price;

	private LocalDateTime createdTime;

	private LocalDateTime updatedTime;
}
