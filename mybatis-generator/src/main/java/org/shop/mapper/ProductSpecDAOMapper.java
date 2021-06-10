package org.shop.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.shop.model.dao.ProductSpecDAO;
import org.shop.model.dao.ProductSpecDAOExample;

public interface ProductSpecDAOMapper {
    long countByExample(ProductSpecDAOExample example);

    int deleteByExample(ProductSpecDAOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductSpecDAO record);

    int insertSelective(ProductSpecDAO record);

    List<ProductSpecDAO> selectByExample(ProductSpecDAOExample example);

    ProductSpecDAO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductSpecDAO record, @Param("example") ProductSpecDAOExample example);

    int updateByExample(@Param("record") ProductSpecDAO record, @Param("example") ProductSpecDAOExample example);

    int updateByPrimaryKeySelective(ProductSpecDAO record);

    int updateByPrimaryKey(ProductSpecDAO record);
}