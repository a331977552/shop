package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@ToString
public class CategoryAddVO {

	@NotBlank
	private String name;

	@Min(0)
	private Integer parent = 0;


	private Boolean visible = true;

	@Min(0)
	private Integer priority = 0 ;

	private String suffix;

	private String keyword;

}
