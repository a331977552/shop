package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;
import org.shop.model.OrderSource;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@ToString
public class OrderCreateVO {

	private String customerId;


	@NotNull
	@Min(value = 0,message = "total price cannot be less than 0 ")
	private BigDecimal totalPrice;


	@NotNull
	private OrderSource orderSource;


	@NotNull
	private String payMethod;

	private String buyerComment;

	private String sellerComment;

	private Integer autoConfirmDays;


	@NotEmpty(message = "订单商品不能为空")
	private List<OrderItemCreateVO> items;

	@NotNull(message = "订单地址不能为空")
	private ShippingAddressAddVO address;




}
