package org.shop.service.imp;

import org.shop.mapper.AttrKeyDAOMapper;
import org.shop.mapper.AttrValueDAOMapper;
import org.shop.model.vo.AttributeAddVO;
import org.shop.model.vo.AttributeReturnVO;
import org.shop.model.vo.AttributeValueAddVO;
import org.shop.model.vo.AttributeValueReturnVO;
import org.shop.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttributeServiceImpl implements AttributeService {

	@Autowired
	AttrKeyDAOMapper keyMapper;
	@Autowired
	AttrValueDAOMapper valueMapper;

	@Override
	public AttributeReturnVO addAttribute(AttributeAddVO attrVO) {

		return null;
	}

	@Override
	public AttributeValueReturnVO addAttrValue(AttributeValueAddVO valueVO) {
		return null;
	}

	@Override
	public void deleteAttribute(Integer id) {

	}

	@Override
	public void deleteValue(Integer id) {

	}

	@Override
	public List<AttributeReturnVO> getAttributeByCategoryID(String categoryID) {
		return null;
	}
}
