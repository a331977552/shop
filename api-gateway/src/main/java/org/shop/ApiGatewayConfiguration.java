package org.shop;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

	/**
	 * security : authentication, authorization,
	 * api aggregation,
	 * monitoring
	 *
	 * @param builder
	 * @return
	 */

	public RouteLocator  gatewayRouter(RouteLocatorBuilder builder){

		return builder.routes().build();
	}

}
