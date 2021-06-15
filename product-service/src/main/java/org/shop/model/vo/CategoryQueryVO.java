package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CategoryQueryVO {
	private Integer id;
	private String name;

	private Integer parent;

	private Integer level;

	private Boolean isleaf;

	private Boolean visible;

	private String keyword;

}
