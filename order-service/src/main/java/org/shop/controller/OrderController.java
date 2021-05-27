package org.shop.controller;

import org.shop.common.Result;
import org.shop.common.util.Page;
import org.shop.model.vo.OrderCreateVO;
import org.shop.model.vo.OrderQueryVO;
import org.shop.model.vo.OrderReturnVO;
import org.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController()
@RequestMapping("/api/order")
public class OrderController {


	@Value("${order.list.page.size}")
	Integer pageSize;
	@Autowired
	OrderService service;

	@PostMapping()
	public ResponseEntity<Result<OrderReturnVO>> addOrder(@Valid @RequestBody OrderCreateVO vo) {
		return ResponseEntity.ok(Result.of(service.createOrder(vo)));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Result<OrderReturnVO>> getOrder(@PathVariable("id") String id) {
		return ResponseEntity.ok(Result.of(service.findOrderById(id)));
	}


	@GetMapping("/{page}/{pageSize}")
	public ResponseEntity<Result<Page<OrderReturnVO>>> getAllOrderByPage(@PathVariable(name = "page") Integer page,
	                                                                         @PathVariable(name = "pageSize") int pageSize,
	                                                                         @RequestParam(name = "orderBy", required = false) String order, @RequestBody(required = false) OrderQueryVO example) {
		Page<OrderQueryVO> of = Page.of(page, Math.max(5, Math.min(pageSize, this.pageSize)), order);
		return ResponseEntity.ok(Result.of(service.findAllOrders(Optional.ofNullable(example).orElse(new OrderQueryVO()), of)));
	}
//
//	@GetMapping("/findByCategoryId/{id}/{page}/{pageSize}")
//	public ResponseEntity<Result<Page<OrderReturnVO>>> findByCategoryIdWithPage(
//			@PathVariable(name = "id") Integer cateId,
//			@PathVariable(name = "page") Integer page,
//			@PathVariable(name = "pageSize") int pageSize,
//			@RequestParam(name = "orderBy", required = false) String order) {
//		Page<ProductQueryVO> of = Page.of(page, Math.max(5, Math.min(pageSize, this.pageSize)), order);
//		return ResponseEntity.ok(Result.of(service.getProductsByCategoryId(cateId, of)));
//	}

}
