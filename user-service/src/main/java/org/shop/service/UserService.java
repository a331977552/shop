package org.shop.service;

import org.shop.model.vo.CustomerVO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {


	/**
	 * assume that the customer is validated already
	 * @param customerVO
	 * @return
	 */
	Optional<CustomerVO> register(CustomerVO customerVO);
	Optional<CustomerVO> login(CustomerVO customerVO);
	void updateInfo(CustomerVO customerVO);
	Optional<CustomerVO> findUserById(String id);
	List<CustomerVO> findAllUsers(int offset,int limit);
	CustomerVO findByToken(String token);
	Optional<CustomerVO> findByUserName(String username);

	Long count();
	Long count(CustomerVO customerVO);
	List<CustomerVO> findUserByExample(CustomerVO customerVO);
	List<CustomerVO> findUserByExample(CustomerVO customerVO,int limit,int offset);
	List<CustomerVO> findUserByExample(CustomerVO customerVO,int limit,int offset,String orderBy);



}
