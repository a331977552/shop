package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class AttributeReturnVO {

	private Integer id;

	private String name;

	private Integer categoryId;


	private String selectType;//single, multiple

	private String entryMethod; //custom, selection

	private Integer sort;

	private Boolean searchable;
	private String searchtype; //normal, color

	private List<AttributeValueReturnVO> values;



}
