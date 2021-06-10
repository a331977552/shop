package org.shop.service;

import org.shop.common.util.Page;
import org.shop.model.vo.ProductSpecAddVO;
import org.shop.model.vo.ProductSpecQueryVO;
import org.shop.model.vo.ProductSpecReturnVO;
import org.shop.model.vo.ProductSpecUpdateVO;

public interface ProductSpecService {


	ProductSpecReturnVO addSpec(ProductSpecAddVO vo);

//	AttributeValueReturnVO addSpecValue(AttributeValueAddVO valueVO);

	void deleteSpec(Integer id);

//	void deleteValue(Integer id);

	Page<ProductSpecReturnVO> getSpecByCategoryExample(ProductSpecQueryVO categoryID);

	void updateSpec(ProductSpecUpdateVO vo);

	ProductSpecReturnVO getSpecById(Integer id);
}
