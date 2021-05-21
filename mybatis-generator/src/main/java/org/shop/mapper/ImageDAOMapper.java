package org.shop.mapper;

import org.apache.ibatis.annotations.Param;
import org.shop.model.dao.ImageDAO;
import org.shop.model.dao.ImageDAOExample;

import java.util.List;

public interface ImageDAOMapper {
    long countByExample(ImageDAOExample example);

    int deleteByExample(ImageDAOExample example);

    int deleteByPrimaryKey(String id);

    int insert(ImageDAO record);

    int insertSelective(ImageDAO record);

    List<ImageDAO> selectByExample(ImageDAOExample example);

    ImageDAO selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ImageDAO record, @Param("example") ImageDAOExample example);

    int updateByExample(@Param("record") ImageDAO record, @Param("example") ImageDAOExample example);

    int updateByPrimaryKeySelective(ImageDAO record);

    int updateByPrimaryKey(ImageDAO record);
}