package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AttributeValueAddVO {
	private Integer attrKey;
	private String value;
	private String img;
	private Integer sort;
}