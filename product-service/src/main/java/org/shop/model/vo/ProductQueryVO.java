package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductQueryVO {

	private String name;
	private Integer category;
	private Integer weight;
	private Status status;
	private Integer priority;
	private String brand;

}
