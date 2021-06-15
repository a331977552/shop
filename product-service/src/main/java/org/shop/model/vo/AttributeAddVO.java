package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;
import org.shop.common.validator.EnumNamePattern;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ToString
public class AttributeAddVO {

	@NotBlank(message = "attribute name cannot be blank")
	private String name;//color, size

	@NotNull(message = "categoryId 不能为空")
	private Integer categoryId;


	@EnumNamePattern(regexp = "single|multiple")
	private String selectType;//single, multiple

	@EnumNamePattern(regexp = "custom|selection")
	private String entryMethod; //custom, selection

	private Integer sort;
	@NotNull(message = "searchable 不能为空")
	private Boolean searchable;
	@EnumNamePattern(regexp = "normal|color")
	private String searchtype; //normal, color

	private List<AttributeValueAddVO> values;//blue, red,  small, large




}
