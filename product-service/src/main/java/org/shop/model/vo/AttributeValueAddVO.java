package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@ToString
public class AttributeValueAddVO {
	private Integer attrKey;
	@NotBlank
	private String value;

	public AttributeValueAddVO(){

	}
	public AttributeValueAddVO(String value){
		this.value = value;
	}

}
