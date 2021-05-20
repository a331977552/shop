package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@ToString
public class ProductUpdateVO {

	@NotNull(message = "产品ID不能为空")
	@NotBlank(message = "产品ID不能为空白")
	private String id;
	@NotBlank(message = "产品名称不能为空")
	private String name;
	@NotNull(message = "必须指定产品目录")
	private Integer category;

	private Integer weight;

	private String status;

	private Integer priority;

	private String thumbnailImg;

	private String standardImg;

	private String brand;
	@NotNull
	private List<SkuUpdateVO> skuList;

	@Data
	@ToString
	public static class SkuUpdateVO {
		private String id;

		private String productId;

		private Integer stock;

		private Integer sales;

		private BigDecimal price;

		private String attribute;

		private String img;

	}
}
