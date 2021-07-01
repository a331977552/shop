package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@ToString
public class ProductReturnVO {
	private String id;
	@NotBlank(message = "产品名称不能为空")
	private String name;
	@NotNull(message = "必须指定产品目录")
	private Integer categoryId;

	private Integer weight;

	private String status;

	private Integer priority;

	private String thumbnailImg;

	private String standardImg;

	private Integer brandId;
	private List<SkuReturnVO> skuList;
	private String subtitle;

	private String description;

	private String itemNo;

	private BigDecimal price;

	private BigDecimal marketPrice;

	private String reviewStatus;

	private String suffix;

	private Integer sales;

	private String specs;

	private String detail;

	@Data
	@ToString
	public static class SkuReturnVO {
		private Integer id;

		private String productId;

		private Integer stock;

		private Integer sales;

		private BigDecimal price;

		private String attribute;

		private String img;

	}

}
