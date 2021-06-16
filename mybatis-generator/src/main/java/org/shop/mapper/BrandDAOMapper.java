package org.shop.mapper;

import org.apache.ibatis.annotations.Param;
import org.shop.model.dao.BrandDAO;
import org.shop.model.dao.BrandDAOExample;

import java.util.List;

public interface BrandDAOMapper {
    long countByExample(BrandDAOExample example);

    int deleteByExample(BrandDAOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BrandDAO record);

    int insertSelective(BrandDAO record);

    List<BrandDAO> selectByExample(BrandDAOExample example);

    BrandDAO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BrandDAO record, @Param("example") BrandDAOExample example);

    int updateByExample(@Param("record") BrandDAO record, @Param("example") BrandDAOExample example);

    int updateByPrimaryKeySelective(BrandDAO record);

    int updateByPrimaryKey(BrandDAO record);
}