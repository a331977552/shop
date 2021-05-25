package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ToString
public class AttributeValueAddVO {
	@NotNull
	private Integer attrKey;
	@NotBlank
	private String value;
	private String img;
	private Integer sort;
}