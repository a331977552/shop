package org.shop.model.vo;


import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class CustomerResultVO {
	private String id;

	private String customerId;

	private String postCode;

	private String homeAddress;

	private String phoneNumber;

	private LocalDateTime createdTime;

	private LocalDateTime updatedTime;


}
