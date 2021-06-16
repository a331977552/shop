package org.shop.service.impl;

import org.shop.common.util.BeanConvertor;
import org.shop.common.util.ModelConvertor;
import org.shop.common.util.Page;
import org.shop.common.util.TextUtil;
import org.shop.mapper.BrandDAOMapper;
import org.shop.model.dao.BrandDAO;
import org.shop.model.dao.BrandDAOExample;
import org.shop.model.vo.BrandAddVO;
import org.shop.model.vo.BrandQueryVO;
import org.shop.model.vo.BrandReturnVO;
import org.shop.model.vo.BrandUpdateVO;
import org.shop.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BrandServiceImpl implements BrandService, ModelConvertor<BrandDAO,BrandAddVO,BrandReturnVO> {
	@Autowired
	BrandDAOMapper mapper;
	@Override
	public BrandReturnVO addBrand(BrandAddVO category) {
		final BrandDAO brandDAO = convertToDAO(category, BrandDAO::new);
		brandDAO.setCreatedTime(LocalDateTime.now());
		brandDAO.setUpdatedTime(LocalDateTime.now());
		mapper.insertSelective(brandDAO);
		return convertToReturnVO(brandDAO, BrandReturnVO::new);
	}

	@Override
	public void updateBrand(BrandUpdateVO category) {
		final BrandDAO convert = BeanConvertor.convert(category, BrandDAO.class);
		convert.setUpdatedTime(LocalDateTime.now());
		mapper.updateByPrimaryKeySelective(convert);
	}

	@Override
	public void deleteBrandById(Integer id) {
		mapper.deleteByPrimaryKey(id);
	}

	@Override
	public BrandReturnVO getBrandById(Integer id) {
		return convertToReturnVO(mapper.selectByPrimaryKey(id),BrandReturnVO::new);
	}

	@Override
	public Page<BrandReturnVO> selectByExample(BrandQueryVO queryVO) {
		BrandDAOExample example = new BrandDAOExample();
		final BrandDAOExample.Criteria criteria = example.createCriteria();
		if(TextUtil.hasText(queryVO.getName())){
			criteria.andNameLike("%"+queryVO.getName()+"%");
		}
		if(TextUtil.hasText(queryVO.getCapitalLetter())){
			criteria.andCapitalLetterEqualTo(queryVO.getCapitalLetter());
		}
		final List<BrandDAO> brandDAOS = mapper.selectByExample(example);
		final List<BrandReturnVO> convert = BeanConvertor.convert(brandDAOS, BrandReturnVO.class);
		return Page.createSinglePage(convert);
	}
}
