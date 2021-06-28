package org.shop.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.shop.model.dao.ShippingAddressDAO;
import org.shop.model.dao.ShippingAddressDAOExample;

import java.util.List;

@Mapper
public interface ShippingAddressDAOMapper {
    long countByExample(ShippingAddressDAOExample example);

    int deleteByExample(ShippingAddressDAOExample example);

    int deleteByPrimaryKey(String id);

    int insert(ShippingAddressDAO record);

    int insertSelective(ShippingAddressDAO record);

    List<ShippingAddressDAO> selectByExample(ShippingAddressDAOExample example);

    ShippingAddressDAO selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ShippingAddressDAO record, @Param("example") ShippingAddressDAOExample example);

    int updateByExample(@Param("record") ShippingAddressDAO record, @Param("example") ShippingAddressDAOExample example);

    int updateByPrimaryKeySelective(ShippingAddressDAO record);

    int updateByPrimaryKey(ShippingAddressDAO record);
}