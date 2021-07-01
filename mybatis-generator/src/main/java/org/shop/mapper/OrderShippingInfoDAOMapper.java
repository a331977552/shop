package org.shop.mapper;

import org.apache.ibatis.annotations.Param;
import org.shop.model.dao.OrderShippingInfoDAO;
import org.shop.model.dao.OrderShippingInfoDAOExample;

import java.util.List;

public interface OrderShippingInfoDAOMapper {
    long countByExample(OrderShippingInfoDAOExample example);

    int deleteByExample(OrderShippingInfoDAOExample example);

    int deleteByPrimaryKey(String id);

    int insert(OrderShippingInfoDAO record);

    int insertSelective(OrderShippingInfoDAO record);

    List<OrderShippingInfoDAO> selectByExample(OrderShippingInfoDAOExample example);

    OrderShippingInfoDAO selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") OrderShippingInfoDAO record, @Param("example") OrderShippingInfoDAOExample example);

    int updateByExample(@Param("record") OrderShippingInfoDAO record, @Param("example") OrderShippingInfoDAOExample example);

    int updateByPrimaryKeySelective(OrderShippingInfoDAO record);

    int updateByPrimaryKey(OrderShippingInfoDAO record);
}