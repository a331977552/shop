package org.shop.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.shop.model.dao.AttrValueDAO;
import org.shop.model.dao.AttrValueDAOExample;

import java.util.List;

@Mapper
public interface AttrValueDAOMapper {
    long countByExample(AttrValueDAOExample example);

    int deleteByExample(AttrValueDAOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AttrValueDAO record);

    int insertSelective(AttrValueDAO record);

    List<AttrValueDAO> selectByExample(AttrValueDAOExample example);

    AttrValueDAO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AttrValueDAO record, @Param("example") AttrValueDAOExample example);

    int updateByExample(@Param("record") AttrValueDAO record, @Param("example") AttrValueDAOExample example);

    int updateByPrimaryKeySelective(AttrValueDAO record);

    int updateByPrimaryKey(AttrValueDAO record);

}