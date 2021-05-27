package org.shop.service.impl;

import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.shop.common.util.ModelConvertor;
import org.shop.common.util.SecurityUtil;
import org.shop.common.util.UUIDUtils;
import org.shop.exception.CustomerAddressException;
import org.shop.mapper.CustomerAddressDAOMapper;
import org.shop.model.dao.CustomerAddressDAO;
import org.shop.model.vo.CustomerAddressAddVO;
import org.shop.model.vo.CustomerResultVO;
import org.shop.service.AddressService;
import org.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

import static org.shop.mapper.CustomerAddressDAODynamicSqlSupport.*;

@Service
public class AddressServiceImpl implements AddressService, ModelConvertor<CustomerAddressDAO, CustomerAddressAddVO, CustomerResultVO> {
	@Autowired
	CustomerAddressDAOMapper addressDAOMapper;
	@Autowired
	UserService userService;

	@Override
	public CustomerResultVO addAddress(CustomerAddressAddVO vo) {
		final CustomerAddressDAO dao = convertToDAO(vo, CustomerAddressDAO::new);
		dao.setId(UUIDUtils.generateID());
		addressDAOMapper.insertSelective(dao);
		return convertToReturnVO(dao, CustomerResultVO::new);
	}

	@Override
	public CustomerResultVO findAddressById(String id) {
		final Optional<CustomerAddressDAO> customerAddressDAO = addressDAOMapper.selectByPrimaryKey(id);
		final CustomerAddressDAO dao = customerAddressDAO.
				orElseThrow(() -> new CustomerAddressException("error occured while trying to find address by id :" + id));
		SecurityUtil.checkIfIllegalUser(dao.getCustomerId());
		return convertToReturnVO(dao,
				CustomerResultVO::new);
	}

	@Override
	public void deleteAddress(String addressID) {
		final Optional<CustomerAddressDAO> customerAddressDAO = addressDAOMapper.selectByPrimaryKey(addressID);
		final CustomerAddressDAO dao = customerAddressDAO.orElseThrow(() -> new CustomerAddressException("error occurred during deleting address"));
		SecurityUtil.checkIfIllegalUser(dao.getCustomerId());
		addressDAOMapper.deleteByPrimaryKey(addressID);
	}

	@Override
	public List<CustomerResultVO> findAddressesByCustomerID(@NotNull String customerID) {
		final SelectStatementProvider render = SqlBuilder.select(customerAddressDAO.allColumns()).from(customerAddressDAO).
				where(customerId, SqlBuilder.isEqualTo(customerID)).build().render(RenderingStrategies.MYBATIS3);
		final List<CustomerAddressDAO> customerAddressDAOS = addressDAOMapper.selectMany(render);
		return convertToReturnVO(customerAddressDAOS, CustomerResultVO.class);
	}
}
