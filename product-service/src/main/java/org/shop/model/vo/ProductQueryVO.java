package org.shop.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductQueryVO {

	private String name;
	private Integer category;
	private Status status;
	private Integer brand;
	private String itemNo;

}
