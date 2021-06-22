package org.shop.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.shop.common.Result;
import org.shop.model.vo.ProductSpecAddVO;
import org.shop.model.vo.ProductSpecReturnVO;
import org.shop.test.utils.BaseControllerTest;
import org.shop.test.utils.TestHttpClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductSpecControllerTest extends BaseControllerTest<ProductSpecReturnVO> {




	@Test
	void addSpec() {

		ProductSpecAddVO addVO = new ProductSpecAddVO();
		addVO.setName("网络制式");
		addVO.setCategoryId(120);
		addVO.setSearchable(true);
		addVO.setEntryMethod("selection");
		addVO.setSelectType("multiple");
		addVO.setValue("3G\n4G\n5G\n4G");

		final String token = getToken();
		final ResponseEntity<Result<ProductSpecReturnVO>> post = helper.
				setUIPath("/api/product/spec").setPort(port).
				builder().withToken(token).build().post(addVO, resVOReturnRef);
		prettyPrint(post);
		Assertions.assertEquals(200,post.getStatusCodeValue());
		final ProductSpecReturnVO result = Objects.requireNonNull(post.getBody()).getResult();
		final Integer id = result.getId();
		Assertions.assertNotNull(id);
		Assertions.assertEquals("3G\n4G\n5G",result.getValue());

		this.deleteSpec(id,token);

	}



	@Test
	void getSpecs() {

		final String token = getToken();
		final ResponseEntity<Result<List<ProductSpecReturnVO>>> post = helper.
				setUIPath("/api/product/spec").setPort(port).
				builder().withToken(token).build().get(resListVOReturnRef);
		System.out.println(post);
		final List<ProductSpecReturnVO> result = post.getBody().getResult();
		Assertions.assertEquals(200,post.getStatusCodeValue());


	}




	void deleteSpec(Integer id, String token) {
		final TestHttpClient build = helper.setUIPath("/api/product/spec/" + id).setPort(port).builder().withToken(token).build();

		final  ResponseEntity<String> delete  = build.delete(strRef);
		Assertions.assertEquals(200, delete.getStatusCodeValue());
	}

	@Override
	protected ParameterizedTypeReference<Result<ProductSpecReturnVO>> getParameterTypeRef() {
		return new ParameterizedTypeReference<Result<ProductSpecReturnVO>>() {
		};
	}

}