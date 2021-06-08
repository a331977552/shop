package org.shop.service;

import org.shop.common.util.Page;
import org.shop.model.vo.*;

public interface CategoryService {

    CategoryReturnVO addCategory(CategoryAddVO categoryVO);
    CategoryReturnVO updateCategory(CategoryUpdateVO categoryVO);
    void deleteCategoryById(Integer id);
    CategoryReturnVO getCategoryById(Integer id);
    Page<CategoryReturnVO> getAll(CategoryQueryVO categoryQueryVO, Page<ProductQueryVO> of);
}