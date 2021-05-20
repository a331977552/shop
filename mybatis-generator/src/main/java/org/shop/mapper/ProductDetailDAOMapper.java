package org.shop.mapper;

import org.apache.ibatis.annotations.Param;
import org.shop.model.dao.ProductDetailDAO;
import org.shop.model.dao.ProductDetailDAOExample;

import java.util.List;

public interface ProductDetailDAOMapper {
    long countByExample(ProductDetailDAOExample example);

    int deleteByExample(ProductDetailDAOExample example);

    int deleteByPrimaryKey(String id);

    int insert(ProductDetailDAO record);

    int insertSelective(ProductDetailDAO record);

    List<ProductDetailDAO> selectByExampleWithBLOBs(ProductDetailDAOExample example);

    List<ProductDetailDAO> selectByExample(ProductDetailDAOExample example);

    ProductDetailDAO selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ProductDetailDAO record, @Param("example") ProductDetailDAOExample example);

    int updateByExampleWithBLOBs(@Param("record") ProductDetailDAO record, @Param("example") ProductDetailDAOExample example);

    int updateByExample(@Param("record") ProductDetailDAO record, @Param("example") ProductDetailDAOExample example);

    int updateByPrimaryKeySelective(ProductDetailDAO record);

    int updateByPrimaryKeyWithBLOBs(ProductDetailDAO record);

    int updateByPrimaryKey(ProductDetailDAO record);
}