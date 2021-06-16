package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;
import org.shop.common.validator.IDValid;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ToString
public class ProductSpecUpdateVO {

	@IDValid
	private Integer id;
	@NotEmpty(message = "product spec name cannot be empty")
	private String name;

	@NotEmpty(message = "product spec selectType cannot be empty")
	private String selectType; // single, multiple

	@NotEmpty(message = "product spec entryMethod cannot be empty")
	private String entryMethod; // custom, selection

	@NotNull(message = "规格的分类ID不能为空")
	private Integer categoryId;

	private Integer sort = 0;

	private Boolean searchable;

	private String value;
}
