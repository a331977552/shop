package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;
import org.shop.common.validator.IDValid;

import javax.validation.constraints.NotEmpty;

@Data
@ToString
public class ProductSpecUpdateVO {

	@IDValid
	private int id;
	@NotEmpty(message = "product spec name cannot be empty")
	private String name;

	@NotEmpty(message = "product spec selectType cannot be empty")
	private String selectType; // single, multiple

	@NotEmpty(message = "product spec entryMethod cannot be empty")
	private String entryMethod; // custom, selection


	private Integer sort = 0;

	private Boolean searchable;

	private String value;
}
