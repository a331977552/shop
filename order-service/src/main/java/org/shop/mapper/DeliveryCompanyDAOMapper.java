package org.shop.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.shop.model.dao.DeliveryCompanyDAO;
import org.shop.model.dao.DeliveryCompanyDAOExample;

import java.util.List;

@Mapper
public interface DeliveryCompanyDAOMapper {
    long countByExample(DeliveryCompanyDAOExample example);

    int deleteByExample(DeliveryCompanyDAOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeliveryCompanyDAO record);

    int insertSelective(DeliveryCompanyDAO record);

    List<DeliveryCompanyDAO> selectByExample(DeliveryCompanyDAOExample example);

    DeliveryCompanyDAO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeliveryCompanyDAO record, @Param("example") DeliveryCompanyDAOExample example);

    int updateByExample(@Param("record") DeliveryCompanyDAO record, @Param("example") DeliveryCompanyDAOExample example);

    int updateByPrimaryKeySelective(DeliveryCompanyDAO record);

    int updateByPrimaryKey(DeliveryCompanyDAO record);
}