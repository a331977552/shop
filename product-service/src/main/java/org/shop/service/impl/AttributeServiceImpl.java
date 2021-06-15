package org.shop.service.impl;

import org.shop.common.util.BeanConvertor;
import org.shop.common.util.Page;
import org.shop.common.util.TextUtil;
import org.shop.exception.AttributeOperationException;
import org.shop.exception.CategoryException;
import org.shop.mapper.AttrKeyDAOMapper;
import org.shop.mapper.AttrValueDAOMapper;
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

	@Override
	@Transactional
	public AttributeReturnVO addAttribute(AttributeAddVO attrVO) {
		//check if category exists
		CategoryQueryVO query = new CategoryQueryVO();
		query.setId(attrVO.getCategoryId());
		final long category = service.countByExample(query);
		if (category == 0)
			throw new CategoryException("分类ID" + attrVO.getCategoryId() + " 为空");
		final AttrKeyDAO attrKeyDAO = BeanConvertor.convert(attrVO, AttrKeyDAO.class);
		attrKeyDAO.setCreatedTime(LocalDateTime.now());
		attrKeyDAO.setUpdatedTime(LocalDateTime.now());
		keyMapper.insert(attrKeyDAO);
		final AttributeReturnVO attributeReturnVO = BeanConvertor.convert(attrKeyDAO, AttributeReturnVO.class);
		if (attrKeyDAO.getEntryMethod().equals("selection")) {
			final List<AttributeValueReturnVO> attributeValueReturnVOS = new ArrayList<>();
			for (AttributeValueAddVO vo : attrVO.getValues()) {
				vo.setAttrKey(attrKeyDAO.getId());
				attributeValueReturnVOS.add(this.addAttrValue(vo));
			}
			attributeReturnVO.setValues(attributeValueReturnVOS);
		} else {
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
		if (attrKeyDAO == null)
			throw new AttributeOperationException("attr key: " + attrKey + " doesn't exist");
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
	public Page<AttributeReturnVO> getAttributeByExample(AttriQueryVO queryVO) {
		AttrKeyDAOExample example = new AttrKeyDAOExample();
		final AttrKeyDAOExample.Criteria criteria = example.createCriteria();
		if (queryVO.getCategoryId() != null) {
			criteria.andCategoryIdEqualTo(queryVO.getCategoryId());
		}
		if (TextUtil.hasText(queryVO.getName())) {
			criteria.andNameEqualTo(queryVO.getName());
		}
		List<AttrKeyDAO> attrKeyDAOS = keyMapper.selectByExample(example);
		final List<AttributeReturnVO> convert = BeanConvertor.convert(attrKeyDAOS, AttributeReturnVO.class);
		for (AttributeReturnVO returnVO : convert) {
			final List<AttrValueDAO> valuesByKeyID = getValuesByKeyID(returnVO.getId());
			returnVO.setValues(BeanConvertor.convert(valuesByKeyID, AttributeValueReturnVO.class));
		}

		return Page.createSinglePage(convert);
	}

	private List<AttrValueDAO> getValuesByKeyID(Integer id) {
		AttrValueDAOExample valueEx = new AttrValueDAOExample();
		valueEx.createCriteria().andAttrKeyEqualTo(id);
		return valueMapper.selectByExample(valueEx);
	}


	@Override
	public AttributeReturnVO getAttribute(Integer id) {
		final AttrKeyDAO attr = keyMapper.selectByPrimaryKey(id);
		final List<AttrValueDAO> valuesByKeyID = getValuesByKeyID(attr.getId());
		System.out.println(valuesByKeyID);
		final AttributeReturnVO convert = BeanConvertor.convert(attr, AttributeReturnVO.class);
		convert.setValues(BeanConvertor.convert(valuesByKeyID, AttributeValueReturnVO.class));
		return convert;
	}

	@Override
	@Transactional
	public AttributeReturnVO updateAttribute(AttributeUpdateVO vo) {
		final AttrKeyDAO convert = BeanConvertor.convert(vo, AttrKeyDAO.class);
		convert.setUpdatedTime(LocalDateTime.now());
		keyMapper.updateByPrimaryKeySelective(convert);
		final List<AttrValueDAO> valuesByKeyID = getValuesByKeyID(vo.getId());
		valuesByKeyID.forEach(va -> valueMapper.deleteByPrimaryKey(va.getId()));

		if ("selection".equals(vo.getEntryMethod())) {
			final List<AttributeValueReturnVO> values = vo.getValues();
			final List<AttrValueDAO> valDaos = BeanConvertor.convert(values, AttrValueDAO.class);
			valDaos.forEach((valDao) -> {
				valDao.setUpdatedTime(LocalDateTime.now());
				valDao.setCreatedTime(LocalDateTime.now());
				valDao.setAttrKey(vo.getId());
				valueMapper.insert(valDao);
			});
			final List<AttributeValueReturnVO> convert1 = BeanConvertor.convert(valDaos, AttributeValueReturnVO.class);
			vo.setValues(convert1);
		} else {
			vo.setValues(new ArrayList<>());
		}

		return BeanConvertor.convert(vo, AttributeReturnVO.class);
	}
}
