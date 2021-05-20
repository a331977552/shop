package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CategoryVO {
	private Integer id;
	private String name;
	private Integer parent;
}
