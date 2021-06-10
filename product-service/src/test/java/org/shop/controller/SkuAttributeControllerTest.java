package org.shop.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.shop.common.Result;
import org.shop.model.vo.AttributeAddVO;
import org.shop.model.vo.AttributeReturnVO;
import org.shop.model.vo.AttributeValueAddVO;
import org.shop.model.vo.AttributeValueReturnVO;
import org.shop.test.utils.BaseControllerTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SkuAttributeControllerTest extends BaseControllerTest<AttributeReturnVO> {




	@Test
	void addAttribute() {

		AttributeAddVO addVO = new AttributeAddVO();
		addVO.setName("颜色");
		addVO.setCategoryId(7);
		addVO.setSearchable(true);
		addVO.setEntryMethod("selection");
		addVO.setSelectType("single");
		addVO.setSearchtype("color");
		java.util.List<AttributeValueAddVO> list = new ArrayList<>();
		AttributeValueAddVO vo = new AttributeValueAddVO();
		vo.setValue("红色");
		AttributeValueAddVO vo2 = new AttributeValueAddVO();
		vo2.setValue("紫色");
		AttributeValueAddVO vo3 = new AttributeValueAddVO();
		vo3.setValue("黑色");

		list.add(vo);
		list.add(vo2);
		list.add(vo3);
		addVO.setValueVOList(list);

		final String token = getToken();
		final ResponseEntity<Result<AttributeReturnVO>> post = helper.
				setUIPath("/api/product/attr").setPort(port).
				builder().withToken(token).build().post(addVO, resVOReturnRef);
		System.out.println(post);
		final AttributeReturnVO result = post.getBody().getResult();
		Assertions.assertEquals(200,post.getStatusCodeValue());
		final Integer id = result.getId();
		Assertions.assertEquals(true,id !=null);
		final List<AttributeValueReturnVO> values = result.getValues();
		for (AttributeValueReturnVO value : values) {
			Assertions.assertEquals(true,value.getId() !=null);
		}

		this.deleteAttr(id,token);

	}

	@Test
	void addValue() {
		final String token = this.getToken();
		AttributeValueAddVO valueAddVO =new AttributeValueAddVO();
		valueAddVO.setAttrKey(3);
		valueAddVO.setValue("彩色");
		final ResponseEntity<Result<AttributeValueReturnVO>> post = helper.
				setUIPath("/api/product/attr/value").setPort(port).
				builder().withToken(token).build().post(valueAddVO, getValueParameterTypeRef());
		System.out.println(post);
		final AttributeValueReturnVO result = post.getBody().getResult();
		Assertions.assertEquals(200,post.getStatusCodeValue());
		final Integer id = result.getId();
		Assertions.assertNotNull(id);
		this.deleteValue(id,token);
	}

	void deleteValue(Integer id,String token) {
		final ResponseEntity<Result<String>> post = helper.
				setUIPath("/api/product/attr/value/"+id).setPort(port).
				builder().withToken(token).build().delete(strResultRef);
		System.out.println(post);
		Assertions.assertEquals(200,post.getStatusCodeValue());

	}

	void deleteAttr(Integer id,String token) {
		final ResponseEntity<String> delete = helper.setUIPath("/api/product/attr/"+id).setPort(port).builder().withToken(token).build()
				.delete(strRef);
		Assertions.assertEquals(200, delete.getStatusCodeValue());
	}

	@Override
	protected ParameterizedTypeReference<Result<AttributeReturnVO>> getParameterTypeRef() {
		return new ParameterizedTypeReference<Result<AttributeReturnVO>>() {
		};
	}

	protected ParameterizedTypeReference<Result<AttributeValueReturnVO>> getValueParameterTypeRef() {
		return new ParameterizedTypeReference<Result<AttributeValueReturnVO>>() {
		};
	}
}