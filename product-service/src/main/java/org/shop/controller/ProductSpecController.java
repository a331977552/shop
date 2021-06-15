package org.shop.controller;

import org.shop.common.Result;
import org.shop.common.util.Page;
import org.shop.model.vo.ProductSpecAddVO;
import org.shop.model.vo.ProductSpecQueryVO;
import org.shop.model.vo.ProductSpecReturnVO;
import org.shop.model.vo.ProductSpecUpdateVO;
import org.shop.service.ProductSpecService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping("/api/product/spec")
public class ProductSpecController {


	final ProductSpecService specService;

	public ProductSpecController(ProductSpecService specService) {
		this.specService = specService;
	}

	@PostMapping()
	public ResponseEntity<Result<ProductSpecReturnVO>> addSpec(@Valid @RequestBody ProductSpecAddVO vo) {
		return ResponseEntity.ok(Result.of(specService.addSpec(vo)));
	}



	@DeleteMapping("/{id}")
	public void deleteSpec(@PathVariable(value = "id") Integer id) {
		specService.deleteSpec(id);
	}

	@PutMapping()
	public void updateSpec(@Valid @RequestBody ProductSpecUpdateVO vo) {
		specService.updateSpec(vo);
	}

	@GetMapping("/{id}")
	public Result<ProductSpecReturnVO> getSpec(@PathVariable(value = "id") Integer id) {
		return Result.of(specService.getSpecById(id));
	}


	@GetMapping()
	public Result<Page<ProductSpecReturnVO>> getSpecs(
			@RequestParam(name = "categoryId",required = false) Integer categoryId,
			@RequestParam(name = "name",required = false) String name
	) {
		return Result.of(specService.getSpecByCategoryExample(new ProductSpecQueryVO(name, categoryId)));
	}


}
