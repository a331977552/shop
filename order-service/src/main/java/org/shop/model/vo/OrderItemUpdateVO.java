package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;
import org.shop.common.validator.IDValid;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@ToString
@Data
public class OrderItemUpdateVO {

	@IDValid
	private String id;

	@NotNull
	private Integer quantity;

	@NotNull
	private BigDecimal unitPrice;

	@NotNull
	private BigDecimal subtotal;

}
