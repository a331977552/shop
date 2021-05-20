package org.shop.service.imp;

import org.shop.common.util.BeanConvertor;
import org.shop.common.util.Page;
import org.shop.common.util.TextUtil;
import org.shop.common.util.UUIDUtils;
import org.shop.exception.ProductUpdateException;
import org.shop.mapper.ProductDAOMapper;
import org.shop.mapper.SkuDAOMapper;
import org.shop.model.dao.ProductDAO;
import org.shop.model.dao.ProductDAOExample;
import org.shop.model.dao.SkuDAO;
import org.shop.model.dao.SkuDAOExample;
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
public class ProductServiceImpl implements ProductService {


	private final ProductDAOMapper mapper;
	private final SkuDAOMapper skuMapper;


	public ProductServiceImpl(ProductDAOMapper mapper, SkuDAOMapper skuMapper) {
		this.mapper = mapper;
		this.skuMapper = skuMapper;
	}

	@Transactional
	@Override
	public ProductReturnVO addProduct(ProductAddVO productVO) {
		ProductDAO product = convertToDAO(productVO);
		product.setId(UUIDUtils.generateID());
		product.setStatus(product.getSales()==null? "ON_SALE":product.getStatus());
		mapper.insert(product);
		//map product into  categories
		List<ProductAddVO.SkuAddVO> skuList = Optional.ofNullable(productVO.getSkuList()).orElse(new ArrayList<>());
		skuList.forEach(sku-> sku.setProductId(product.getId()));
		System.out.println(product);
		List<SkuDAO> convert = BeanConvertor.convert(skuList, SkuDAO.class);
		for (SkuDAO skuDAO : convert) {
			skuDAO.setSales(0);
			skuMapper.insert(skuDAO);
		}
		ProductReturnVO productReturnVO = convertToReturnVO(product, convert);
		return productReturnVO;
	}
	ProductDAO convertToDAO(ProductAddVO vo){
		ProductDAO product = new ProductDAO();
		BeanUtils.copyProperties(vo,product);
		return product;
	}
	ProductDAO convertToDAO(ProductUpdateVO vo){
		ProductDAO product = new ProductDAO();
		BeanUtils.copyProperties(vo,product);
		return product;
	}

	ProductReturnVO convertToReturnVO(ProductDAO save,List<SkuDAO> list){
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
		int i = mapper.updateByPrimaryKey(productDAO);
		if(i == 0){
			throw new ProductUpdateException("产品更新失败");
		}
	   List<ProductUpdateVO.SkuUpdateVO> skuList = example.getSkuList();
		for (ProductUpdateVO.SkuUpdateVO vo : skuList) {
			SkuDAO convert = BeanConvertor.convert(vo, SkuDAO.class);
			skuMapper.updateByPrimaryKey(convert);
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
		SkuDAOExample example = new SkuDAOExample();
		example.createCriteria().andProductIdEqualTo(id);
		List<SkuDAO> skuDAOS = skuMapper.selectByExample(example);
		return convertToReturnVO(product,skuDAOS);
	}

	@Override
	public long count(ProductQueryVO example) {

		ProductDAOExample productDAOExample = createExample();
		ProductDAOExample.Criteria criteria = productDAOExample.createCriteria();
		if(TextUtil.hasText(example.getName())){
			criteria.andNameEqualTo(example.getName());
		}
		if(TextUtil.hasText(example.getBrand())){
			criteria.andBrandEqualTo(example.getBrand());
		}
		if(example.getStatus() !=null){
			criteria.andStatusEqualTo(example.getStatus().name());
		}
		if(example.getCategory() !=null){
			criteria.andCategoryEqualTo(example.getCategory());
		}

		return mapper.countByExample(productDAOExample);
	}

	@Override
	public List<ProductReturnVO> getProductByIds(List<String> ids) {
		return ids.stream().map(this::getProductById).collect(Collectors.toList());
	}

	@Override
	public Page<ProductReturnVO> getProductsByCategoryId(Integer id,Page page) {
		ProductDAOExample productDAOExample = createExample();
		ProductDAOExample.Criteria criteria = productDAOExample.createCriteria();
		criteria.andCategoryEqualTo(id);
		SkuDAOExample example = new SkuDAOExample();
		final long count = mapper.countByExample(productDAOExample);
		final List<ProductReturnVO> collect = mapper.selectByExample(productDAOExample).stream().map(dao -> {
			example.clear();
			example.createCriteria().andProductIdEqualTo(dao.getId());
			return convertToReturnVO(dao, skuMapper.selectByExample(example));
		}).collect(Collectors.toList());
		return Page.createFrom(page,count,collect);
	}


	@Override
	public long countProductsByCategoryId(Integer id) {
		ProductDAOExample productDAOExample = createExample();
		ProductDAOExample.Criteria criteria = productDAOExample.createCriteria();
		criteria.andCategoryEqualTo(id);
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
		if(TextUtil.hasText(exampleParameter.getName())){
			criteria.andNameLike("%"+exampleParameter.getName().trim()+"%");
		}
		long count = mapper.countByExample(example);

		String orderBy = Optional.ofNullable(page.getOrderBy()).orElse("");
		example.setOrderByClause(orderBy+ " limit "+ page.getPageSize() +" offset "+ page.getOffset());

		final List<ProductDAO> productDAOS = mapper.selectByExample(example);
		SkuDAOExample skuExample = new SkuDAOExample();
		final List<ProductReturnVO> collect = productDAOS.stream().map(dao -> {
			skuExample.clear();
			skuExample.createCriteria().andProductIdEqualTo(dao.getId());
			return convertToReturnVO(dao, skuMapper.selectByExample(skuExample));
		}).collect(Collectors.toList());

		return Page.createFrom(page, count, collect);
	}



}
