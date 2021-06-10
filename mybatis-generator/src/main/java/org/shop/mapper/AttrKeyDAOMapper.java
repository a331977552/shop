package org.shop.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.shop.model.dao.AttrKeyDAO;
import org.shop.model.dao.AttrKeyDAOExample;

public interface AttrKeyDAOMapper {
    long countByExample(AttrKeyDAOExample example);

    int deleteByExample(AttrKeyDAOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AttrKeyDAO record);

    int insertSelective(AttrKeyDAO record);

    List<AttrKeyDAO> selectByExample(AttrKeyDAOExample example);

    AttrKeyDAO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AttrKeyDAO record, @Param("example") AttrKeyDAOExample example);

    int updateByExample(@Param("record") AttrKeyDAO record, @Param("example") AttrKeyDAOExample example);

    int updateByPrimaryKeySelective(AttrKeyDAO record);

    int updateByPrimaryKey(AttrKeyDAO record);
}