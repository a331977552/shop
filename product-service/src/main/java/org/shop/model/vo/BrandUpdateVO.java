package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;
import org.shop.common.validator.IDValid;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ToString
public class BrandUpdateVO {
	@IDValid
	private Integer id;

	@NotEmpty
	private String name;

	private String registrationNum;

	@NotEmpty
	private String capitalLetter;

	@NotNull
	private Integer priority;

	@NotNull
	private Boolean isManufacturer;

	@NotNull
	private Boolean visible;

	private String description;
}
