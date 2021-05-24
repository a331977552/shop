package org.shop.service;

import org.shop.model.vo.AttributeAddVO;
import org.shop.model.vo.AttributeReturnVO;
import org.shop.model.vo.AttributeValueAddVO;
import org.shop.model.vo.AttributeValueReturnVO;

import java.util.List;

public interface AttributeService {


	AttributeReturnVO addAttribute(AttributeAddVO attrVO);

	AttributeValueReturnVO addAttrValue(AttributeValueAddVO valueVO);

	void deleteAttribute(Integer id);

	void deleteValue(Integer id);

	List<AttributeReturnVO> getAttributeByCategoryID(String categoryID);

}
