package org.shop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcCfg implements WebMvcConfigurer {
	@Override
	public void addFormatters(FormatterRegistry registry) {
//		DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
//		registrar.setUseIsoFormat(true);
//		registrar.registerFormatters(registry);
	}
}
