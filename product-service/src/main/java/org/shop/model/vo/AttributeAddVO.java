package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@ToString
public class AttributeAddVO {

	@NotBlank(message = "attribute name cannot be blank")
	private String name;//color, size

	private Integer categoryid;

	@NotEmpty(message = "attribute values cannot be empty")
	private List<AttributeValueAddVO> valueVOList;//blue, red,  small, large




}
