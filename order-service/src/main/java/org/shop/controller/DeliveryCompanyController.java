package org.shop.controller;


import org.shop.common.Result;
import org.shop.model.vo.DeliveryCompanyAddVO;
import org.shop.model.vo.DeliveryCompanyReturnVO;
import org.shop.service.DeliveryCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController()
@RequestMapping("/api/delivery_company")
public class DeliveryCompanyController {


	@Autowired
	DeliveryCompanyService service;


	@PostMapping()
	public Result<DeliveryCompanyReturnVO> add(@Valid @RequestBody DeliveryCompanyAddVO vo) {
		return Result.of(service.addDeliveryCompany(vo));
	}


	@GetMapping("/all")
	public Result<List<DeliveryCompanyReturnVO>> getAll() {

		return Result.of(service.getAllDeliveryCompanies());
	}


}
