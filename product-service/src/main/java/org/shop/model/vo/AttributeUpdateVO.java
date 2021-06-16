package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;
import org.shop.common.validator.EnumNamePattern;
import org.shop.common.validator.IDValid;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ToString
public class AttributeUpdateVO {

	@IDValid
	private Integer id;
	@NotEmpty(message = "product spec name cannot be empty")
	private String name;

	@NotEmpty(message = "product spec selectType cannot be empty")
	@EnumNamePattern(regexp = "single|multiple")
	private String selectType; // single, multiple

	@NotEmpty(message = "product spec entryMethod cannot be empty")
	@EnumNamePattern(regexp = "custom|selection")
	private String entryMethod; // custom, selection

	@NotNull(message = "规格的分类ID不能为空")
	private Integer categoryId;
	@EnumNamePattern(regexp = "normal|color")
	private String searchtype; //normal, color
	private Integer sort = 0;
	@NotNull(message = "searchable不能为空")

	private Boolean searchable;


	private List<AttributeValueReturnVO> values;

}
