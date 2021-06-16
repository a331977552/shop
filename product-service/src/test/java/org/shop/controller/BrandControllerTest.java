package org.shop.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.shop.common.Result;
import org.shop.common.util.Page;
import org.shop.model.vo.BrandAddVO;
import org.shop.model.vo.BrandReturnVO;
import org.shop.test.utils.BaseControllerTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BrandControllerTest extends BaseControllerTest<BrandReturnVO> {

	@Test
	void testAddBrand(){
		this.addBrand();
	}

	BrandReturnVO addBrand() {
		final BrandAddVO brandAddVO = new BrandAddVO();
		brandAddVO.setName("海尔");
		brandAddVO.setPriority(1);
		brandAddVO.setCapitalLetter("M");
		brandAddVO.setVisible(true);
		brandAddVO.setIsManufacturer(true);
		final String token = getToken();
		ResponseEntity<Result<BrandReturnVO>> added = helper.builder().withToken(token).setUipath("/api/brand").setPort(port)
				.build().post(brandAddVO, resVOReturnRef);
		prettyPrint(added);
		Assertions.assertEquals(200, added.getStatusCodeValue() );
		return added.getBody().getResult();
	}

	@Test
	void updateBrand() {
		final BrandReturnVO brand = getBrand(1);
		prettyPrint(brand);
		final String token = getToken();
		brand.setDescription("更新了描述");
		brand.setPriority(99);
		final ResponseEntity<String> updated = helper.builder().setUipath("/api/brand").setPort(port)
				.withToken(token).build().put(null, brand,strRef);
		Assertions.assertEquals(200, updated.getStatusCodeValue() );
		final BrandReturnVO brand1 = getBrand(1);
		Assertions.assertEquals("更新了描述",brand1.getDescription());
	}


	@Test
	void deleteBrand() {
		final BrandReturnVO brandReturnVO = this.addBrand();
		System.out.println(brandReturnVO);
		final String token = getToken();
		final ResponseEntity<String> post = helper.builder().withToken(token).setUipath("/api/brand/"+brandReturnVO.getId()).setPort(port)
				.build().delete();
		prettyPrint(post);
	}

	@Test
	void getBrand() {
		final BrandReturnVO brand = getBrand(1);
		Assertions.assertNotNull(brand);
	}

	BrandReturnVO getBrand(Integer id){
		final ResponseEntity<Result<BrandReturnVO>> post = helper.builder().setUipath("/api/brand/"+id).setPort(port)
				.build().get(resVOReturnRef);
		prettyPrint(post);
		return post.getBody().getResult();
	}

	@Test
	void getAllBrand() {
		final ResponseEntity<Result<Page<BrandReturnVO>>> post = helper.builder().setUipath("/api/brand/all").setPort(port)
				.build().get(resPageVOReturnRef);
		prettyPrint(post);
	}

	@Override
	protected ParameterizedTypeReference<Result<BrandReturnVO>> getParameterTypeRef() {
		return new ParameterizedTypeReference<Result<BrandReturnVO>>() {
		};
	}

	@Override
	protected ParameterizedTypeReference<Result<Page<BrandReturnVO>>> getParameterPageTypeRef() {
		return new ParameterizedTypeReference<Result<Page<BrandReturnVO>>>() {
		};
	}
}