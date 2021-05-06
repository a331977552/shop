package org.shop.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static org.shop.mapper.ImageProductDAODynamicSqlSupport.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;
import org.shop.model.dao.ImageProductDAO;

@Mapper
public interface ImageProductDAOMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.891343+08:00", comments="Source Table: image_product")
    BasicColumn[] selectList = BasicColumn.columnList(id, productId, imgId);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.888165+08:00", comments="Source Table: image_product")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.888244+08:00", comments="Source Table: image_product")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.888316+08:00", comments="Source Table: image_product")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<ImageProductDAO> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.888347+08:00", comments="Source Table: image_product")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<ImageProductDAO> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.888382+08:00", comments="Source Table: image_product")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("ImageProductDAOResult")
    Optional<ImageProductDAO> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.888428+08:00", comments="Source Table: image_product")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="ImageProductDAOResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.CHAR),
        @Result(column="img_id", property="imgId", jdbcType=JdbcType.CHAR)
    })
    List<ImageProductDAO> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.888504+08:00", comments="Source Table: image_product")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.888639+08:00", comments="Source Table: image_product")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, imageProductDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.889459+08:00", comments="Source Table: image_product")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, imageProductDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.889551+08:00", comments="Source Table: image_product")
    default int deleteByPrimaryKey(String id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.889587+08:00", comments="Source Table: image_product")
    default int insert(ImageProductDAO record) {
        return MyBatis3Utils.insert(this::insert, record, imageProductDAO, c ->
            c.map(id).toProperty("id")
            .map(productId).toProperty("productId")
            .map(imgId).toProperty("imgId")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.890072+08:00", comments="Source Table: image_product")
    default int insertMultiple(Collection<ImageProductDAO> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, imageProductDAO, c ->
            c.map(id).toProperty("id")
            .map(productId).toProperty("productId")
            .map(imgId).toProperty("imgId")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.890599+08:00", comments="Source Table: image_product")
    default int insertSelective(ImageProductDAO record) {
        return MyBatis3Utils.insert(this::insert, record, imageProductDAO, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(productId).toPropertyWhenPresent("productId", record::getProductId)
            .map(imgId).toPropertyWhenPresent("imgId", record::getImgId)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.89153+08:00", comments="Source Table: image_product")
    default Optional<ImageProductDAO> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, imageProductDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.891843+08:00", comments="Source Table: image_product")
    default List<ImageProductDAO> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, imageProductDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.892065+08:00", comments="Source Table: image_product")
    default List<ImageProductDAO> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, imageProductDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.892125+08:00", comments="Source Table: image_product")
    default Optional<ImageProductDAO> selectByPrimaryKey(String id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.892189+08:00", comments="Source Table: image_product")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, imageProductDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.892319+08:00", comments="Source Table: image_product")
    static UpdateDSL<UpdateModel> updateAllColumns(ImageProductDAO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(productId).equalTo(record::getProductId)
                .set(imgId).equalTo(record::getImgId);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.89242+08:00", comments="Source Table: image_product")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(ImageProductDAO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(productId).equalToWhenPresent(record::getProductId)
                .set(imgId).equalToWhenPresent(record::getImgId);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.893527+08:00", comments="Source Table: image_product")
    default int updateByPrimaryKey(ImageProductDAO record) {
        return update(c ->
            c.set(productId).equalTo(record::getProductId)
            .set(imgId).equalTo(record::getImgId)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.897404+08:00", comments="Source Table: image_product")
    default int updateByPrimaryKeySelective(ImageProductDAO record) {
        return update(c ->
            c.set(productId).equalToWhenPresent(record::getProductId)
            .set(imgId).equalToWhenPresent(record::getImgId)
            .where(id, isEqualTo(record::getId))
        );
    }
}