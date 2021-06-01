package org.shop.remote;

import org.shop.common.Result;
import org.shop.model.dto.ProductReturnDTO;
import org.shop.model.dto.SkuDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.NotNull;

@FeignClient("api-gateway")
public interface ProductServiceProxy {


	@GetMapping("/product-service/api/product/{id}")
	Result<ProductReturnDTO> getProductByID(@PathVariable("id")@NotNull String id);
	@GetMapping("/product-service/api/product/sku/{id}")
	Result<SkuDTO> getSkuByID(@PathVariable(value = "id")@NotNull(message = "skuID cannot be null") Integer id);
}
