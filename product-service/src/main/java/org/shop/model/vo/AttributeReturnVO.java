package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class AttributeReturnVO {

	private Integer id;

	private String name;

	private Integer categoryid;

	private List<AttributeValueReturnVO> values;



}
