package org.shop.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.shop.model.dao.OrderItemDAO;
import org.shop.model.dao.OrderItemDAOExample;

public interface OrderItemDAOMapper {
    long countByExample(OrderItemDAOExample example);

    int deleteByExample(OrderItemDAOExample example);

    int deleteByPrimaryKey(String id);

    int insert(OrderItemDAO record);

    int insertSelective(OrderItemDAO record);

    List<OrderItemDAO> selectByExample(OrderItemDAOExample example);

    OrderItemDAO selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") OrderItemDAO record, @Param("example") OrderItemDAOExample example);

    int updateByExample(@Param("record") OrderItemDAO record, @Param("example") OrderItemDAOExample example);

    int updateByPrimaryKeySelective(OrderItemDAO record);

    int updateByPrimaryKey(OrderItemDAO record);
}