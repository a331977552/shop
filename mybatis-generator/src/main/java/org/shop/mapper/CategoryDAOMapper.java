package org.shop.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.shop.model.dao.CategoryDAO;
import org.shop.model.dao.CategoryDAOExample;

public interface CategoryDAOMapper {
    long countByExample(CategoryDAOExample example);

    int deleteByExample(CategoryDAOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CategoryDAO record);

    int insertSelective(CategoryDAO record);

    List<CategoryDAO> selectByExample(CategoryDAOExample example);

    CategoryDAO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CategoryDAO record, @Param("example") CategoryDAOExample example);

    int updateByExample(@Param("record") CategoryDAO record, @Param("example") CategoryDAOExample example);

    int updateByPrimaryKeySelective(CategoryDAO record);

    int updateByPrimaryKey(CategoryDAO record);
}