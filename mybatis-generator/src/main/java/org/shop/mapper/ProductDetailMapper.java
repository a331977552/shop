package org.shop.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static org.shop.mapper.ProductDetailDynamicSqlSupport.*;

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
import org.shop.model.ProductDetail;

@Mapper
public interface ProductDetailMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.114967+08:00", comments="Source Table: product_detail")
    BasicColumn[] selectList = BasicColumn.columnList(id, productId, createdTime, updatedTime, description);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.113853+08:00", comments="Source Table: product_detail")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.114045+08:00", comments="Source Table: product_detail")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.114073+08:00", comments="Source Table: product_detail")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<ProductDetail> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.114102+08:00", comments="Source Table: product_detail")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<ProductDetail> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.114385+08:00", comments="Source Table: product_detail")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("ProductDetailResult")
    Optional<ProductDetail> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.114467+08:00", comments="Source Table: product_detail")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="ProductDetailResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.INTEGER),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.DATE),
        @Result(column="updated_time", property="updatedTime", jdbcType=JdbcType.DATE),
        @Result(column="description", property="description", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<ProductDetail> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.11459+08:00", comments="Source Table: product_detail")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.114622+08:00", comments="Source Table: product_detail")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, productDetail, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.114645+08:00", comments="Source Table: product_detail")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, productDetail, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.114667+08:00", comments="Source Table: product_detail")
    default int deleteByPrimaryKey(String id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.114696+08:00", comments="Source Table: product_detail")
    default int insert(ProductDetail record) {
        return MyBatis3Utils.insert(this::insert, record, productDetail, c ->
            c.map(id).toProperty("id")
            .map(productId).toProperty("productId")
            .map(createdTime).toProperty("createdTime")
            .map(updatedTime).toProperty("updatedTime")
            .map(description).toProperty("description")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.114756+08:00", comments="Source Table: product_detail")
    default int insertMultiple(Collection<ProductDetail> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, productDetail, c ->
            c.map(id).toProperty("id")
            .map(productId).toProperty("productId")
            .map(createdTime).toProperty("createdTime")
            .map(updatedTime).toProperty("updatedTime")
            .map(description).toProperty("description")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.114797+08:00", comments="Source Table: product_detail")
    default int insertSelective(ProductDetail record) {
        return MyBatis3Utils.insert(this::insert, record, productDetail, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(productId).toPropertyWhenPresent("productId", record::getProductId)
            .map(createdTime).toPropertyWhenPresent("createdTime", record::getCreatedTime)
            .map(updatedTime).toPropertyWhenPresent("updatedTime", record::getUpdatedTime)
            .map(description).toPropertyWhenPresent("description", record::getDescription)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.114995+08:00", comments="Source Table: product_detail")
    default Optional<ProductDetail> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, productDetail, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.11504+08:00", comments="Source Table: product_detail")
    default List<ProductDetail> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, productDetail, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.115069+08:00", comments="Source Table: product_detail")
    default List<ProductDetail> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, productDetail, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.115097+08:00", comments="Source Table: product_detail")
    default Optional<ProductDetail> selectByPrimaryKey(String id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.115122+08:00", comments="Source Table: product_detail")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, productDetail, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.115359+08:00", comments="Source Table: product_detail")
    static UpdateDSL<UpdateModel> updateAllColumns(ProductDetail record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(productId).equalTo(record::getProductId)
                .set(createdTime).equalTo(record::getCreatedTime)
                .set(updatedTime).equalTo(record::getUpdatedTime)
                .set(description).equalTo(record::getDescription);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.115425+08:00", comments="Source Table: product_detail")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(ProductDetail record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(productId).equalToWhenPresent(record::getProductId)
                .set(createdTime).equalToWhenPresent(record::getCreatedTime)
                .set(updatedTime).equalToWhenPresent(record::getUpdatedTime)
                .set(description).equalToWhenPresent(record::getDescription);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.115481+08:00", comments="Source Table: product_detail")
    default int updateByPrimaryKey(ProductDetail record) {
        return update(c ->
            c.set(productId).equalTo(record::getProductId)
            .set(createdTime).equalTo(record::getCreatedTime)
            .set(updatedTime).equalTo(record::getUpdatedTime)
            .set(description).equalTo(record::getDescription)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.115552+08:00", comments="Source Table: product_detail")
    default int updateByPrimaryKeySelective(ProductDetail record) {
        return update(c ->
            c.set(productId).equalToWhenPresent(record::getProductId)
            .set(createdTime).equalToWhenPresent(record::getCreatedTime)
            .set(updatedTime).equalToWhenPresent(record::getUpdatedTime)
            .set(description).equalToWhenPresent(record::getDescription)
            .where(id, isEqualTo(record::getId))
        );
    }
}