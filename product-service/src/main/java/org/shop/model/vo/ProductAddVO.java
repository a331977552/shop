package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;
import org.shop.common.validator.EnumNamePattern;

import javax.validation.constraints.Min;
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

//	@NotEmpty(message = "商品 sku不能为空")
	private List<SkuAddVO> skuList;
	private Integer weight;

	private String detail;


	@EnumNamePattern(regexp = "OUT_OF_ORDER|ON_SALE")
	private String status;

	@NotNull
	private Integer priority;

	private String thumbnailImg;

	@NotNull
	private String standardImg;

	@NotNull(message = "必须指定产品目录")
	private Integer categoryId;

	@Min(1)
	private Integer brandId;

	@NotEmpty(message = "subtitle cannot be empty")
	private String subtitle;

	private String description;

	private String itemNo;

	private BigDecimal price;

	private BigDecimal marketPrice;

	private String specs;

	private String suffix;

	@Data
	@ToString
	public static class SkuAddVO {
		private Integer stock;
		private BigDecimal price;
		private String attribute;
		private String img;
	}
}
