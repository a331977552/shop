package org.shop.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.shop.model.dao.BrandDAO;
import org.shop.model.dao.BrandDAOExample;

import java.util.List;
@Mapper
public interface BrandDAOMapper {
    long countByExample(BrandDAOExample example);

    int deleteByExample(BrandDAOExample example);

    int insert(BrandDAO record);

    int insertSelective(BrandDAO record);

    List<BrandDAO> selectByExample(BrandDAOExample example);

    int updateByExampleSelective(@Param("record") BrandDAO record, @Param("example") BrandDAOExample example);

    int updateByExample(@Param("record") BrandDAO record, @Param("example") BrandDAOExample example);
}