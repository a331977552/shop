package org.shop.controller;

import org.shop.common.Result;
import org.shop.common.util.Page;
import org.shop.model.vo.*;
import org.shop.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping("/api/category/attr")
public class ProductAttrController {


	@Autowired
	AttributeService attributeService;

	@PostMapping()
	public ResponseEntity<Result<AttributeReturnVO>> addAttribute(@Valid @RequestBody AttributeAddVO vo) {
		return ResponseEntity.ok(Result.of(attributeService.addAttribute(vo)));
	}

	@PutMapping()
	public AttributeReturnVO updateAttribute(@Valid @RequestBody AttributeUpdateVO vo) {
		return attributeService.updateAttribute(vo);
	}

	@GetMapping("/{id}")
	public Result<AttributeReturnVO> getAttribute(@PathVariable(value = "id") Integer id) {
		return Result.of(attributeService.getAttribute(id));
	}


	@GetMapping()
	public Result<Page<AttributeReturnVO>> getAttributes(
			@RequestParam(name = "categoryId", required = false) Integer categoryId,
			@RequestParam(name = "name", required = false) String name
	) {
		return Result.of(attributeService.getAttributeByExample(new AttriQueryVO(name, categoryId)));
	}



	@DeleteMapping("/{id}")
	public void deleteAttr(@PathVariable(value = "id") Integer id) {
		attributeService.deleteAttribute(id);
	}


}
