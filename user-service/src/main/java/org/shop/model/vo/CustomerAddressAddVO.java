package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;
import org.shop.common.validator.IsPhone;

import javax.validation.constraints.NotBlank;

@Data
@ToString
public class CustomerAddressAddVO {

	private String customerId;

	@NotBlank(message = "邮编不能为空")
	private String postCode;

	@NotBlank(message = "家庭住址不能为空")
	private String homeAddress;

	@IsPhone()
	private String phoneNumber;

}
