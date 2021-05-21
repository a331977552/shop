package org.shop.model.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ImageAddVO {
	@NotBlank(message = "图片不能为空")
	private String path;

	private String description;

}
