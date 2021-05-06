package org.shop.config;

import org.shop.validator.ChinesePhoneValidator;
import org.shop.validator.PhoneValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MiscConfig {

	@Bean
	public PhoneValidator phoneValidator(){
		return new ChinesePhoneValidator();
	}

}
