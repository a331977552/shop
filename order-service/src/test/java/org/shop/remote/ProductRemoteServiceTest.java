package org.shop.remote;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import org.junit.jupiter.api.Test;
import org.shop.common.Result;
import org.shop.model.dto.ProductReturnDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest()
class ProductRemoteServiceTest {

	@Autowired
	ProductRemoteService service;
//	@Autowired
//	SimpleDiscoveryClient client;
	@Test
	void getProduct(){

		final Result<ProductReturnDTO> product = service.getProduct("000f17368bc54869af74feed991be3d5");
		System.out.println(product);
	}

	private RestTemplate restTemplate = new RestTemplate();;

	@Test
	public void run() throws Exception {
		// use the "smart" Eureka-aware RestTemplate
		ResponseEntity<Result<ProductReturnDTO>> exchange =
				this.restTemplate.exchange(
						"http://product-service/api/product/000f17368bc54869af74feed991be3d5",
						HttpMethod.GET,
						null,
						new ParameterizedTypeReference<Result<ProductReturnDTO>>() {
						});
		final Result<ProductReturnDTO> body = exchange.getBody();
		System.out.println(body);
	}

	@Qualifier("eurekaClient")
	@Autowired
	private EurekaClient discoveryClient;

	@Test
	public void serviceUrl() {
		InstanceInfo instance = discoveryClient.getNextServerFromEureka("productService", false);

		System.out.println(instance.getHomePageUrl());
		System.out.println(instance);
		final Applications applications = discoveryClient.getApplications();
		for (Application registeredApplication : applications.getRegisteredApplications()) {
			System.out.println(registeredApplication.getName()+": " + registeredApplication.size() +":  "+ registeredApplication);
		}
	}

}