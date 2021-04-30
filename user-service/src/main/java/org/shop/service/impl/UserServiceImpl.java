package org.shop.service.impl;

import lombok.extern.java.Log;
import org.apache.logging.log4j.util.Strings;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.SelectModel;
import org.mybatis.dynamic.sql.select.aggregate.Count;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.shop.BeanConvertor;
import org.shop.UUIDUtils;
import org.shop.mapper.CustomerMapper;
import org.shop.model.dao.CustomerDAO;
import org.shop.model.vo.CustomerVO;
import org.shop.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.Instant;
import java.util.*;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static org.shop.mapper.CustomerDynamicSqlSupport.*;

@Service
@Log
public class UserServiceImpl implements UserService {

	private CustomerMapper mapper;
	@Autowired
	Validator validator;

	public UserServiceImpl(CustomerMapper mapper) {
		this.mapper = mapper;
	}


	@Override
	public synchronized Optional<CustomerVO> register(CustomerVO customerVO) {
		Set<ConstraintViolation<CustomerVO>> validate = validator.validate(customerVO);
		if (validate.size() > 0) {
			log.warning("errors in validation:");
			validate.forEach(System.out::println);
			return Optional.empty();
		}
		return this.registerWrapper(customerVO);
	}

	@Transactional
	protected Optional<CustomerVO> registerWrapper(CustomerVO customerVO) {
		customerVO.setId(UUIDUtils.generateID());
		CustomerDAO customerDAO = new CustomerDAO();
		BeanUtils.copyProperties(customerVO, customerDAO);
		customerDAO.setUpdatedTime(Date.from(Instant.now()));
		customerDAO.setCreatedTime(Date.from(Instant.now()));
		List<CustomerVO> result = UserServiceImpl.this.findUserByExample(customerVO);
		if (!result.isEmpty())
			return Optional.empty();
		mapper.insert(customerDAO);
		return BeanConvertor.convert(Optional.of(customerVO), CustomerVO.class);
	}

	@Override
	public Optional<CustomerVO> login(CustomerVO customerVO) {

		List<CustomerVO> userByExample = this.findUserByExample(customerVO);
		if (userByExample.size() == 1) {
			return Optional.of(userByExample.get(0));
		} else {
			return Optional.empty();
		}
	}

	@Transactional
	@Override
	public void updateInfo(CustomerVO customerVO) {
		UpdateDSL<UpdateModel> update = update(customer).
				set(alias).equalTo(customerVO.getAlias()).
				set(dateOfBirth).equalTo(customerVO.getDateOfBirth()).
				set(updatedTime).equalTo(Calendar.getInstance().getTime());
		if (StringUtils.hasText(customerVO.getPassword())) {
			update = update.set(password).equalTo(customerVO.getPassword());
		}

		UpdateStatementProvider render = update.where(id, isEqualTo(customerVO.getId())).
				build().
				render(RenderingStrategies.MYBATIS3);
		Map<String, Object> parameters = render.getParameters();
		parameters.forEach((k, v) -> {
			System.out.println(k + "  " + v);
		});
		int result = mapper.update(render);
	}

	@Override
	public Optional<CustomerVO> findUserById(String customerId) {
		Optional<CustomerDAO> dao = mapper.selectByPrimaryKey(customerId);
		return BeanConvertor.convert(dao, CustomerVO.class);
	}

	@Override
	public List<CustomerVO> findAllUsers() {

		return null;
	}

	@Override
	public Long count() {
		return mapper.count(CountDSLCompleter.allRows());
	}

	@Override
	public Long count(CustomerVO customerVO) {
		QueryExpressionDSL<SelectModel>.QueryExpressionWhereBuilder where = select(Count.of(id)).from(customer).where();
		if (customerVO.getDateOfBirth() != null) {
			where = where.and(customer.dateOfBirth, isEqualTo(customerVO.getDateOfBirth()));
		}

		if (StringUtils.hasText(customerVO.getAlias())) {
			where = where.and(customer.alias, isEqualTo(customerVO.getAlias()));
		}

		if (StringUtils.hasText(customerVO.getUsername())) {
			where = where.and(customer.username, isEqualTo(customerVO.getUsername()));
		}

		SelectStatementProvider render = where.build().
				render(RenderingStrategies.MYBATIS3);
		return mapper.count(render);
	}

	@Override
	public List<CustomerVO> findUserByExample(CustomerVO customerVO) {
		QueryExpressionDSL<SelectModel>.QueryExpressionWhereBuilder where = select(id, username, alias, email, avatar, dateOfBirth).from(customer).where();

		if (StringUtils.hasText(customerVO.getUsername())) {
			where = where.and(username, isEqualTo(customerVO.getUsername()));
		}
		if (StringUtils.hasText(customerVO.getAlias())) {
			where = where.and(alias, isEqualTo(customerVO.getAlias()));
		}
		if (customerVO.getDateOfBirth()!=null) {
			where = where.and(dateOfBirth, isEqualTo(customerVO.getDateOfBirth()));
		}
		if (StringUtils.hasText(customerVO.getPassword())) {
			where = where.and(password, isEqualTo(customerVO.getPassword()));
		}
		SelectStatementProvider render = where.build().
				render(RenderingStrategies.MYBATIS3);
		List<CustomerDAO> customerDAO = mapper.selectMany(render);
		return BeanConvertor.convert(customerDAO, CustomerVO.class);
	}
}
