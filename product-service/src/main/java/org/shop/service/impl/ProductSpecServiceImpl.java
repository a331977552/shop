package org.shop.service.impl;

import org.shop.common.util.BeanConvertor;
import org.shop.common.util.Page;
import org.shop.common.util.TextUtil;
import org.shop.exception.ProductException;
import org.shop.mapper.ProductSpecDAOMapper;
import org.shop.model.dao.ProductSpecDAO;
import org.shop.model.dao.ProductSpecDAOExample;
import org.shop.model.vo.ProductSpecAddVO;
import org.shop.model.vo.ProductSpecQueryVO;
import org.shop.model.vo.ProductSpecReturnVO;
import org.shop.model.vo.ProductSpecUpdateVO;
import org.shop.service.ProductSpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductSpecServiceImpl implements ProductSpecService {
	@Autowired
	ProductSpecDAOMapper mapper;
	@Override
	public ProductSpecReturnVO addSpec(ProductSpecAddVO vo) {
		if (vo.getSearchable() ==null)
			vo.setSearchable(true);
		final ProductSpecDAO convert = BeanConvertor.convert(vo, ProductSpecDAO.class);
		convert.setCreatedTime(LocalDateTime.now());
		convert.setUpdatedTime(LocalDateTime.now());
		if("custom".equals(convert.getEntryMethod())) {
			convert.setValue(null);
		}else {
			if(TextUtil.isEmpty(convert.getValue()))
				throw new ProductException("产品规格不能为空");
			final String collect = distinctValue(convert.getValue());
			convert.setValue(collect);

		}
		mapper.insert(convert);
		return BeanConvertor.convert(convert, ProductSpecReturnVO.class);
	}

	private String distinctValue(String  value) {
		final String[] split = value.split("\n");
		final Stream<String> distinct = Arrays.stream(split).distinct();
		final String collect = distinct.collect(Collectors.joining("\n"));
		return collect;
	}


	@Override
	public void deleteSpec(Integer id) {
		mapper.deleteByPrimaryKey(id);
	}

	@Override
	public Page<ProductSpecReturnVO> getSpecByCategoryExample(ProductSpecQueryVO queryVO) {
		ProductSpecDAOExample ex=new ProductSpecDAOExample();

		final ProductSpecDAOExample.Criteria criteria = ex.createCriteria();
		if(queryVO.getCategoryId()!=null){
			criteria.andCategoryIdEqualTo(queryVO.getCategoryId());
		}
		if(TextUtil.hasText(queryVO.getName())){
			criteria.andNameLike("%"+queryVO.getName()+"%");
		}

		final List<ProductSpecDAO> list = mapper.selectByExample(ex);
		return Page.createSinglePage(BeanConvertor.convert(list, ProductSpecReturnVO.class));
	}

	@Override
	public void updateSpec(ProductSpecUpdateVO vo) {
		final ProductSpecDAO convert = BeanConvertor.convert(vo, ProductSpecDAO.class);
		if("custom".equals(convert.getEntryMethod())) {
			convert.setValue(null);
		}else {
			if(TextUtil.isEmpty(convert.getValue()))
				throw new ProductException("产品规格不能为空");
			final String collect = distinctValue(convert.getValue());
			convert.setValue(collect);
		}

		mapper.updateByPrimaryKey(convert);
	}

	@Override
	public ProductSpecReturnVO getSpecById(Integer id) {
		return BeanConvertor.convert(mapper.selectByPrimaryKey(id),ProductSpecReturnVO.class);
	}
}
