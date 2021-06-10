package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ToString
public class AttributeReturnVO {

	private Integer id;

	private String name;

	private Integer categoryId;


	@NotBlank(message = "selectType name cannot be blank")
	private String selectType;//single, multiple

	@NotBlank(message = "entryMethod name cannot be blank")
	private String entryMethod; //custom, selection

	private Integer sort;

	private Boolean searchable;
	@NotBlank(message = "searchtype name cannot be blank")
	private String searchtype; //normal, color

	private List<AttributeValueReturnVO> values;



}
