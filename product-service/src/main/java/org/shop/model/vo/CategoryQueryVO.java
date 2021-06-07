package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CategoryAddVO {
	private Integer id;

	private String name;

	private Integer parent;

	private Integer level;

	private Boolean isleaf;

	private Boolean visible;

	private Integer priority;

	private String suffix;

	private String keyword;

}
