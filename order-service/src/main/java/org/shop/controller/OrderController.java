package org.shop.controller;

import org.shop.common.Result;
import org.shop.common.util.Page;
import org.shop.model.vo.*;
import org.shop.service.OrderService;
import org.shop.service.OrderShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@RestController()
@RequestMapping("/api/order")
public class OrderController {

	@Value("${order.list.page.size}")
	Integer pageSize;
	@Autowired
	OrderService service;

	@Autowired
	OrderShippingService shippingService;

	@PostMapping()
	public ResponseEntity<Result<OrderReturnVO>> addOrder(@Valid @RequestBody OrderCreateVO vo) {
		return ResponseEntity.ok(Result.of(service.createOrder(vo)));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Result<OrderReturnVO>> getOrder(@PathVariable("id") String id) {
		return ResponseEntity.ok(Result.of(service.findOrderById(id)));
	}

	@PutMapping()
	public void updateOrder(@Valid @RequestBody OrderUpdateVO vo) {
		service.updateOrder(vo);
	}
	@PostMapping("/ship")
	public Result<OrderShippingInfoReturnVO> shipProduct(@Valid @RequestBody OrderShippingInfoAddVO vo) {
		return Result.of(shippingService.shipOrder(vo));
	}


	@DeleteMapping("/{orderID}")
	public void deleteOrder(@Valid @NotEmpty(message = "订单ID不能为空")@PathVariable(name = "orderID") String orderID) {
		service.deleteOrder(orderID);
	}

	@GetMapping("/{page}/{pageSize}")
	public ResponseEntity<Result<Page<OrderReturnVO>>> getAllOrderByPage(@PathVariable(name = "page") Integer page,
	                                                                     @PathVariable(name = "pageSize") int pageSize,
	                                                                     @RequestParam(name = "orderBy", required = false) String order,
	                                                                     @RequestParam(name = "receiverName", required = false) String receiverName,
	                                                                     @RequestParam(name = "orderNum", required = false) String orderNum,
	                                                                     @RequestParam(name = "orderSource", required = false) String orderSource,
	                                                                     @RequestParam(name = "status", required = false) String status,
	                                                                     @RequestParam(name = "username", required = false) String username

	) {

		Page<OrderQueryVO> of = Page.of(page, Math.max(5, Math.min(pageSize, this.pageSize)), order);
		final OrderQueryVO orderQueryVO = new OrderQueryVO(receiverName,username, orderNum, orderSource, status);
		return ResponseEntity.ok(Result.of(service.findAllOrders(orderQueryVO, of)));
	}


}
