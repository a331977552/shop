package org.shop.service.impl;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.SelectModel;
import org.mybatis.dynamic.sql.select.aggregate.Count;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.shop.BeanConvertor;
import org.shop.TextUtil;
import org.shop.UUIDUtils;
import org.shop.mapper.CustomerDAOMapper;
import org.shop.model.dao.CustomerDAO;
import org.shop.model.vo.CustomerVO;
import org.shop.service.UserService;
import org.shop.validator.PhoneValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static org.shop.mapper.CustomerDAODynamicSqlSupport.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	private CustomerDAOMapper mapper;
	final Validator validator;
	private final PasswordEncoder passwordEncoder;
	private PhoneValidator phoneValidator;

	public UserServiceImpl(CustomerDAOMapper mapper, Validator validator, PasswordEncoder passwordEncoder, PhoneValidator phoneValidator) {
		this.mapper = mapper;
		this.validator = validator;
		this.passwordEncoder = passwordEncoder;
		this.phoneValidator = phoneValidator;
	}


	@Override
	public synchronized Optional<CustomerVO> register(CustomerVO customerVO) {
		Set<ConstraintViolation<CustomerVO>> validate = validator.validate(customerVO);
		if (validate.size() > 0) {
			log.debug("errors in validation:");
			validate.forEach(System.out::println);
			return Optional.empty();
		}
		return this.registerWrapper(customerVO);
	}

	@Transactional
	protected Optional<CustomerVO> registerWrapper(CustomerVO customerVO) {
		if(TextUtil.isEmpty(customerVO.getUsername(),customerVO.getPhone(),customerVO.getPassword()))
			return Optional.empty();

		CustomerVO test =new CustomerVO();
		test.setUsername(customerVO.getUsername());
		Long duplicateCount = UserServiceImpl.this.count(test);
		if (duplicateCount >0) {
			log.info("multiple user {}",customerVO.getUsername());
			return Optional.empty();
		}
		boolean validate = phoneValidator.validate(customerVO.getPhone());
		if (!validate)
		{
			log.info("invalid phone number {}",phone);
			return Optional.empty();
		}
		test.setUsername(null);
		test.setPhone(customerVO.getPhone());
		duplicateCount = UserServiceImpl.this.count(test);
		if (duplicateCount >0) {
			return Optional.empty();
		}

		customerVO.setId(UUIDUtils.generateID());
		CustomerDAO customerDAO = new CustomerDAO();
		BeanUtils.copyProperties(customerVO, customerDAO);
		customerDAO.setUpdatedTime(LocalDateTime.now());
		customerDAO.setCreatedTime(LocalDateTime.now());
		customerDAO.setPassword(passwordEncoder.encode(customerVO.getPassword()));
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
		CustomerDAO dao =new CustomerDAO();
		BeanUtils.copyProperties(customerVO,dao);
		if(TextUtil.hasText(dao.getPassword())){
			dao.setPassword(passwordEncoder.encode(dao.getPassword()));
		}
		int result = mapper.updateByPrimaryKeySelective(dao);
	}

	@Override
	public Optional<CustomerVO> findUserById(String customerId) {
		Optional<CustomerDAO> dao = mapper.selectByPrimaryKey(customerId);
		return BeanConvertor.convert(dao, CustomerVO.class);
	}

	@Override
	public List<CustomerVO> findAllUsers(int offset,int limit) {
		QueryExpressionDSL<SelectModel>.QueryExpressionWhereBuilder where = select(customerDAO.allColumns()).from(customerDAO).where();

		paginate(limit, offset,where);

		SelectStatementProvider render = where.build().render(RenderingStrategies.MYBATIS3);
		List<CustomerDAO> customerDAOS = mapper.selectMany(render);
		List<CustomerVO> convert = BeanConvertor.convert(customerDAOS, CustomerVO.class);
		return convert;
	}

	@Override
	public Long count() {
		return mapper.count(CountDSLCompleter.allRows());
	}

	@Override
	public Long count(CustomerVO customerVO) {
		QueryExpressionDSL<SelectModel>.QueryExpressionWhereBuilder where = select(Count.of(id)).from(customerDAO).where();
		if (customerVO.getDateOfBirth() != null) {
			where = where.and(customerDAO.dateOfBirth, isEqualTo(customerVO.getDateOfBirth()));
		}

		if (StringUtils.hasText(customerVO.getAlias())) {
			where = where.and(customerDAO.alias, isEqualTo(customerVO.getAlias()));
		}

		if (StringUtils.hasText(customerVO.getUsername())) {
			where = where.and(customerDAO.username, isEqualTo(customerVO.getUsername()));
		}
		if (StringUtils.hasText(customerVO.getPhone())) {
			where = where.and(customerDAO.phone, isEqualTo(customerVO.getPhone()));
		}

		SelectStatementProvider render = where.build().
				render(RenderingStrategies.MYBATIS3);
		return mapper.count(render);
	}

	@Override
	public List<CustomerVO> findUserByExample(CustomerVO customerVO) {
		return this.findUserByExample(customerVO, 0, 0);
	}

	@Override
	public List<CustomerVO> findUserByExample(CustomerVO customerVO, int limit, int offset) {
		return this.findUserByExample(customerVO,limit,offset,null);
	}

	@Override
	public List<CustomerVO> findUserByExample(CustomerVO customerVO, int limit, int offset, String orderBy) {
		QueryExpressionDSL<SelectModel>.QueryExpressionWhereBuilder where = select(id, username,password, alias,phone, email, avatar, dateOfBirth).from(customerDAO).where();

		if (StringUtils.hasText(customerVO.getUsername())) {
			where = where.and(username, isEqualTo(customerVO.getUsername()));
		}
		if (StringUtils.hasText(customerVO.getAlias())) {
			where = where.and(alias, isEqualTo(customerVO.getAlias()));
		}
		if (customerVO.getDateOfBirth()!=null) {
			where = where.and(dateOfBirth, isEqualTo(customerVO.getDateOfBirth()));
		}
		if (TextUtil.hasText(orderBy)){
			where.orderBy(customerDAO.column(orderBy));

		}
		paginate(limit, offset, where);
		SelectStatementProvider render = where.build().
				render(RenderingStrategies.MYBATIS3);
		List<CustomerDAO> customerDAO = mapper.selectMany(render);

		if (TextUtil.hasText(customerVO.getPassword())) {
			customerDAO = customerDAO.stream().filter(cus -> passwordEncoder.matches(customerVO.getPassword(), cus.getPassword())).collect(Collectors.toList());
		}
		return BeanConvertor.convert(customerDAO, CustomerVO.class);
	}

	private void paginate(int limit, int offset, QueryExpressionDSL<?>.QueryExpressionWhereBuilder where) {
		if(limit > 0){
		   where.limit(limit);
			where.offset(offset>0?offset:0);
		}

	}
}
