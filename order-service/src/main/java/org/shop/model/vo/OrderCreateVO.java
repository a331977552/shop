package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;
import org.shop.common.validator.IsPhone;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@ToString
public class OrderCreateVO {

	private String customerId;

	@NotBlank(message = "postcode cannot be empty")
	private String postCode;

	@NotBlank(message = "home address cannot be empty")
	private String homeAddress;

	@IsPhone
	private String phoneNumber;

	@NotNull
	@Min(value = 0,message = "total price cannot be less than 0 ")
	private BigDecimal totalPrice;

	@NotEmpty(message = "订单商品不能为空")
	private List<OrderItemCreateVO> items;


}
