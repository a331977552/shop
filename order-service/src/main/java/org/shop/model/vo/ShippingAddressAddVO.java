package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;
import org.shop.common.validator.IsPhone;

import javax.validation.constraints.NotBlank;

@Data
@ToString
public class ShippingAddressAddVO {


	@NotBlank(message = "用户名不能为空")
	private String customerName;
	@NotBlank(message = "邮编不能为空")
	private String postCode;
	@NotBlank(message = "家庭住址不能为空")
	private String homeAddress;
	@IsPhone
	private String phoneNumber;

}
