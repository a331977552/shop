package org.shop.service;

import org.shop.common.util.Page;
import org.shop.model.vo.BrandAddVO;
import org.shop.model.vo.BrandQueryVO;
import org.shop.model.vo.BrandReturnVO;
import org.shop.model.vo.BrandUpdateVO;

public interface BrandService {
	public BrandReturnVO addBrand(BrandAddVO category);

	void updateBrand(BrandUpdateVO category);

	void deleteBrandById(Integer id);

	BrandReturnVO getBrandById(Integer id);

	Page<BrandReturnVO> selectByExample(BrandQueryVO queryVO);
}
