package org.shop.model.dto;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ToString
public class SkuDTO {
	private Integer id;

	private String productId;

	private Integer stock;

	private BigDecimal price;

	private Integer sales;

	private LocalDateTime createdTime;

	private LocalDateTime updatedTime;

	private String img;

	private String attribute;

}
