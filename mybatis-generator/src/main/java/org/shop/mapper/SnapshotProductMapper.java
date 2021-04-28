package org.shop.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static org.shop.mapper.SnapshotProductDynamicSqlSupport.*;

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
import org.shop.model.SnapshotProduct;

@Mapper
public interface SnapshotProductMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.165846+08:00", comments="Source Table: snapshot_product")
    BasicColumn[] selectList = BasicColumn.columnList(id, productId, name, price, category, thumbnailImg, standardImg, createdTime, updatedTime, description);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.164932+08:00", comments="Source Table: snapshot_product")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.165094+08:00", comments="Source Table: snapshot_product")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.165122+08:00", comments="Source Table: snapshot_product")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<SnapshotProduct> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.165149+08:00", comments="Source Table: snapshot_product")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<SnapshotProduct> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.16518+08:00", comments="Source Table: snapshot_product")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SnapshotProductResult")
    Optional<SnapshotProduct> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.165258+08:00", comments="Source Table: snapshot_product")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SnapshotProductResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.INTEGER),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="price", property="price", jdbcType=JdbcType.DECIMAL),
        @Result(column="category", property="category", jdbcType=JdbcType.INTEGER),
        @Result(column="thumbnail_img", property="thumbnailImg", jdbcType=JdbcType.INTEGER),
        @Result(column="standard_img", property="standardImg", jdbcType=JdbcType.INTEGER),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.DATE),
        @Result(column="updated_time", property="updatedTime", jdbcType=JdbcType.DATE),
        @Result(column="description", property="description", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<SnapshotProduct> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.165502+08:00", comments="Source Table: snapshot_product")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.165543+08:00", comments="Source Table: snapshot_product")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, snapshotProduct, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.165568+08:00", comments="Source Table: snapshot_product")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, snapshotProduct, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.165591+08:00", comments="Source Table: snapshot_product")
    default int deleteByPrimaryKey(String id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.165616+08:00", comments="Source Table: snapshot_product")
    default int insert(SnapshotProduct record) {
        return MyBatis3Utils.insert(this::insert, record, snapshotProduct, c ->
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

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.165701+08:00", comments="Source Table: snapshot_product")
    default int insertMultiple(Collection<SnapshotProduct> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, snapshotProduct, c ->
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

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.165744+08:00", comments="Source Table: snapshot_product")
    default int insertSelective(SnapshotProduct record) {
        return MyBatis3Utils.insert(this::insert, record, snapshotProduct, c ->
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

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.16587+08:00", comments="Source Table: snapshot_product")
    default Optional<SnapshotProduct> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, snapshotProduct, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.165897+08:00", comments="Source Table: snapshot_product")
    default List<SnapshotProduct> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, snapshotProduct, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.165938+08:00", comments="Source Table: snapshot_product")
    default List<SnapshotProduct> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, snapshotProduct, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.165967+08:00", comments="Source Table: snapshot_product")
    default Optional<SnapshotProduct> selectByPrimaryKey(String id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.16599+08:00", comments="Source Table: snapshot_product")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, snapshotProduct, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.166013+08:00", comments="Source Table: snapshot_product")
    static UpdateDSL<UpdateModel> updateAllColumns(SnapshotProduct record, UpdateDSL<UpdateModel> dsl) {
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

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.166057+08:00", comments="Source Table: snapshot_product")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(SnapshotProduct record, UpdateDSL<UpdateModel> dsl) {
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

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.166103+08:00", comments="Source Table: snapshot_product")
    default int updateByPrimaryKey(SnapshotProduct record) {
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

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.16614+08:00", comments="Source Table: snapshot_product")
    default int updateByPrimaryKeySelective(SnapshotProduct record) {
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