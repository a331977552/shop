package org.shop.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static org.shop.mapper.ProductDAODynamicSqlSupport.*;

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
import org.shop.model.dao.ProductDAO;

@Mapper
public interface ProductDAOMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.940985+08:00", comments="Source Table: product")
    BasicColumn[] selectList = BasicColumn.columnList(id, name, price, category, quantity, thumbnailImg, standardImg, createdTime, updatedTime);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.940284+08:00", comments="Source Table: product")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.940381+08:00", comments="Source Table: product")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.940415+08:00", comments="Source Table: product")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<ProductDAO> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.940445+08:00", comments="Source Table: product")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<ProductDAO> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.940484+08:00", comments="Source Table: product")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("ProductDAOResult")
    Optional<ProductDAO> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.94053+08:00", comments="Source Table: product")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="ProductDAOResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="price", property="price", jdbcType=JdbcType.DECIMAL),
        @Result(column="category", property="category", jdbcType=JdbcType.INTEGER),
        @Result(column="quantity", property="quantity", jdbcType=JdbcType.INTEGER),
        @Result(column="thumbnail_img", property="thumbnailImg", jdbcType=JdbcType.INTEGER),
        @Result(column="standard_img", property="standardImg", jdbcType=JdbcType.INTEGER),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_time", property="updatedTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ProductDAO> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.94064+08:00", comments="Source Table: product")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.940668+08:00", comments="Source Table: product")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, productDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.940691+08:00", comments="Source Table: product")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, productDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.940714+08:00", comments="Source Table: product")
    default int deleteByPrimaryKey(String id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.940738+08:00", comments="Source Table: product")
    default int insert(ProductDAO record) {
        return MyBatis3Utils.insert(this::insert, record, productDAO, c ->
            c.map(id).toProperty("id")
            .map(name).toProperty("name")
            .map(price).toProperty("price")
            .map(category).toProperty("category")
            .map(quantity).toProperty("quantity")
            .map(thumbnailImg).toProperty("thumbnailImg")
            .map(standardImg).toProperty("standardImg")
            .map(createdTime).toProperty("createdTime")
            .map(updatedTime).toProperty("updatedTime")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.940799+08:00", comments="Source Table: product")
    default int insertMultiple(Collection<ProductDAO> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, productDAO, c ->
            c.map(id).toProperty("id")
            .map(name).toProperty("name")
            .map(price).toProperty("price")
            .map(category).toProperty("category")
            .map(quantity).toProperty("quantity")
            .map(thumbnailImg).toProperty("thumbnailImg")
            .map(standardImg).toProperty("standardImg")
            .map(createdTime).toProperty("createdTime")
            .map(updatedTime).toProperty("updatedTime")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.940844+08:00", comments="Source Table: product")
    default int insertSelective(ProductDAO record) {
        return MyBatis3Utils.insert(this::insert, record, productDAO, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(name).toPropertyWhenPresent("name", record::getName)
            .map(price).toPropertyWhenPresent("price", record::getPrice)
            .map(category).toPropertyWhenPresent("category", record::getCategory)
            .map(quantity).toPropertyWhenPresent("quantity", record::getQuantity)
            .map(thumbnailImg).toPropertyWhenPresent("thumbnailImg", record::getThumbnailImg)
            .map(standardImg).toPropertyWhenPresent("standardImg", record::getStandardImg)
            .map(createdTime).toPropertyWhenPresent("createdTime", record::getCreatedTime)
            .map(updatedTime).toPropertyWhenPresent("updatedTime", record::getUpdatedTime)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.941014+08:00", comments="Source Table: product")
    default Optional<ProductDAO> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, productDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.941043+08:00", comments="Source Table: product")
    default List<ProductDAO> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, productDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.941071+08:00", comments="Source Table: product")
    default List<ProductDAO> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, productDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.9411+08:00", comments="Source Table: product")
    default Optional<ProductDAO> selectByPrimaryKey(String id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.941126+08:00", comments="Source Table: product")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, productDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.941152+08:00", comments="Source Table: product")
    static UpdateDSL<UpdateModel> updateAllColumns(ProductDAO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(name).equalTo(record::getName)
                .set(price).equalTo(record::getPrice)
                .set(category).equalTo(record::getCategory)
                .set(quantity).equalTo(record::getQuantity)
                .set(thumbnailImg).equalTo(record::getThumbnailImg)
                .set(standardImg).equalTo(record::getStandardImg)
                .set(createdTime).equalTo(record::getCreatedTime)
                .set(updatedTime).equalTo(record::getUpdatedTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.941216+08:00", comments="Source Table: product")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(ProductDAO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(name).equalToWhenPresent(record::getName)
                .set(price).equalToWhenPresent(record::getPrice)
                .set(category).equalToWhenPresent(record::getCategory)
                .set(quantity).equalToWhenPresent(record::getQuantity)
                .set(thumbnailImg).equalToWhenPresent(record::getThumbnailImg)
                .set(standardImg).equalToWhenPresent(record::getStandardImg)
                .set(createdTime).equalToWhenPresent(record::getCreatedTime)
                .set(updatedTime).equalToWhenPresent(record::getUpdatedTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.941275+08:00", comments="Source Table: product")
    default int updateByPrimaryKey(ProductDAO record) {
        return update(c ->
            c.set(name).equalTo(record::getName)
            .set(price).equalTo(record::getPrice)
            .set(category).equalTo(record::getCategory)
            .set(quantity).equalTo(record::getQuantity)
            .set(thumbnailImg).equalTo(record::getThumbnailImg)
            .set(standardImg).equalTo(record::getStandardImg)
            .set(createdTime).equalTo(record::getCreatedTime)
            .set(updatedTime).equalTo(record::getUpdatedTime)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.941326+08:00", comments="Source Table: product")
    default int updateByPrimaryKeySelective(ProductDAO record) {
        return update(c ->
            c.set(name).equalToWhenPresent(record::getName)
            .set(price).equalToWhenPresent(record::getPrice)
            .set(category).equalToWhenPresent(record::getCategory)
            .set(quantity).equalToWhenPresent(record::getQuantity)
            .set(thumbnailImg).equalToWhenPresent(record::getThumbnailImg)
            .set(standardImg).equalToWhenPresent(record::getStandardImg)
            .set(createdTime).equalToWhenPresent(record::getCreatedTime)
            .set(updatedTime).equalToWhenPresent(record::getUpdatedTime)
            .where(id, isEqualTo(record::getId))
        );
    }
}