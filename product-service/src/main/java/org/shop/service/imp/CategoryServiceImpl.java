package org.shop.service.imp;

import org.shop.common.util.*;
import org.shop.exception.CategoryDeletionFailureException;
import org.shop.exception.MaliciousAttackException;
import org.shop.mapper.CategoryDAOMapper;
import org.shop.model.dao.CategoryDAO;
import org.shop.model.dao.CategoryDAOExample;
import org.shop.model.vo.*;
import org.shop.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService, ModelConvertor<CategoryDAO, CategoryAddVO, CategoryReturnVO> {

	private final CategoryDAOMapper mapper;

	public CategoryServiceImpl(CategoryDAOMapper mapper) {
		this.mapper = mapper;
	}


	CategoryDAO convertToDAO(CategoryAddVO vo) {
		CategoryDAO category = new CategoryDAO();
		BeanUtils.copyProperties(vo, category);
		return category;
	}

	@Override
	@Transactional
	public CategoryReturnVO addCategory(CategoryAddVO vo) {
		CategoryDAO categoryDAO = convertToDAO(vo);
		categoryDAO.setIsleaf(true);
		if (categoryDAO.getParent() == 0) {
			categoryDAO.setLevel(0);
		} else {
			final CategoryReturnVO categoryById = getCategoryById(categoryDAO.getParent());
			categoryDAO.setLevel(categoryById.getLevel() + 1);
			categoryById.setIsleaf(false);
			final CategoryUpdateVO convert = BeanConvertor.convert(categoryById, CategoryUpdateVO.class);
			updateCategory(convert);
		}
		categoryDAO.setCreatedTime(LocalDateTime.now());
		categoryDAO.setUpdatedTime(LocalDateTime.now());
		mapper.insert(categoryDAO);
		return convertToReturnVO(categoryDAO, CategoryReturnVO::new);
	}


	@Override
	@Transactional
	public CategoryReturnVO updateCategory(CategoryUpdateVO vo) {
		final CategoryDAO convert = BeanConvertor.convert(vo, CategoryDAO.class);
		final CategoryDAO categoryDAO = mapper.selectByPrimaryKey(vo.getId());
		int originalParent = categoryDAO.getParent();
		int newParentID = vo.getParent();
		final CategoryDAO parent = mapper.selectByPrimaryKey(originalParent);
		if (newParentID != originalParent) {
			if (originalParent != 0) {
				CategoryDAOExample example = new CategoryDAOExample();
				example.createCriteria().andParentEqualTo(originalParent);
				final long cateCountWithSameParents = mapper.countByExample(example);
				//update parent
				if (cateCountWithSameParents == 1) {
					parent.setIsleaf(true);
					parent.setUpdatedTime(LocalDateTime.now());
					mapper.updateByPrimaryKeySelective(parent);
				}
			}
			if (newParentID != 0) {
				final CategoryDAO newParent = mapper.selectByPrimaryKey(newParentID);
				newParent.setIsleaf(false);
				mapper.updateByPrimaryKeySelective(newParent);
				cascadeUpdate(convert, newParent.getLevel() + 1);
			} else {
				cascadeUpdate(convert, 0);
			}
		} else {
			convert.setLevel(null);
			convert.setIsleaf(null);
			mapper.updateByPrimaryKeySelective(convert);
		}
		return convertToReturnVO(convert, CategoryReturnVO::new);
	}

	private void cascadeUpdate(CategoryDAO child, int newLevel) {

		child.setLevel(newLevel);
		mapper.updateByPrimaryKeySelective(child);
		CategoryDAOExample example = new CategoryDAOExample();
		example.createCriteria().andParentEqualTo(child.getId());
		final List<CategoryDAO> grandChildren = mapper.selectByExample(example);
		grandChildren.forEach(grandChild -> {
			cascadeUpdate(grandChild, child.getLevel() + 1);
		});
	}

	//todo check if it's got children or parent
	@Override
	public void deleteCategoryById(Integer id) {
		CategoryDAOExample example = new CategoryDAOExample();
		example.createCriteria().andParentEqualTo(id);

		final long childrenCount = mapper.countByExample(example);
		if (childrenCount>0)
			throw new CategoryDeletionFailureException("无法删除目录,该目录还有子目录");
		final CategoryDAO categoryDAO = mapper.selectByPrimaryKey(id);
		final Integer parentID = categoryDAO.getParent();
		example.clear();
		example.createCriteria().andParentEqualTo(parentID);
		final long countWithSameParent = mapper.countByExample(example);

		if(countWithSameParent == 1 ){
			final CategoryDAO parent = mapper.selectByPrimaryKey(parentID);
			parent.setIsleaf(true);
			parent.setUpdatedTime(LocalDateTime.now());
			mapper.updateByPrimaryKey(parent);
		}

		mapper.deleteByPrimaryKey(id);


	}

	@Override
	public CategoryReturnVO getCategoryById(Integer id) {
		CategoryDAO one = mapper.selectByPrimaryKey(id);
		if (one == null) {
			final CategoryReturnVO categoryReturnVO = new CategoryReturnVO();
			categoryReturnVO.setName("已删除");
			categoryReturnVO.setId(-1);
		}
		return convertToReturnVO(one, CategoryReturnVO::new);
	}

	@Override
	public Page<CategoryReturnVO> getAll(CategoryQueryVO categoryQueryVO, Page<ProductQueryVO> page) {
		final CategoryDAOExample categoryDAOExample = new CategoryDAOExample();
		final CategoryDAOExample.Criteria criteria = categoryDAOExample.createCriteria();
		if (TextUtil.hasText(categoryQueryVO.getName())) {
			criteria.andNameLike("%" + categoryQueryVO.getName() + "%");
		}

		if (categoryQueryVO.getParent()!=null) {
			criteria.andParentEqualTo(categoryQueryVO.getParent());
		}
		if(!EntityUtils.containsFieldWithName(CategoryQueryVO.class,page.getOrderBy()) && !TextUtil.isEmpty(page.getOrderBy()) ){
			throw new MaliciousAttackException("get off");
		}
		String orderBy = Optional.ofNullable(page.getOrderBy()).orElse("priority desc ");
		categoryDAOExample.setOrderByClause(orderBy
				/*+ " limit " + page.getPageSize() + " offset " + page.getOffset()*/
		);

		List<CategoryDAO> categories = mapper.selectByExample(categoryDAOExample);
		final long count = mapper.countByExample(categoryDAOExample);
		return 	Page.createFrom(page, count,BeanConvertor.convert(categories, CategoryReturnVO.class));
	}


}
