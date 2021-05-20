package org.shop.common.config;

import org.shop.common.validator.ChinesePhoneValidator;
import org.shop.common.validator.PhoneValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MiscConfig {

	@Bean
	public PhoneValidator phoneValidator() {
		return new ChinesePhoneValidator();
	}


}
