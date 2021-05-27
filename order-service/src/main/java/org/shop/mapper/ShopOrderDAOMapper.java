package org.shop.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.shop.model.dao.ShopOrderDAO;
import org.shop.model.dao.ShopOrderDAOExample;

import java.util.List;

@Mapper
public interface ShopOrderDAOMapper {
    long countByExample(ShopOrderDAOExample example);

    int deleteByExample(ShopOrderDAOExample example);

    int deleteByPrimaryKey(String id);

    int insert(ShopOrderDAO record);

    int insertSelective(ShopOrderDAO record);

    List<ShopOrderDAO> selectByExample(ShopOrderDAOExample example);

    ShopOrderDAO selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ShopOrderDAO record, @Param("example") ShopOrderDAOExample example);

    int updateByExample(@Param("record") ShopOrderDAO record, @Param("example") ShopOrderDAOExample example);

    int updateByPrimaryKeySelective(ShopOrderDAO record);

    int updateByPrimaryKey(ShopOrderDAO record);
}