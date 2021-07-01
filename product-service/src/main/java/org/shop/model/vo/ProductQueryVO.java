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
	private Integer categoryId;
	private Status status;
	private Integer brandId;
	private String itemNo;

}
