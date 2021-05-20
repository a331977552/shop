package org.shop.controller;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.MyBatisSystemException;
import org.shop.Constants;
import org.shop.common.RedisService;
import org.shop.common.UserRole;
import org.shop.common.util.UUIDUtils;
import org.shop.exception.RegistrationException;
import org.shop.mapper.CustomerDAOMapper;
import org.shop.model.dao.CustomerDAO;
import org.shop.model.vo.CustomerVO;
import org.shop.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
@SpringBootTest
class UserServiceImplTest {
	@Autowired
	UserService service;
	@Autowired
	private Validator validator;
	@Autowired
	CustomerDAOMapper customerMapper;
	@Autowired
	PasswordEncoder encoder;
	static List<String> ids= new ArrayList<>();
//	@Test
//	@Order(0)
	 void addTestData(){
		String names[] = {"jack","tom","rose", "cody"};
		LocalDateTime dayOfBirth[] = {
				LocalDateTime.of(2000, 2, 2,0, 0),
				LocalDateTime.of(2005, 2, 2,0, 0),
				LocalDateTime.of(1990, 5, 2,0, 0),
		};
		 NumberFormat instance = NumberFormat.getInstance();
		 instance.setMinimumIntegerDigits(2);
		 instance.setMaximumIntegerDigits(2);
		 for (int i=0;i<100;i++){
		   CustomerDAO dao=new CustomerDAO();
		   String s = UUIDUtils.generateID();
		   ids.add(s);
		   dao.setId(s);
		   dao.setCreatedTime(LocalDateTime.now());
		   dao.setUpdatedTime(LocalDateTime.now());
		   dao.setEmail("a33197755"+i+"@test.com");
		   dao.setAlias(names[i%names.length]);
		   dao.setRole("CUSTOMER");
		   dao.setPhone("158030123"+instance.format(i));
		   dao.setDateOfBirth(dayOfBirth[i%dayOfBirth.length]);
		   dao.setUsername("158030123"+instance.format(i));
		   dao.setPassword(encoder.encode("a12345"+i));
		   customerMapper.insert(dao);
	   }
	}

//	@Test
//	@Order(Order.DEFAULT)
//	void deleteTestData(){
//		ids.forEach(id->{
//			customerMapper.deleteByPrimaryKey(id);
//		});
//	}

	@Test
	void testValidationOfCustomer() {
		CustomerVO customerVO = new CustomerVO();
		Set<ConstraintViolation<CustomerVO>> validate = validator.validate(customerVO, CustomerVO.RegistryGroup.class);

		assertEquals(5, validate.size(), "customer validation error"+validate.stream().map(v->v.getMessage()).collect(Collectors.joining()));
		customerVO.setUsername("a123321");

		validate = validator.validate(customerVO, CustomerVO.LoginGroup.class);
		System.out.println(validate.stream().map(v->v.getMessage()).collect(Collectors.joining()));
		assertEquals(1, validate.size(), "customer validation error");

		customerVO.setUsername("a123321");
		customerVO.setAlias("tom");
		customerVO.setPassword("a123321");
		customerVO.setEmail("a331977554@qqcom");
		customerVO.setRole(UserRole.CUSTOMER.name());
		validate= validator.validate(customerVO, CustomerVO.RegistryGroup.class);
		System.out.println(validate.stream().map(v->v.getMessage()).collect(Collectors.joining()));
		assertEquals(2, validate.size(), "email or dateOfBirth has error");
		customerVO.setDateOfBirth(LocalDateTime.of(1992, 2, 2,0,0,0));
		customerVO.setEmail("a331977554qq.com");
		validate = validator.validate(customerVO, CustomerVO.RegistryGroup.class);
		System.out.println(validate.stream().map(v->v.getMessage()).collect(Collectors.joining()));

		assertEquals(1, validate.size(), "dateOfBirth has error");
		assertEquals("电话号码不能为空",validate.stream().findFirst().get().getMessage() , "dateOfBirth has error");
		customerVO.setPhone("15932032132");
		customerVO.setEmail("a312@qq.com");
		int size2 = validator.validate(customerVO, CustomerVO.RegistryGroup.class).size();
		System.out.println(validate.stream().map(v->v.getMessage()).collect(Collectors.joining()));

		assertEquals(0, size2, "dateOfBirth has error");
	}

	@Test
	@Transactional
	@Rollback
	void testRegister() {
		CustomerVO customerVO = new CustomerVO();
		customerVO.setAlias("rose2");
		customerVO.setPassword("a123321");
		customerVO.setEmail("a_331977552@qq.com");
		customerVO.setRole(UserRole.CUSTOMER.name());
		customerVO.setDateOfBirth(LocalDateTime.now());
		customerVO.setUsername("AAAAAD");
		customerVO.setPhone("130953245");
		assertThrows(RegistrationException.class,()->service.register(customerVO));
		customerVO.setPhone("13095324542");
		Long count = service.count();

		Optional<CustomerVO> register = service.register(customerVO);

		assertEquals(true, register.isPresent());
		Long count2 = service.count();
		//successful, so should be plus one.
		assertEquals(count2, count + 1, "customer registration error ");
		assertThrows(RegistrationException.class,()->service.register(customerVO),"should throw multiple user found error");
	}


	@Test
	void testLogin() {
		CustomerVO customerVO = new CustomerVO();
		customerVO.setUsername("15803012301");
		customerVO.setPassword("a123451");
		Optional<CustomerVO> register = service.login(new CustomerVO());
		assertEquals(true, register.isEmpty(),"customer registeration error: empty username password");
		Optional<CustomerVO> register2 = service.login(new CustomerVO("1234234","231231"));
		assertEquals(true, register2.isEmpty(),"customer registeration error: wrong username and password");
		Optional<CustomerVO> success = service.login(customerVO);
		String id = success.get().getId();
		assertEquals(id.length(), 32, "customer registeration error");
	}

