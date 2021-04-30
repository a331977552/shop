package org.shop.service;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.MyBatisSystemException;
import org.shop.mapper.CustomerMapper;
import org.shop.model.UserRole;
import org.shop.model.vo.CustomerVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImplTest {
	@Autowired
	UserService service;
	@Autowired
	private Validator validator;
	@Autowired
	CustomerMapper customerMapper;


	@Test
	void testValidationOfCustomer() {
		CustomerVO customerVO = new CustomerVO();
		Set<ConstraintViolation<CustomerVO>> validate = validator.validate(customerVO);
		assertEquals(3, validate.size(), "customer validation error");
		customerVO.setUsername("a123321");
		customerVO.setAlias("tom");
		customerVO.setPassword("a123321");
		customerVO.setEmail("a331977554@qqcom");
		customerVO.setRole(UserRole.CUSTOMER.name());
		int size = validator.validate(customerVO).size();
		assertEquals(2, size, "email or dateOfBirth has error");
		customerVO.setDateOfBirth(new Date(1992, 02, 02));
		customerVO.setEmail("a331977554qq.com");
		Set<ConstraintViolation<CustomerVO>> size1 = validator.validate(customerVO);
		assertEquals(1, size1.size(), "dateOfBirth has error");
		assertEquals(size1.stream().findFirst().get().getMessage(), "电子邮件格式不正确", "dateOfBirth has error");
		customerVO.setEmail("a312@qq.com");
		int size2 = validator.validate(customerVO).size();
		assertEquals(0, size2, "dateOfBirth has error");
	}

	@Test
	@Transactional
	@Rollback
	void testRegister() {
		CustomerVO customerVO = new CustomerVO();
		customerVO.setAlias("rose2");
		customerVO.setPassword("a123321");
		customerVO.setEmail("a331977551@qq.com");
		customerVO.setRole(UserRole.CUSTOMER.name());
		Calendar instance = Calendar.getInstance();
		instance.set(2000, 2, 2);
		customerVO.setDateOfBirth(instance.getTime());
		Long count = service.count();
		customerVO.setUsername("AAAAAD");
		service.register(customerVO);
		Long count2 = service.count();
		//successful, so should be plus one.
		assertEquals(count2, count + 1, "customer registration error 2");
		Optional<CustomerVO> register2 = service.register(customerVO);//failure, because same username AAAAAA
		assertEquals(register2.isEmpty(), true);
	}


	@Test
	void testLogin() {
		CustomerVO customerVO = new CustomerVO();
		customerVO.setUsername("a112233");
		customerVO.setPassword("a123321");
		Optional<CustomerVO> register = service.login(customerVO);
		String id = register.get().getId();
		assertEquals(id.length(), 32, "customer registeration error");
	}

	//transactional doesn't work when there is another thread running inside of this method
	@Test
	void testHighConcurrencyRegister() throws InterruptedException {
		CustomerVO vo1 = new CustomerVO();
		vo1.setUsername("AAAAAA");
		List<CustomerVO> userByExample = service.findUserByExample(vo1);
		if(!userByExample.isEmpty()){
			customerMapper.deleteByPrimaryKey(userByExample.get(0).getId());
		}
		int threadCount = 30;
		CountDownLatch latch = new CountDownLatch(threadCount);
		CountDownLatch latchMain = new CountDownLatch(threadCount);
		AtomicInteger atomicInteger = new AtomicInteger(0);
		AtomicReference<String> customerId = new AtomicReference<>();
		for (int i = 0; i < threadCount; i++) {
			Thread thread = new Thread(() -> {
				CustomerVO vo = new CustomerVO();
				vo.setAlias("jack");
				vo.setPassword("a123321");
				vo.setEmail("a331977554@qq.com");
				vo.setRole(UserRole.CUSTOMER.name());
				vo.setUsername("AAAAAA");
				Calendar instance = Calendar.getInstance();
				instance.set(2000, 2,2);
				vo.setDateOfBirth(instance.getTime());
				try {
					latch.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				try {
					Optional<CustomerVO> register = service.register(vo);
					if (register.isPresent()) {
						customerId.set(register.get().getId());
						atomicInteger.incrementAndGet();
					}
				} catch (MyBatisSystemException e) {
					e.printStackTrace();
				}
				latchMain.countDown();
			});
			thread.start();
			latch.countDown();
		}
		latchMain.await();
		assertEquals(1, atomicInteger.get(), "multiple user found! ");
		int i = customerMapper.deleteByPrimaryKey(customerId.get());
		assertEquals(1, i, "delete created user failed ");
	}


	@Transactional
	@Rollback
	@Test
	void updateInfo() {
		CustomerVO vo = new CustomerVO();
		vo.setUsername("a123321");
		List<CustomerVO> userByExample = service.findUserByExample(vo);
		assertEquals(1, userByExample.size());
		CustomerVO vo1 = userByExample.get(0);
		BeanUtils.copyProperties(vo1, vo);
		vo.setPassword("updated");
		service.updateInfo(vo);
		Optional<CustomerVO> userById = service.findUserById(vo1.getId());
		assertEquals("updated",userById.get().getPassword());
	}

	@Test
	void findUserById() {
		Optional<CustomerVO> cus = service.findUserById("e94bdebed63f4f8294887e3a4db9eec6");
		assertEquals(true, cus.isPresent(),"customer with id e94bdebed63f4f8294887e3a4db9eec6 doesn't exist");
	}

	@Test
	void findAllUsers() {

	}

	@Test
	void count() {
		Long count = service.count();
		assertEquals(4, count);
		CustomerVO vo = new CustomerVO();
		vo.setAlias("jack");
		Long count1 = service.count(vo);
		assertEquals(2, count1);
		vo =new CustomerVO();
		vo.setUsername("a123321");
		Long count2 = service.count(vo);
		assertEquals(1, count2);
		vo = new CustomerVO();
		Calendar instance = Calendar.getInstance();
		instance.set(2000, 2, 2);
		vo.setDateOfBirth(instance.getTime());
		Long count3 = service.count(vo);
		assertEquals(2, count3);

	}


	@Test
	void findUserByExample() {
		List<CustomerVO> count = service.findUserByExample(new CustomerVO());
		assertEquals(4, count.size());
		CustomerVO vo = new CustomerVO();
		vo.setAlias("jack");
		List<CustomerVO> count1 = service.findUserByExample(vo);
		assertEquals(2, count1.size());
		vo =new CustomerVO();
		vo.setUsername("a123321");
		List<CustomerVO> count2 = service.findUserByExample(vo);
		assertEquals(1, count2.size());
		vo = new CustomerVO();
		Calendar instance = Calendar.getInstance();
		instance.set(2000, 2, 2);
		vo.setDateOfBirth(instance.getTime());
		List<CustomerVO> count3 = service.findUserByExample(vo);
		assertEquals(2, count3.size());

	}
}