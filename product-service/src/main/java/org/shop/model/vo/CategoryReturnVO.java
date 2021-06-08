package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class CategoryReturnVO {
	private Integer id;

	private String name;

	private Integer parent;

	private Integer level;

	private Boolean isleaf;

	private Boolean visible;

	private Integer priority;

	private LocalDateTime createdTime;

	private LocalDateTime updatedTime;
	private String suffix;

	private String keyword;

}
