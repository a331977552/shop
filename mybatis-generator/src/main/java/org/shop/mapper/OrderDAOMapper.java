package org.shop.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.shop.model.dao.OrderDAO;
import org.shop.model.dao.OrderDAOExample;

public interface OrderDAOMapper {
    long countByExample(OrderDAOExample example);

    int deleteByExample(OrderDAOExample example);

    int deleteByPrimaryKey(String id);

    int insert(OrderDAO record);

    int insertSelective(OrderDAO record);

    List<OrderDAO> selectByExample(OrderDAOExample example);

    OrderDAO selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") OrderDAO record, @Param("example") OrderDAOExample example);

    int updateByExample(@Param("record") OrderDAO record, @Param("example") OrderDAOExample example);

    int updateByPrimaryKeySelective(OrderDAO record);

    int updateByPrimaryKey(OrderDAO record);
}