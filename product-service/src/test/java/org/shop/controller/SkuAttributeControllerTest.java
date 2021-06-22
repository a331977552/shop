package org.shop.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.shop.common.Result;
import org.shop.common.util.BeanConvertor;
import org.shop.common.util.Page;
import org.shop.model.vo.*;
import org.shop.service.AttributeService;
import org.shop.test.utils.BaseControllerTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SkuAttributeControllerTest extends BaseControllerTest<AttributeReturnVO> {


	@Autowired
	AttributeService service;


	@Test
	void addAttribute() {

		AttributeAddVO addVO = new AttributeAddVO();
		addVO.setName("品质");
		addVO.setCategoryId(120);
		addVO.setSearchable(true);
		addVO.setEntryMethod("selection");
		addVO.setSelectType("single");
		addVO.setSearchtype("color");
		java.util.List<AttributeValueAddVO> list = new ArrayList<>();
		AttributeValueAddVO vo = new AttributeValueAddVO();
		vo.setValue("低档");
		AttributeValueAddVO vo2 = new AttributeValueAddVO();
		vo2.setValue("高档");
		AttributeValueAddVO vo3 = new AttributeValueAddVO();
		vo3.setValue("高档");

		list.add(vo);
		list.add(vo2);
		list.add(vo3);
		addVO.setValues(list);

		final String token = getToken();
		final ResponseEntity<Result<AttributeReturnVO>> post = helper.
				setUIPath("/api/category/attr").setPort(port).
				builder().withToken(token).build().post(addVO, resVOReturnRef);
		prettyPrint(post);
		final AttributeReturnVO result = Objects.requireNonNull(post.getBody()).getResult();
		Assertions.assertEquals(200,post.getStatusCodeValue());
		final Integer id = result.getId();
		Assertions.assertNotNull(id);
		final List<AttributeValueReturnVO> values = result.getValues();
		for (AttributeValueReturnVO value : values) {
			Assertions.assertNotNull(value.getId());
		}

//		this.deleteAttr(id,token);

	}



	void deleteAttr(Integer id,String token) {
		final ResponseEntity<String> delete = helper.setUIPath("/api/category/attr/"+id).setPort(port).builder().withToken(token).build()
				.delete(strRef);
		Assertions.assertEquals(200, delete.getStatusCodeValue());
	}


	@Test
	@Rollback
	@Transactional
	void update(){

		final AttributeReturnVO attribute = service.getAttribute(3);
		attribute.setName("代销方式");
		final AttributeUpdateVO convert = BeanConvertor.convert(attribute, AttributeUpdateVO.class);
		convert.setCategoryId(7);
		convert.setSort(87);
		convert.setEntryMethod("selection");
		List<AttributeValueReturnVO> v=new ArrayList<>();
		AttributeValueReturnVO e=new AttributeValueReturnVO();
		AttributeValueReturnVO e2=new AttributeValueReturnVO();
		e.setValue("小明");
		e2.setValue("小明");

		v.add(e);
		v.add(e2);
		convert.setValues(v);
		final AttributeReturnVO attributeReturnVO = service.updateAttribute(convert);
		prettyPrint(attributeReturnVO);
		final AttributeReturnVO attributeByExample = service.getAttribute(3);
		prettyPrint(attributeByExample);
		Assertions.assertEquals(attribute.getName(), attributeByExample.getName()) ;
	}

	@Test
	void getByExample(){
		AttriQueryVO queryVO =new AttriQueryVO();
		queryVO.setCategoryId(7);
		queryVO.setName("规格");
		final Page<AttributeReturnVO> attributeByExample = service.getAttributeByExample(queryVO);
		prettyPrint(attributeByExample);
		Assertions.assertTrue(attributeByExample.isEmpty());
		queryVO.setName("");
		final Page<AttributeReturnVO> attributeByExample1 = service.getAttributeByExample(queryVO);
		Assertions.assertEquals(2, attributeByExample1.getItems().size());


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