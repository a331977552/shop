package org.shop.controller;


import lombok.extern.log4j.Log4j2;
import org.shop.common.Result;
import org.shop.common.util.Page;
import org.shop.model.vo.*;
import org.shop.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Log4j2
@RestController
@RequestMapping("/api/brand")
public class BrandController {
	@Autowired
	BrandService service;

	@PostMapping()
	public Result<BrandReturnVO> addBrand(@Valid @RequestBody BrandAddVO category) {
		return Result.of(service.addBrand(category));
	}

	@PutMapping()
	public void updateBrand(@Valid @RequestBody BrandUpdateVO category) {
		service.updateBrand(category);
	}

	@DeleteMapping("/{id}")
	public void deleteBrand(@NotNull @Min(0) @PathVariable("id") Integer id) {
		service.deleteBrandById(id);
	}

	@GetMapping("/{id}")
	public Result<BrandReturnVO> getBrand(@NotNull @Min(0) @PathVariable("id") Integer id) {
		return Result.of(service.getBrandById(id));
	}

	@GetMapping("/all")
	public Result<Page<BrandReturnVO>>
	getAllBrand(
			@RequestParam(name = "capitalLetter", required = false) String capitalLetter,
			@RequestParam(name = "name", required = false) String name
	) {
		final BrandQueryVO queryVO = new BrandQueryVO();
		queryVO.setName(name);
		queryVO.setCapitalLetter(capitalLetter);
		return Result.of(service.selectByExample(queryVO));

	}

}
