package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;
import org.shop.common.validator.IDValid;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@ToString
public class OrderItemCreateVO {

	@NotNull
	@Min(1)
	private Integer quantity;

	@IDValid(message = "产品ID 不能为空")
	private String productId;

	@NotNull(message = "unit price not null")
	private BigDecimal unitPrice;

	@IDValid(message = "sku ID 不能为空")
	private Integer skuId;

	@NotNull
	@Min(value = 0,message = "商品价格不能为负数")
	private BigDecimal subtotal;

}
