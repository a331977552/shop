package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class ProductDetailAddVO {

	private String  productId;
	private String description;


}
