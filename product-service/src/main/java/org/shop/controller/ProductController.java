package org.shop.controller;

import lombok.extern.log4j.Log4j2;
import org.shop.common.Result;
import org.shop.common.util.Page;
import org.shop.model.vo.*;
import org.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Value("${product.list.page.size}")
	Integer pageSize;
	@Autowired
	ProductService service;

	@PostMapping()
	public ResponseEntity<Result<ProductReturnVO>> addProduct(@Valid @RequestBody ProductAddVO product) {
		return ResponseEntity.ok(Result.of(service.addProduct(product)));
	}

	@PutMapping()
	public void updateProduct(@Valid @RequestBody ProductUpdateVO product) {
		service.updateProduct(product);
	}




	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable("id") String id) {
		service.deleteProductById(id);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Result<ProductReturnVO>> getProduct(@PathVariable("id") String id) {
		return ResponseEntity.ok(Result.of(service.getProductById(id)));
	}


	@GetMapping("sku/{id}")
	public ResponseEntity<Result<ProductReturnVO.SkuReturnVO>> getProductSKU(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(Result.of(service.getProductSKUById(id)));
	}




	@GetMapping("/{page}/{pageSize}")
	public ResponseEntity<Result<Page<ProductReturnVO>>> getAllProductByPage(@PathVariable(name = "page") Integer page,
	                                                                         @PathVariable(name = "pageSize") int pageSize,
	                                                                         @RequestParam(name = "orderBy", required = false) String order,
	                                                                         @RequestParam(name = "name", required = false) String name,
	                                                                         @RequestParam(name = "category", required = false) Integer category,
	                                                                         @RequestParam(name = "status", required = false) String status,
	                                                                         @RequestParam(name = "brand", required = false) Integer brand
	                                                                         ) {
		Page<ProductQueryVO> of = Page.of(page, Math.max(5, Math.min(pageSize, this.pageSize)), order);
		return ResponseEntity.ok(Result.of(service.getAll(
				new ProductQueryVO(name,category, Status.valueOf(Optional.ofNullable(status).orElse("ON_SALE")) ,brand), of)));
	}

	@GetMapping("/findByCategoryId/{id}/{page}/{pageSize}")
	public ResponseEntity<Result<Page<ProductReturnVO>>> findByCategoryIdWithPage(
			@PathVariable(name = "id") Integer cateId,
			@PathVariable(name = "page") Integer page,
			@PathVariable(name = "pageSize") int pageSize,
			@RequestParam(name = "orderBy", required = false) String order) {
		Page<ProductQueryVO> of = Page.of(page, Math.max(5, Math.min(pageSize, this.pageSize)), order);
		return ResponseEntity.ok(Result.of(service.getProductsByCategoryId(cateId, of)));
	}

}
