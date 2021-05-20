package org.shop.service;

import org.shop.model.vo.CategoryVO;

import java.util.List;

public interface CategoryService {

    CategoryVO addCategory(CategoryVO categoryVO);
    void updateCategorySelective(CategoryVO categoryVO);
    void updateAllCategories(Iterable<CategoryVO> categories);
    void updateCategory(CategoryVO CategoryVO);
    void deleteCategoryById(Integer id);
    CategoryVO getCategoryById(Integer id);
    List<CategoryVO> getCategoryByIds(List<Integer> ids);
    List<CategoryVO> getAll();
}