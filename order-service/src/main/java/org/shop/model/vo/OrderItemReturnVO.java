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

	private String sOrderId;

	private Integer skuId;

	private Integer quantity;

	private String productId;

	private BigDecimal unitPrice;


	private String snapshotProductId;

	private BigDecimal subtotal;

	private LocalDateTime createdTime;

	private LocalDateTime updatedTime;


}
