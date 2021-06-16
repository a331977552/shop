package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class BrandReturnVO {
	private Integer id;

	private String name;

	private String registrationNum;

	private LocalDateTime createdTime;

	private LocalDateTime updatedTime;

	private String capitalLetter;

	private Integer priority;

	private Boolean isManufacturer;

	private Boolean visible;

	private String info;

	private String description;

}
