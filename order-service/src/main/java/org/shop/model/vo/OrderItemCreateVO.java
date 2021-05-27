package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class OrderItemCreateVO {


	private Integer quantity;

	private String productId;

	private BigDecimal price;

}
