package org.shop.mapper;

import org.apache.ibatis.annotations.Param;
import org.shop.model.dao.SkuDAO;
import org.shop.model.dao.SkuDAOExample;

import java.util.List;

public interface SkuDAOMapper {
    long countByExample(SkuDAOExample example);

    int deleteByExample(SkuDAOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SkuDAO record);

    int insertSelective(SkuDAO record);

    List<SkuDAO> selectByExample(SkuDAOExample example);

    SkuDAO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SkuDAO record, @Param("example") SkuDAOExample example);

    int updateByExample(@Param("record") SkuDAO record, @Param("example") SkuDAOExample example);

    int updateByPrimaryKeySelective(SkuDAO record);

    int updateByPrimaryKey(SkuDAO record);
}