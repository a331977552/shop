package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;
import org.shop.common.validator.EnumNamePattern;
import org.shop.common.validator.IDValid;

import java.util.List;

@Data
@ToString
public class OrderUpdateVO {
	@IDValid
	private String id;

	@EnumNamePattern(regexp = "UNPAID|PAID|SHIPPED|FINISHED|REFUND|CLOSED")
	private String status;

	private String payMethod;

	private String buyerComment;

	private String sellerComment;

	private List<OrderItemUpdateVO> items;

	private ShippingAddressUpdateVO address;
}
