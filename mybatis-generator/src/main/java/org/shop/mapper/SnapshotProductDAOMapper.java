package org.shop.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static org.shop.mapper.SnapshotProductDAODynamicSqlSupport.*;

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
import org.shop.model.dao.SnapshotProductDAO;

@Mapper
public interface SnapshotProductDAOMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.992296+08:00", comments="Source Table: snapshot_product")
    BasicColumn[] selectList = BasicColumn.columnList(id, productId, name, price, category, thumbnailImg, standardImg, createdTime, updatedTime, description);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.990906+08:00", comments="Source Table: snapshot_product")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.99095+08:00", comments="Source Table: snapshot_product")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.990974+08:00", comments="Source Table: snapshot_product")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<SnapshotProductDAO> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.991007+08:00", comments="Source Table: snapshot_product")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<SnapshotProductDAO> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.991115+08:00", comments="Source Table: snapshot_product")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SnapshotProductDAOResult")
    Optional<SnapshotProductDAO> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.991331+08:00", comments="Source Table: snapshot_product")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SnapshotProductDAOResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.INTEGER),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="price", property="price", jdbcType=JdbcType.DECIMAL),
        @Result(column="category", property="category", jdbcType=JdbcType.INTEGER),
        @Result(column="thumbnail_img", property="thumbnailImg", jdbcType=JdbcType.INTEGER),
        @Result(column="standard_img", property="standardImg", jdbcType=JdbcType.INTEGER),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_time", property="updatedTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="description", property="description", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<SnapshotProductDAO> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.991547+08:00", comments="Source Table: snapshot_product")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.991588+08:00", comments="Source Table: snapshot_product")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, snapshotProductDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.991611+08:00", comments="Source Table: snapshot_product")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, snapshotProductDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.991631+08:00", comments="Source Table: snapshot_product")
    default int deleteByPrimaryKey(String id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.991653+08:00", comments="Source Table: snapshot_product")
    default int insert(SnapshotProductDAO record) {
        return MyBatis3Utils.insert(this::insert, record, snapshotProductDAO, c ->
            c.map(id).toProperty("id")
            .map(productId).toProperty("productId")
            .map(name).toProperty("name")
            .map(price).toProperty("price")
            .map(category).toProperty("category")
            .map(thumbnailImg).toProperty("thumbnailImg")
            .map(standardImg).toProperty("standardImg")
            .map(createdTime).toProperty("createdTime")
            .map(updatedTime).toProperty("updatedTime")
            .map(description).toProperty("description")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.991701+08:00", comments="Source Table: snapshot_product")
    default int insertMultiple(Collection<SnapshotProductDAO> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, snapshotProductDAO, c ->
            c.map(id).toProperty("id")
            .map(productId).toProperty("productId")
            .map(name).toProperty("name")
            .map(price).toProperty("price")
            .map(category).toProperty("category")
            .map(thumbnailImg).toProperty("thumbnailImg")
            .map(standardImg).toProperty("standardImg")
            .map(createdTime).toProperty("createdTime")
            .map(updatedTime).toProperty("updatedTime")
            .map(description).toProperty("description")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.991737+08:00", comments="Source Table: snapshot_product")
    default int insertSelective(SnapshotProductDAO record) {
        return MyBatis3Utils.insert(this::insert, record, snapshotProductDAO, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(productId).toPropertyWhenPresent("productId", record::getProductId)
            .map(name).toPropertyWhenPresent("name", record::getName)
            .map(price).toPropertyWhenPresent("price", record::getPrice)
            .map(category).toPropertyWhenPresent("category", record::getCategory)
            .map(thumbnailImg).toPropertyWhenPresent("thumbnailImg", record::getThumbnailImg)
            .map(standardImg).toPropertyWhenPresent("standardImg", record::getStandardImg)
            .map(createdTime).toPropertyWhenPresent("createdTime", record::getCreatedTime)
            .map(updatedTime).toPropertyWhenPresent("updatedTime", record::getUpdatedTime)
            .map(description).toPropertyWhenPresent("description", record::getDescription)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.992324+08:00", comments="Source Table: snapshot_product")
    default Optional<SnapshotProductDAO> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, snapshotProductDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.992373+08:00", comments="Source Table: snapshot_product")
    default List<SnapshotProductDAO> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, snapshotProductDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.992406+08:00", comments="Source Table: snapshot_product")
    default List<SnapshotProductDAO> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, snapshotProductDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.992433+08:00", comments="Source Table: snapshot_product")
    default Optional<SnapshotProductDAO> selectByPrimaryKey(String id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.992463+08:00", comments="Source Table: snapshot_product")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, snapshotProductDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.992487+08:00", comments="Source Table: snapshot_product")
    static UpdateDSL<UpdateModel> updateAllColumns(SnapshotProductDAO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(productId).equalTo(record::getProductId)
                .set(name).equalTo(record::getName)
                .set(price).equalTo(record::getPrice)
                .set(category).equalTo(record::getCategory)
                .set(thumbnailImg).equalTo(record::getThumbnailImg)
                .set(standardImg).equalTo(record::getStandardImg)
                .set(createdTime).equalTo(record::getCreatedTime)
                .set(updatedTime).equalTo(record::getUpdatedTime)
                .set(description).equalTo(record::getDescription);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.992661+08:00", comments="Source Table: snapshot_product")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(SnapshotProductDAO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(productId).equalToWhenPresent(record::getProductId)
                .set(name).equalToWhenPresent(record::getName)
                .set(price).equalToWhenPresent(record::getPrice)
                .set(category).equalToWhenPresent(record::getCategory)
                .set(thumbnailImg).equalToWhenPresent(record::getThumbnailImg)
                .set(standardImg).equalToWhenPresent(record::getStandardImg)
                .set(createdTime).equalToWhenPresent(record::getCreatedTime)
                .set(updatedTime).equalToWhenPresent(record::getUpdatedTime)
                .set(description).equalToWhenPresent(record::getDescription);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.992749+08:00", comments="Source Table: snapshot_product")
    default int updateByPrimaryKey(SnapshotProductDAO record) {
        return update(c ->
            c.set(productId).equalTo(record::getProductId)
            .set(name).equalTo(record::getName)
            .set(price).equalTo(record::getPrice)
            .set(category).equalTo(record::getCategory)
            .set(thumbnailImg).equalTo(record::getThumbnailImg)
            .set(standardImg).equalTo(record::getStandardImg)
            .set(createdTime).equalTo(record::getCreatedTime)
            .set(updatedTime).equalTo(record::getUpdatedTime)
            .set(description).equalTo(record::getDescription)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.99281+08:00", comments="Source Table: snapshot_product")
    default int updateByPrimaryKeySelective(SnapshotProductDAO record) {
        return update(c ->
            c.set(productId).equalToWhenPresent(record::getProductId)
            .set(name).equalToWhenPresent(record::getName)
            .set(price).equalToWhenPresent(record::getPrice)
            .set(category).equalToWhenPresent(record::getCategory)
            .set(thumbnailImg).equalToWhenPresent(record::getThumbnailImg)
            .set(standardImg).equalToWhenPresent(record::getStandardImg)
            .set(createdTime).equalToWhenPresent(record::getCreatedTime)
            .set(updatedTime).equalToWhenPresent(record::getUpdatedTime)
            .set(description).equalToWhenPresent(record::getDescription)
            .where(id, isEqualTo(record::getId))
        );
    }
}