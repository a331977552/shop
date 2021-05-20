package org.shop.service.imp;

import org.shop.common.util.BeanConvertor;
import org.shop.mapper.CategoryDAOMapper;
import org.shop.model.dao.CategoryDAO;
import org.shop.model.dao.CategoryDAOExample;
import org.shop.model.vo.CategoryVO;
import org.shop.service.CategoryService;
import org.shop.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CategoryServiceImpl implements CategoryService {

	private final CategoryDAOMapper mapper;
	@Autowired
	private ProductService productService;

	public CategoryServiceImpl(CategoryDAOMapper mapper) {
		this.mapper = mapper;
	}





	CategoryDAO convertToDAO(CategoryVO vo){
		CategoryDAO category=new CategoryDAO();
		BeanUtils.copyProperties(vo,category);
		return category;
	}

	@Override
	public CategoryVO addCategory(CategoryVO vo) {
		vo.setId(null);
		CategoryDAO categoryDAO = convertToDAO(vo);
		mapper.insert(categoryDAO);
		return vo;
	}

	@Override
	public void updateCategorySelective(CategoryVO CategoryVO) {

	}

	@Override
	public void updateAllCategories(Iterable<CategoryVO> Categories) {

	}

	@Override
	public void updateCategory(CategoryVO vo) {
		CategoryDAO category = convertToDAO(vo);
		int res = mapper.updateByPrimaryKey(category);

	}

	//todo check if it's got children or parent
	@Override
	public void deleteCategoryById(Integer id) {
		CategoryDAOExample example = new CategoryDAOExample();
		example.createCriteria().andIdEqualTo(id);
		int res = mapper.deleteByPrimaryKey(id);
	}

	@Override
	public CategoryVO getCategoryById(Integer id) {
		CategoryDAO one = mapper.selectByPrimaryKey(id);
		Optional<CategoryVO> convert = BeanConvertor.convert(Optional.of(one), CategoryVO.class);
		return convert.get();
	}


	@Override
	public List<CategoryVO> getCategoryByIds(List<Integer> ids) {
		return  ids.stream().map(this::getCategoryById).collect(Collectors.toList());
	}

	@Override
	public List<CategoryVO> getAll() {
		List<CategoryDAO> categories = mapper.selectByExample(new CategoryDAOExample());
		return BeanConvertor.convert(categories, CategoryVO.class);
	}


}
