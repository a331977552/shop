package org.shop.service;

import org.shop.common.util.Page;
import org.shop.model.vo.*;

public interface AttributeService {


	AttributeReturnVO addAttribute(AttributeAddVO attrVO);

	AttributeValueReturnVO addAttrValue(AttributeValueAddVO valueVO);

	void deleteAttribute(Integer id);

	void deleteValue(Integer id);


	Page<AttributeReturnVO> getAttributeByExample(AttriQueryVO queryVO);

	AttributeReturnVO getAttribute(Integer id);

	AttributeReturnVO updateAttribute(AttributeUpdateVO vo);
}
