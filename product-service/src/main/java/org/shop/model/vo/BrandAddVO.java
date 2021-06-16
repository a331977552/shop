package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ToString
public class BrandAddVO {

	@NotEmpty
	private String name;

	private String registrationNum;


	@NotEmpty
	@Length(max = 1)
	private String capitalLetter;

	@NotNull
	private Integer priority;

	@NotNull
	private Boolean isManufacturer;

	@NotNull
	private Boolean visible;

	private String description;
}
