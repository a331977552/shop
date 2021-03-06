package org.shop.service.impl;

import lombok.extern.log4j.Log4j2;
import org.shop.common.util.BeanConvertor;
import org.shop.common.util.Page;
import org.shop.common.util.TextUtil;
import org.shop.common.util.UUIDUtils;
import org.shop.exception.ProductException;
import org.shop.exception.ProductUpdateException;
import org.shop.mapper.BrandDAOMapper;
import org.shop.mapper.ProductDAOMapper;
import org.shop.mapper.SkuDAOMapper;
import org.shop.model.dao.*;
import org.shop.model.vo.ProductAddVO;
import org.shop.model.vo.ProductQueryVO;
import org.shop.model.vo.ProductReturnVO;
import org.shop.model.vo.ProductUpdateVO;
import org.shop.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {


	private final ProductDAOMapper mapper;
	private final SkuDAOMapper skuMapper;
	private final BrandDAOMapper brandDAOMapper;

	public ProductServiceImpl(ProductDAOMapper mapper, SkuDAOMapper skuMapper, BrandDAOMapper brandDAOMapper) {
		this.mapper = mapper;
		this.skuMapper = skuMapper;
		this.brandDAOMapper = brandDAOMapper;
	}

	@Transactional
	@Override
	public ProductReturnVO addProduct(ProductAddVO productVO) {
		ProductDAO product = convertToDAO(productVO);
		product.setId(UUIDUtils.generateID());
		product.setStatus(product.getSales() == null ? "ON_SALE" : product.getStatus());
		product.setSales(0);
		product.setReviewStatus("PASSED");
		mapper.insertSelective(product);
		//map product into  categories
		List<ProductAddVO.SkuAddVO> skuList = Optional.ofNullable(productVO.getSkuList()).orElse(new ArrayList<>());
		List<SkuDAO> convert = BeanConvertor.convert(skuList, SkuDAO.class);
		for (SkuDAO skuDAO : convert) {
			skuDAO.setSales(0);
			skuDAO.setProductId(product.getId());
			skuMapper.insertSelective(skuDAO);
		}
		return convertToReturnVO(product, convert);
	}

	ProductDAO convertToDAO(ProductAddVO vo) {
		ProductDAO product = new ProductDAO();
		BeanUtils.copyProperties(vo, product);
		return product;
	}

	ProductDAO convertToDAO(ProductUpdateVO vo) {
		ProductDAO product = new ProductDAO();
		BeanUtils.copyProperties(vo, product);
		return product;
	}

	ProductReturnVO convertToReturnVO(ProductDAO save, List<SkuDAO> list) {
		ProductReturnVO vo = BeanConvertor.convert(save, ProductReturnVO.class);
		List<ProductReturnVO.SkuReturnVO> skuVO = BeanConvertor.convert(list, ProductReturnVO.SkuReturnVO.class);
		vo.setSkuList(skuVO);
		return vo;
	}

	@Override
	public void updateProductSelective(ProductUpdateVO product) {

	}

	@Override
	public void updateAllProducts(Iterable<ProductUpdateVO> products) {

	}

	@Transactional
	@Override
	public void updateProduct(ProductUpdateVO example) {
		ProductDAO productDAO = convertToDAO(example);
		productDAO.setUpdatedTime(LocalDateTime.now());
		productDAO.setUpdatedTime(LocalDateTime.now());
		mapper.updateByPrimaryKeySelective(productDAO);
		if (example.getBrandId() != null) {
			BrandDAOExample brexample = new BrandDAOExample();
			brexample.createCriteria().andIdEqualTo(example.getBrandId());
			final long l = brandDAOMapper.countByExample(brexample);
			if (l <= 0) {
				log.error("????????????????????? brand ID: {} ?????? ", example.getBrandId());
				throw new ProductUpdateException("????????????????????? brand ID ??????");
			}
		}
		final String productID = example.getId();
		SkuDAOExample skuExample=new SkuDAOExample();
		skuExample.createCriteria().andProductIdEqualTo(productID);
		skuMapper.deleteByExample(skuExample);

		List<ProductUpdateVO.SkuUpdateVO> skuList = Optional.ofNullable(example.getSkuList()).orElse(new ArrayList<>());
		for (ProductUpdateVO.SkuUpdateVO skuVO : skuList) {
			SkuDAO sku = BeanConvertor.convert(skuVO, SkuDAO.class);
			sku.setProductId(productDAO.getId());
			sku.setCreatedTime(LocalDateTime.now());
			sku.setUpdatedTime(LocalDateTime.now());
			skuMapper.insertSelective(sku);
		}
	}

	@Transactional
	@Override
	public void deleteProductById(String id) {
		mapper.deleteByPrimaryKey(id);
	}

	@Override
	public ProductReturnVO getProductById(String id) {
		ProductDAO product = mapper.selectByPrimaryKey(id);
		if (product == null)
			throw new ProductException("cannot get product by id " + id);
		SkuDAOExample example = new SkuDAOExample();
		example.createCriteria().andProductIdEqualTo(id);
		List<SkuDAO> skuDAOS = skuMapper.selectByExample(example);
		return convertToReturnVO(product, skuDAOS);
	}

	@Override
	public long count(ProductQueryVO example) {

		ProductDAOExample productDAOExample = createExample();
		ProductDAOExample.Criteria criteria = productDAOExample.createCriteria();
		if (TextUtil.hasText(example.getName())) {
			criteria.andNameEqualTo(example.getName());
		}
		if (example.getBrandId() != null) {
			criteria.andBrandIdEqualTo(example.getBrandId());
		}
		if (example.getStatus() != null) {
			criteria.andStatusEqualTo(example.getStatus().name());
		}
		if (example.getCategoryId() != null) {
			criteria.andCategoryIdEqualTo(example.getCategoryId());
		}

		return mapper.countByExample(productDAOExample);
	}

	@Override
	public List<ProductReturnVO> getProductByIds(List<String> ids) {
		return ids.stream().map(this::getProductById).collect(Collectors.toList());
	}

	@Override
	public Page<ProductReturnVO> getProductsByCategoryId(Integer id, Page page) {
		ProductDAOExample productDAOExample = createExample();
		ProductDAOExample.Criteria criteria = productDAOExample.createCriteria();
		criteria.andCategoryIdEqualTo(id);

		String orderBy = Optional.ofNullable(page.getOrderBy()).orElse("updated_time desc ");
		productDAOExample.setOrderByClause(orderBy + " limit " + page.getPageSize() + " offset " + page.getOffset());
		final long count = mapper.countByExample(productDAOExample);
		final List<ProductDAO> products = mapper.selectByExample(productDAOExample);

		final List<ProductReturnVO> results = products.stream().map(product -> {
			SkuDAOExample example = new SkuDAOExample();
			example.createCriteria().andProductIdEqualTo(product.getId());
			return convertToReturnVO(product, skuMapper.selectByExample(example));
		}).collect(Collectors.toList());
		return Page.createFrom(page, count, results);
	}


	@Override
	public long countProductsByCategoryId(Integer id) {
		ProductDAOExample productDAOExample = createExample();
		ProductDAOExample.Criteria criteria = productDAOExample.createCriteria();
		criteria.andCategoryIdEqualTo(id);
		return mapper.countByExample(productDAOExample);
	}

	@Override
	public List<ProductReturnVO> getAll() {
		final List<ProductDAO> productDAOS = mapper.selectByExample(createExample());
		SkuDAOExample example = new SkuDAOExample();
		return productDAOS.stream().map(dao -> {
			example.clear();
			example.createCriteria().andProductIdEqualTo(dao.getId());
			return convertToReturnVO(dao, skuMapper.selectByExample(example));
		}).collect(Collectors.toList());
	}

	private ProductDAOExample createExample() {
		return new ProductDAOExample();
	}


	@Override
	public Page<ProductReturnVO> getAll(ProductQueryVO exampleParameter, Page<ProductQueryVO> page) {
		final ProductDAOExample example = createExample();
		final ProductDAOExample.Criteria criteria = example.createCriteria();
		if (TextUtil.hasText(exampleParameter.getName())) {
			criteria.andNameLike("%" + exampleParameter.getName().trim() + "%");
		}

		criteria.andStatusEqualTo(exampleParameter.getStatus().name());
		final Integer category = exampleParameter.getCategoryId();

		if (category != null) {
			criteria.andCategoryIdEqualTo(category);
		}
		if (exampleParameter.getBrandId() != null) {
			criteria.andBrandIdEqualTo(exampleParameter.getBrandId());
		}
		if (TextUtil.hasText(exampleParameter.getItemNo())) {
			criteria.andItemNoLike("%"+exampleParameter.getItemNo()+"%");
		}

		//TODO remove detail part for optimizing

		long count = mapper.countByExample(example);

		String orderBy = Optional.ofNullable(page.getOrderBy()).orElse("updated_time desc ");
		example.setOrderByClause(orderBy + " limit " + page.getPageSize() + " offset " + page.getOffset());
		final List<ProductDAO> productDAOS = mapper.selectByExample(example);
		SkuDAOExample skuExample = new SkuDAOExample();
		final List<ProductReturnVO> collect = productDAOS.stream().map(dao -> {
			skuExample.clear();
			skuExample.createCriteria().andProductIdEqualTo(dao.getId());
			return convertToReturnVO(dao, skuMapper.selectByExample(skuExample));
		}).collect(Collectors.toList());

		return Page.createFrom(page, count, collect);
	}

	@Override
	public ProductReturnVO.SkuReturnVO getProductSKUById(Integer id) {
		return BeanConvertor.convert(skuMapper.selectByPrimaryKey(id), ProductReturnVO.SkuReturnVO.class);
	}


}
