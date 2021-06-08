package org.shop.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.shop.common.Result;
import org.shop.common.util.Page;
import org.shop.model.vo.CategoryAddVO;
import org.shop.model.vo.CategoryReturnVO;
import org.shop.model.vo.CategoryUpdateVO;
import org.shop.test.utils.BaseControllerTest;
import org.shop.test.utils.RestTestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class CategoryControllerTest extends BaseControllerTest<CategoryReturnVO> {

	@Autowired
	private RestTestHelper helper;


	@Test
	@Order(0)
	public void addCategory() {
		final String token = getToken();
		CategoryAddVO vo=new CategoryAddVO();
		vo.setParent(10);
		vo.setName("水果系列");
		vo.setPriority(1);
		vo.setSuffix("斤");
		vo.setKeyword("水果");
		addCate(vo,token);
	}
	private  void addCate(CategoryAddVO vo, String token){
		final ResponseEntity<Result<CategoryReturnVO>> post = helper.setPort(port).setUIPath("/api/category").builder().withToken(token)
				.build().post(vo, resVOReturnRef);
		final Result<CategoryReturnVO> body = post.getBody();
		System.out.println(body);
		Assertions.assertEquals(200, post.getStatusCodeValue() );
		Assertions.assertTrue(body.getResult().getId() != null);
	}

	@Test()
	public void updateCategory() {
		final String token = getToken();
		CategoryUpdateVO vo = new CategoryUpdateVO();
		vo.setId(9);
		vo.setParent(0);
		vo.setPriority(99);
		vo.setName("充电器");
		vo.setVisible(true);
		final ResponseEntity<String> body = helper.setPort(port).setUIPath("/api/category").builder().withToken(token)
				.build().put(null, vo, strRef);
		System.out.println(body);
		Assertions.assertEquals(200, body.getStatusCodeValue() );

	}

	@Test
	public void deleteCategory() {
		final String token = getToken();

		final ResponseEntity<String> body = helper.setPort(port).setUIPath("/api/category/19").builder().withToken(token)
		.build().delete(strRef);

		System.out.println(body);

		Assertions.assertEquals(400, body.getStatusCodeValue());

		final ResponseEntity<String> body2 = helper.setPort(port).setUIPath("/api/category/8").builder().withToken(token)
				.build().delete(strRef);

		Assertions.assertEquals(200, body2.getStatusCodeValue());


	}

	@Test
	public void getCategory() {

		final ResponseEntity<Result<CategoryReturnVO>> body = helper.setPort(port).setUIPath("/api/category/8").builder()
				.build().get(resVOReturnRef);
		final Result<CategoryReturnVO> category8 = body.getBody();
		System.out.println(body);
		Assertions.assertEquals(200, body.getStatusCodeValue());
		Assertions.assertEquals(8,body.getBody().getResult().getId());

	}


	@Test
	public void getAllCategory() {
		final ResponseEntity<Result<Page<CategoryReturnVO>>> body = helper.setPort(port).setUIPath("api/category/all/0/10").builder()
				.build().get(getParameterPageTypeRef());
		System.out.println(body);
		System.out.println(body.getBody().getResult().getItems().size());
		Assertions.assertEquals(200, body.getStatusCodeValue());
		Assertions.assertEquals(10,body.getBody().getResult().getItems().size());
		final ResponseEntity<Result<Page<CategoryReturnVO>>> body2= helper.setPort(port).setUIPath("api/category/all/1/10").builder()
				.build().get(getParameterPageTypeRef());
		System.out.println(body2.getBody().getResult());
		System.out.println(		body2.getBody().getResult().getItems().get(0).getName());
		Assertions.assertEquals(200, body2.getStatusCodeValue());
		Assertions.assertEquals(3,body2.getBody().getResult().getItems().size());

	}



	@Override
	protected ParameterizedTypeReference<Result<CategoryReturnVO>> getParameterTypeRef() {
		return new ParameterizedTypeReference<Result<CategoryReturnVO>>() {
		};
	}

	@Override
	protected ParameterizedTypeReference<Result<Page<CategoryReturnVO>>> getParameterPageTypeRef() {
		return new ParameterizedTypeReference<Result<Page<CategoryReturnVO>>>() {
		};
	}
}