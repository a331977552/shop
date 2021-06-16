package org.shop.controller;

import lombok.extern.log4j.Log4j2;
import org.shop.common.Result;
import org.shop.common.util.Page;
import org.shop.model.vo.*;
import org.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Log4j2
@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	CategoryService service;

	@Value("${category.list.page.size}")
	Integer pageSize;

	@PostMapping()
	public Result<CategoryReturnVO> addCategory(@Valid @RequestBody CategoryAddVO category) {
		return Result.of(service.addCategory(category));
	}

	@PutMapping()
	public ResponseEntity<Object> updateCategory(@Valid @RequestBody CategoryUpdateVO category) {
		service.updateCategory(category);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCategory(@NotNull @Min(0) @PathVariable("id") Integer id) {
		service.deleteCategoryById(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{id}")
	public Result<CategoryReturnVO> getCategory(@NotNull @Min(0) @PathVariable("id") Integer id) {
		return Result.of(service.getCategoryById(id));
	}

	//disabled pagination
	@GetMapping("/all/{page}/{pageSize}")
	public Result<Page<CategoryReturnVO>>
	getAllCategory(@PathVariable(name = "page") Integer page,
	               @PathVariable(name = "pageSize") int pageSize,
	               @RequestParam(name = "orderBy", required = false) String order,
	               @RequestParam(name = "parent",required = false) Integer parent,
	               @RequestParam(name = "name",required = false) String name
	               ) {
		final CategoryQueryVO queryVO = new CategoryQueryVO();
		queryVO.setName(name);
		queryVO.setParent(parent);

		Page<ProductQueryVO> of = Page.of(page, Math.max(5, Math.min(pageSize, this.pageSize)), order);
		return Result.of(service.getAll(queryVO, of));

	}


}
