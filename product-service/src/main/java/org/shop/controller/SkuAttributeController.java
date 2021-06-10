package org.shop.controller;

import org.shop.common.Result;
import org.shop.common.util.ErrorResultConvertor;
import org.shop.model.vo.AttributeAddVO;
import org.shop.model.vo.AttributeReturnVO;
import org.shop.model.vo.AttributeValueAddVO;
import org.shop.model.vo.AttributeValueReturnVO;
import org.shop.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping("/api/product/attr")
public class SkuAttributeController {


	@Autowired
	AttributeService attributeService;

	@PostMapping()
	public ResponseEntity<Result<AttributeReturnVO>> addAttribute(@Valid @RequestBody AttributeAddVO vo, BindingResult result) {
		if (result.hasErrors())
			return ResponseEntity.badRequest().body(Result.badRequest(ErrorResultConvertor.getErrorMsg(result)));
		return ResponseEntity.ok(Result.of(attributeService.addAttribute(vo)));
	}

	@PostMapping("/value")
	public ResponseEntity<Result<AttributeValueReturnVO>> addValue(@Valid @RequestBody AttributeValueAddVO vo, BindingResult result) {
		if (result.hasErrors())
			return ResponseEntity.badRequest().body(Result.badRequest(ErrorResultConvertor.getErrorMsg(result)));
		return ResponseEntity.ok(Result.of(attributeService.addAttrValue(vo)));
	}


	@DeleteMapping("/value/{id}")
	public ResponseEntity deleteValue(@PathVariable(value = "id") Integer id) {
		attributeService.deleteValue(id);
		return ResponseEntity.ok().build();
	}



	@DeleteMapping("/{id}")
	public ResponseEntity deleteAttr(@PathVariable(value = "id") Integer id) {
		attributeService.deleteAttribute(id);
		return ResponseEntity.ok().build();
	}






}
