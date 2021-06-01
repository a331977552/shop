package org.shop.service;

import org.shop.common.util.Page;
import org.shop.model.vo.ProductAddVO;
import org.shop.model.vo.ProductQueryVO;
import org.shop.model.vo.ProductReturnVO;
import org.shop.model.vo.ProductUpdateVO;

import java.util.List;

public interface ProductService {
	ProductReturnVO addProduct(ProductAddVO product);

	void updateProductSelective(ProductUpdateVO product);

	void updateAllProducts(Iterable<ProductUpdateVO> products);

	void updateProduct(ProductUpdateVO product);

	void deleteProductById(String id);

	ProductReturnVO getProductById(String id);

	long count(ProductQueryVO example);

	List<ProductReturnVO> getProductByIds(List<String> ids);

	Page<ProductReturnVO> getProductsByCategoryId(Integer id,Page page);

	long countProductsByCategoryId(Integer id);

	List<ProductReturnVO> getAll();

	Page<ProductReturnVO> getAll(ProductQueryVO example, Page<ProductQueryVO> page);

	ProductReturnVO.SkuReturnVO getProductSKUById(Integer id);
}
