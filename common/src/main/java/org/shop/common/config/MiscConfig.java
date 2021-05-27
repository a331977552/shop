package org.shop.common.config;

import org.shop.common.validator.ChineseIPhoneValidator;
import org.shop.common.validator.IPhoneValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MiscConfig {

	@Bean
	public IPhoneValidator phoneValidator() {
		return new ChineseIPhoneValidator();
	}


}
