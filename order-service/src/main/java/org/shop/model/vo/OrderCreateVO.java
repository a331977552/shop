package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@ToString
public class OrderCreateVO {

	private Integer customerId;

	private String postCode;

	private String homeAddress;

	private String phoneNumber;

	@NotEmpty(message = "订单商品不能为空")
	private List<OrderItemCreateVO> items;


}
