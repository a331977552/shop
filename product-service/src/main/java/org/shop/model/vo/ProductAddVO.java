package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@ToString
public class ProductAddVO {
	@NotBlank(message = "产品名称不能为空")
	private String name;
	@NotNull(message = "必须指定产品目录")
	private Integer category;

	@NotEmpty(message = "商品 sku不能为空")
	private List<SkuAddVO> skuList;
	private ProductDetailAddVO productDetail;
	private Integer weight;

	private String status;

	private Integer priority;

	private String thumbnailImg;

	private String standardImg;

	private String brand;

	@Data
	@ToString
	public static class SkuAddVO {
		private String productId;
		private Integer stock;
		private BigDecimal price;
		private String attribute;
		private String img;
	}
}
