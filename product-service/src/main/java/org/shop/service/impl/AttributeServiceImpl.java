package org.shop.service.impl;

import org.shop.common.util.BeanConvertor;
import org.shop.exception.AttributeOperationException;
import org.shop.mapper.AttrKeyDAOMapper;
import org.shop.mapper.AttrValueDAOMapper;
import org.shop.mapper.CategoryDAOMapper;
import org.shop.model.dao.AttrKeyDAO;
import org.shop.model.dao.AttrKeyDAOExample;
import org.shop.model.dao.AttrValueDAO;
import org.shop.model.dao.AttrValueDAOExample;
import org.shop.model.vo.*;
import org.shop.service.AttributeService;
import org.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AttributeServiceImpl implements AttributeService {

	@Autowired
	AttrKeyDAOMapper keyMapper;
	@Autowired
	AttrValueDAOMapper valueMapper;
	@Autowired
	CategoryService service;
	@Autowired
	CategoryDAOMapper mapper;
	@Override
	@Transactional
	public AttributeReturnVO addAttribute(AttributeAddVO attrVO) {
		//check if category exists
		final CategoryReturnVO category = service.getCategoryById(attrVO.getCategoryId());

		final AttrKeyDAO attrKeyDAO = BeanConvertor.convert(attrVO, AttrKeyDAO.class);
		attrKeyDAO.setCreatedTime(LocalDateTime.now());
		attrKeyDAO.setUpdatedTime(LocalDateTime.now());
		keyMapper.insert(attrKeyDAO);
		final AttributeReturnVO attributeReturnVO = BeanConvertor.convert(attrKeyDAO, AttributeReturnVO.class);
		if(attrKeyDAO.getEntryMethod().equals("selection")){
			final List<AttributeValueAddVO> valueVOList = attrVO.getValueVOList();
			final List<AttributeValueReturnVO> attributeValueReturnVOS = new ArrayList<>();
			for (AttributeValueAddVO vo : valueVOList) {
				vo.setAttrKey(attrKeyDAO.getId());
				attributeValueReturnVOS.add(this.addAttrValue(vo));
			}
			attributeReturnVO.setValues(attributeValueReturnVOS);
		}else {
			attributeReturnVO.setValues(Collections.emptyList());
		}
		return attributeReturnVO;
	}

	@Transactional
	@Override
	public AttributeValueReturnVO addAttrValue(AttributeValueAddVO vo) {
		final AttrValueDAO valDao = BeanConvertor.convert(vo, AttrValueDAO.class);
		final Integer attrKey = vo.getAttrKey();
		final AttrKeyDAO attrKeyDAO = keyMapper.selectByPrimaryKey(attrKey);
		if(attrKeyDAO == null )
			throw new AttributeOperationException("attr key: "+ attrKey +" doesn't exist");
		valueMapper.insertSelective(valDao);
		return BeanConvertor.convert(valDao, AttributeValueReturnVO.class);
	}

	@Transactional
	@Override
	public void deleteAttribute(Integer id) {
		keyMapper.deleteByPrimaryKey(id);
		AttrValueDAOExample example = new AttrValueDAOExample();
		example.createCriteria().andAttrKeyEqualTo(id);
		valueMapper.deleteByExample(example);
	}

	@Override
	public void deleteValue(Integer id) {
		valueMapper.deleteByPrimaryKey(id);
	}


	@Override
	public List<AttributeReturnVO> getAttributeByCategoryID(Integer categoryID) {
		AttrKeyDAOExample example = new AttrKeyDAOExample();
		example.createCriteria().andCategoryIdEqualTo(categoryID);
		List<AttrKeyDAO> attrKeyDAOS = keyMapper.selectByExample(example);
		final List<AttributeReturnVO> convert = BeanConvertor.convert(attrKeyDAOS, AttributeReturnVO.class);
		for (AttributeReturnVO returnVO : convert) {
			Integer id = returnVO.getId();
			AttrValueDAOExample valueEx = new AttrValueDAOExample();
			valueEx.createCriteria().andAttrKeyEqualTo(id);
			List<AttrValueDAO> attrValueDAOS = valueMapper.selectByExample(valueEx);
			returnVO.setValues(BeanConvertor.convert(attrValueDAOS, AttributeValueReturnVO.class));
		}
		return convert;
	}
}
