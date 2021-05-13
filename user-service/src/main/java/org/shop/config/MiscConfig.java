package org.shop.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.shop.validator.ChinesePhoneValidator;
import org.shop.validator.PhoneValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MiscConfig {

	@Bean
	public PhoneValidator phoneValidator() {
		return new ChinesePhoneValidator();
	}


}
