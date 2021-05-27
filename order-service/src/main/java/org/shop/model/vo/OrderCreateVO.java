package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Data
@ToString
public class OrderCreateVO {


	private Integer customerId;

	private BigDecimal totalPrice;
	private List<OrderItemCreateVO> items;


}
