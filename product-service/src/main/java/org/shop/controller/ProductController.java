package org.shop.controller;

import org.shop.common.Result;
import org.shop.common.util.Page;
import org.shop.model.vo.ProductAddVO;
import org.shop.model.vo.ProductQueryVO;
import org.shop.model.vo.ProductReturnVO;
import org.shop.model.vo.ProductUpdateVO;
import org.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

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


	@GetMapping("/{page}/{pageSize}")
	public ResponseEntity<Result<Page<ProductReturnVO>>> getAllProductByPage(@PathVariable(name = "page") Integer page,
	                                                                         @PathVariable(name = "pageSize") int pageSize,
	                                                                         @RequestParam(name = "orderBy", required = false) String order, @RequestBody(required = false) ProductQueryVO example) {
		Page<ProductQueryVO> of = Page.of(page, Math.max(5, Math.min(pageSize, this.pageSize)), order);
		return ResponseEntity.ok(Result.of(service.getAll(Optional.ofNullable(example).orElse(new ProductQueryVO()), of)));
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
