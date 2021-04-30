package org.shop.service;

import org.shop.model.vo.CustomerVO;

import java.util.List;
import java.util.Optional;

public interface UserService {


	/**
	 * assume that the customer is validated already
	 * @param customerVO
	 * @return
	 */
	Optional<CustomerVO> register(CustomerVO customerVO);
	Optional<CustomerVO> login(CustomerVO customerVO);
	void updateInfo(CustomerVO customerVO);
	Optional<CustomerVO> findUserById(String id);
	List<CustomerVO> findAllUsers();
	Long count();
	Long count(CustomerVO customerVO);
	List<CustomerVO> findUserByExample(CustomerVO customerVO);



}
