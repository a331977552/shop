package org.shop.service.imp;

import org.shop.common.util.BeanConvertor;
import org.shop.mapper.AttrKeyDAOMapper;
import org.shop.mapper.AttrValueDAOMapper;
import org.shop.model.dao.AttrKeyDAO;
import org.shop.model.dao.AttrKeyDAOExample;
import org.shop.model.dao.AttrValueDAO;
import org.shop.model.dao.AttrValueDAOExample;
import org.shop.model.vo.AttributeAddVO;
import org.shop.model.vo.AttributeReturnVO;
import org.shop.model.vo.AttributeValueAddVO;
import org.shop.model.vo.AttributeValueReturnVO;
import org.shop.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttributeServiceImpl implements AttributeService {

	@Autowired
	AttrKeyDAOMapper keyMapper;
	@Autowired
	AttrValueDAOMapper valueMapper;

	@Override
	@Transactional
	public AttributeReturnVO addAttribute(AttributeAddVO attrVO) {

		final AttrKeyDAO attrKeyDAO = new AttrKeyDAO();
		attrKeyDAO.setCategoryid(attrVO.getCategoryid());
		attrKeyDAO.setName(attrVO.getName());
		keyMapper.insertSelective(attrKeyDAO);

		final AttributeReturnVO attributeReturnVO = BeanConvertor.convert(attrKeyDAO, AttributeReturnVO.class);
		final List<AttributeValueAddVO> valueVOList = attrVO.getValueVOList();
		final List<AttributeValueReturnVO> attributeValueReturnVOS = new ArrayList<>();
		for (AttributeValueAddVO vo : valueVOList) {
			vo.setAttrKey(attrKeyDAO.getId());
			attributeValueReturnVOS.add(this.addAttrValue(vo));
		}
		attributeReturnVO.setValues(attributeValueReturnVOS);
		return attributeReturnVO;
	}

	@Transactional
	@Override
	public AttributeValueReturnVO addAttrValue(AttributeValueAddVO vo) {
		final AttrValueDAO valDao = BeanConvertor.convert(vo, AttrValueDAO.class);
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
		example.createCriteria().andCategoryidEqualTo(categoryID);
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
