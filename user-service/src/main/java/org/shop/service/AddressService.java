package org.shop.service;

import org.shop.model.vo.CustomerAddressAddVO;
import org.shop.model.vo.CustomerResultVO;

import java.util.List;

public interface AddressService {

	CustomerResultVO addAddress(CustomerAddressAddVO vo);
	void deleteAddress(String addressID);
	CustomerResultVO findAddressById(String id);
	List<CustomerResultVO> findAddressesByCustomerID(String customerID);


}
