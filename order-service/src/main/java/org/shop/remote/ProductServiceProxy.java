package org.shop.remote;

import org.shop.common.Result;
import org.shop.model.dto.ProductReturnDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("product-service")
public interface ProductRemoteService {
	@GetMapping("/api/product/{id}")
	Result<ProductReturnDTO> getProduct(@PathVariable("id") String id);

}
