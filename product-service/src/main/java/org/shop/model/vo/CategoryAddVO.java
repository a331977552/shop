package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class CategoryVO {
	private Integer id;

	private String name;

	private Integer parent;

	private LocalDateTime createdTime;

	private LocalDateTime updatedTime;

	private Integer level;

	private Boolean isleaf;

	private Boolean visible;

	private Integer priority;

	private String suffix;

	private String keyword;

}
