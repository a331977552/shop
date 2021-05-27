package org.shop.controller;

import org.shop.common.Result;
import org.shop.common.util.SecurityUtil;
import org.shop.common.validator.IDValid;
import org.shop.model.vo.CustomerAddressAddVO;
import org.shop.model.vo.CustomerResultVO;
import org.shop.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {


	@Autowired
	AddressService addressService;

	@PostMapping
	ResponseEntity<Result<CustomerResultVO>> addAddress(@Valid @RequestBody CustomerAddressAddVO vo) throws AuthenticationException {
		vo.setCustomerId(SecurityUtil.getAuthenticatedUserID());

		final CustomerResultVO customerResultVO = addressService.addAddress(vo);
		return ResponseEntity.ok(Result.of(customerResultVO));
	}

	@DeleteMapping("/{id}")
	void deleteAddress(@PathVariable("id") @IDValid String addressID) {
		addressService.deleteAddress(addressID);
	}

	@GetMapping("/{id}")
	ResponseEntity<Result<CustomerResultVO>> findAddressById(
			@PathVariable("id")@IDValid String id) {
		return ResponseEntity.ok(Result.of(addressService.findAddressById(id)));
	}

	@GetMapping("/customer")
	ResponseEntity<Result<List<CustomerResultVO>>> findAddressesByCustomerID() {
		final String authenticatedUserID = SecurityUtil.getAuthenticatedUserID();
		return ResponseEntity.ok(Result.of(addressService.findAddressesByCustomerID(authenticatedUserID)));
	}


}
