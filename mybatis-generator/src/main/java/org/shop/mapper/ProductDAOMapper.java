package org.shop.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.shop.model.dao.ProductDAO;
import org.shop.model.dao.ProductDAOExample;

public interface ProductDAOMapper {
    long countByExample(ProductDAOExample example);

    int deleteByExample(ProductDAOExample example);

    int deleteByPrimaryKey(String id);

    int insert(ProductDAO record);

    int insertSelective(ProductDAO record);

    List<ProductDAO> selectByExampleWithBLOBs(ProductDAOExample example);

    List<ProductDAO> selectByExample(ProductDAOExample example);

    ProductDAO selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ProductDAO record, @Param("example") ProductDAOExample example);

    int updateByExampleWithBLOBs(@Param("record") ProductDAO record, @Param("example") ProductDAOExample example);

    int updateByExample(@Param("record") ProductDAO record, @Param("example") ProductDAOExample example);

    int updateByPrimaryKeySelective(ProductDAO record);

    int updateByPrimaryKeyWithBLOBs(ProductDAO record);

    int updateByPrimaryKey(ProductDAO record);
}