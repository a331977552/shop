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
public class ProductUpdateVO {

	@NotNull(message = "产品ID不能为空")
	@NotBlank(message = "产品ID不能为空白")
	private String id;
	@NotBlank(message = "产品名称不能为空")
	private String name;
	@NotNull(message = "必须指定产品目录")
	private Integer category;

	private Integer weight;

	@EnumNamePattern(regexp = "ON_SALE|OUT_OF_ORDER")
	private String status;


	@NotNull
	private Integer priority;

	private String thumbnailImg;
	@NotNull
	private String standardImg;
	@NotEmpty(message = "subtitle cannot be empty")
	private String subtitle;


	private String detail;


	@Min(1)
	private Integer brand;


	private String description;

	private String itemNo;

	private BigDecimal price;

	private BigDecimal marketPrice;

	private String reviewStatus; //"PASSED", "FAILED", "REVIEWING"

	private String specs;

	private String suffix;

//	@NotNull(message = "sku不能为空")x
	private List<SkuUpdateVO> skuList;

	@Data
	@ToString
	public static class SkuUpdateVO {


		@NotNull(message = "stock cannot be null")
		private Integer stock;

		private Integer sales;
		@NotNull(message = "price cannot be null")
		private BigDecimal price;

		@NotBlank(message = "product attribute cannot be empty")
		private String attribute;

		private String img;

	}
}
