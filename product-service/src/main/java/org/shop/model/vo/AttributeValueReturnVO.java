package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class AttributeValueReturnVO {
	private Integer id;

	private Integer attrKey;

	private String value;

}