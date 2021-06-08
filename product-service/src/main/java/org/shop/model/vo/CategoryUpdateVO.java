package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;
import org.shop.common.validator.IDValid;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ToString
public class CategoryUpdateVO {
	@IDValid(message = "种类 id 不能为空")
	private Integer id;

	@NotBlank
	private String name;

	@Min(0)
	private Integer parent;

	@NotNull
	private Boolean visible;

	private Integer priority= 0;

	private String suffix;

	private String keyword;

}