	//transactional doesn't work when there is another thread running inside of this method
	@Test
	void testHighConcurrencyRegister() throws InterruptedException {
		int threadCount = 60;
		CountDownLatch latch = new CountDownLatch(threadCount);
		CountDownLatch latchMain = new CountDownLatch(threadCount);
		AtomicInteger atomicInteger = new AtomicInteger(0);
		AtomicReference<String> customerId = new AtomicReference<>();
		for (int i = 0; i < threadCount; i++) {
			Thread thread = new Thread(() -> {
				CustomerVO vo = new CustomerVO();
				vo.setAlias("jack");
				vo.setPassword("a123321");
				vo.setEmail("a331977554_@qq.com");
				vo.setRole(UserRole.CUSTOMER.name());
				vo.setUsername("AAAAAA");
				vo.setPhone("15003098887");
				vo.setDateOfBirth(LocalDateTime.of(2000, 2, 2,0,0));
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
				}catch (RegistrationException e){
					System.out.println(e.getMessage());
				}
				latchMain.countDown();
			});
			thread.start();
			latch.countDown();
		}
		latchMain.await();
		assertEquals(1, atomicInteger.get(), (atomicInteger.get() == 0)? "register unsuccessful":"multiple user found! ");
		int i = customerMapper.deleteByPrimaryKey(customerId.get());
		assertEquals(1, i, "delete created user failed ");
	}


	@Transactional
	@Rollback
	@Test
	void updateInfo() {
		CustomerVO vo = new CustomerVO();
		vo.setUsername("15803012301");
		List<CustomerVO> userByExample = service.findUserByExample(vo);
		assertEquals(1, userByExample.size());
		CustomerVO vo1 = userByExample.get(0);
		BeanUtils.copyProperties(vo1, vo);
		vo.setPassword("updated");
		service.updateInfo(vo);
		Optional<CustomerVO> userById = service.findUserById(vo1.getId());
		assertEquals(true ,encoder.matches("updated",userById.get().getPassword()));
	}

	@Test
	void findUserById() {
		List<CustomerVO> allUsers = service.findAllUsers(0, 1);
		Optional<CustomerVO> cus = service.findUserById(allUsers.get(0).getId());
		assertEquals(true, cus.isPresent(),"customer with id "+allUsers.get(0).getId()+" doesn't exist");
	}


	@Test
	void findAllUsers() {
		List<CustomerVO> allUsers = service.findAllUsers(10, 10);
		System.out.println(allUsers.size());
		allUsers.forEach(System.out::println);
	}

	@Test
	void count() {
		Long count = service.count();
		MatcherAssert.assertThat("count", count, greaterThan((long)97));
		CustomerVO vo = new CustomerVO();
		vo.setAlias("jack");
		Long count1 = service.count(vo);
		assertEquals(24, count1);
		vo =new CustomerVO();
		vo.setUsername("15803012301");
		Long count2 = service.count(vo);
		assertEquals(1, count2);
		vo = new CustomerVO();

		vo.setDateOfBirth(LocalDateTime.of(2000, 2, 2,0,0,0));
		Long count3 = service.count(vo);
		assertEquals(32, count3);

	}


	@Test
	void testPagination(){
		List<CustomerVO> username = service.findUserByExample(new CustomerVO(), 10, 0, "username");
		List<CustomerVO> username2 = service.findUserByExample(new CustomerVO(), 10, 10, "username");
		assertEquals(username.get(9).getUsername(), "15803012309");
		assertEquals(username2.get(9).getUsername(), "15803012319");
	}
	@Test
	void findUserByExample() {
		List<CustomerVO> count = service.findUserByExample(new CustomerVO());
		MatcherAssert.assertThat("count", count.size(), greaterThan(97));
		CustomerVO vo = new CustomerVO();
		vo.setAlias("jack");
		List<CustomerVO> count1 = service.findUserByExample(vo);
		assertEquals(24, count1.size());
		vo =new CustomerVO();
		vo.setUsername("15803012301");
		List<CustomerVO> count2 = service.findUserByExample(vo);
		assertEquals(1, count2.size());
		vo = new CustomerVO();

		vo.setDateOfBirth(LocalDateTime.of(2000, 2, 2,0,0));
		List<CustomerVO> count3 = service.findUserByExample(vo);
		assertEquals(32, count3.size());

		vo =new CustomerVO();
		vo.setUsername("15803012301");
		vo.setPassword("a12345");
		List<CustomerVO> count4 = service.findUserByExample(vo);
		assertEquals(0, count4.size());
		vo =new CustomerVO();
		vo.setUsername("15803012301");
		vo.setPassword("a123451");
		List<CustomerVO> count5 = service.findUserByExample(vo);
		assertEquals(1, count5.size());
	}

	@Autowired
	RedisService redisService;


	@Test
	public void testRedis(){
		System.out.println(redisService.toString());
		long increment = redisService.increment(Constants.REDIS_USER_LOGIN_ATTEMPT, 1);
		long increment2 = redisService.increment(Constants.REDIS_USER_LOGIN_ATTEMPT, 1);
		Object o = redisService.get(Constants.REDIS_USER_LOGIN_ATTEMPT);
		System.out.println(increment);
		System.out.println(increment2);
		System.out.println(o);
	}


}