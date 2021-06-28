package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;
import org.shop.common.validator.IDValid;

@Data
@ToString
public class ShippingAddressUpdateVO {
	@IDValid
	private String id;

	private String orderId;

	private String customerName;

	private String postCode;

	private String homeAddress;

	private String phoneNumber;

}