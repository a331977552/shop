package org.shop.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static org.shop.mapper.ProductDetailDAODynamicSqlSupport.*;

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
import org.shop.model.dao.ProductDetailDAO;

@Mapper
public interface ProductDetailDAOMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.955457+08:00", comments="Source Table: product_detail")
    BasicColumn[] selectList = BasicColumn.columnList(id, productId, createdTime, updatedTime, description);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.954485+08:00", comments="Source Table: product_detail")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.954544+08:00", comments="Source Table: product_detail")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.954572+08:00", comments="Source Table: product_detail")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<ProductDetailDAO> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.954599+08:00", comments="Source Table: product_detail")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<ProductDetailDAO> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.954659+08:00", comments="Source Table: product_detail")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("ProductDetailDAOResult")
    Optional<ProductDetailDAO> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.954725+08:00", comments="Source Table: product_detail")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="ProductDetailDAOResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.INTEGER),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_time", property="updatedTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="description", property="description", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<ProductDetailDAO> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.954842+08:00", comments="Source Table: product_detail")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.954867+08:00", comments="Source Table: product_detail")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, productDetailDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.954888+08:00", comments="Source Table: product_detail")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, productDetailDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.95491+08:00", comments="Source Table: product_detail")
    default int deleteByPrimaryKey(String id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.955156+08:00", comments="Source Table: product_detail")
    default int insert(ProductDetailDAO record) {
        return MyBatis3Utils.insert(this::insert, record, productDetailDAO, c ->
            c.map(id).toProperty("id")
            .map(productId).toProperty("productId")
            .map(createdTime).toProperty("createdTime")
            .map(updatedTime).toProperty("updatedTime")
            .map(description).toProperty("description")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.95523+08:00", comments="Source Table: product_detail")
    default int insertMultiple(Collection<ProductDetailDAO> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, productDetailDAO, c ->
            c.map(id).toProperty("id")
            .map(productId).toProperty("productId")
            .map(createdTime).toProperty("createdTime")
            .map(updatedTime).toProperty("updatedTime")
            .map(description).toProperty("description")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.955271+08:00", comments="Source Table: product_detail")
    default int insertSelective(ProductDetailDAO record) {
        return MyBatis3Utils.insert(this::insert, record, productDetailDAO, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(productId).toPropertyWhenPresent("productId", record::getProductId)
            .map(createdTime).toPropertyWhenPresent("createdTime", record::getCreatedTime)
            .map(updatedTime).toPropertyWhenPresent("updatedTime", record::getUpdatedTime)
            .map(description).toPropertyWhenPresent("description", record::getDescription)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.955484+08:00", comments="Source Table: product_detail")
    default Optional<ProductDetailDAO> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, productDetailDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.95553+08:00", comments="Source Table: product_detail")
    default List<ProductDetailDAO> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, productDetailDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.955559+08:00", comments="Source Table: product_detail")
    default List<ProductDetailDAO> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, productDetailDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.955588+08:00", comments="Source Table: product_detail")
    default Optional<ProductDetailDAO> selectByPrimaryKey(String id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.955613+08:00", comments="Source Table: product_detail")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, productDetailDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.955824+08:00", comments="Source Table: product_detail")
    static UpdateDSL<UpdateModel> updateAllColumns(ProductDetailDAO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(productId).equalTo(record::getProductId)
                .set(createdTime).equalTo(record::getCreatedTime)
                .set(updatedTime).equalTo(record::getUpdatedTime)
                .set(description).equalTo(record::getDescription);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.955892+08:00", comments="Source Table: product_detail")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(ProductDetailDAO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(productId).equalToWhenPresent(record::getProductId)
                .set(createdTime).equalToWhenPresent(record::getCreatedTime)
                .set(updatedTime).equalToWhenPresent(record::getUpdatedTime)
                .set(description).equalToWhenPresent(record::getDescription);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.955951+08:00", comments="Source Table: product_detail")
    default int updateByPrimaryKey(ProductDetailDAO record) {
        return update(c ->
            c.set(productId).equalTo(record::getProductId)
            .set(createdTime).equalTo(record::getCreatedTime)
            .set(updatedTime).equalTo(record::getUpdatedTime)
            .set(description).equalTo(record::getDescription)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.955995+08:00", comments="Source Table: product_detail")
    default int updateByPrimaryKeySelective(ProductDetailDAO record) {
        return update(c ->
            c.set(productId).equalToWhenPresent(record::getProductId)
            .set(createdTime).equalToWhenPresent(record::getCreatedTime)
            .set(updatedTime).equalToWhenPresent(record::getUpdatedTime)
            .set(description).equalToWhenPresent(record::getDescription)
            .where(id, isEqualTo(record::getId))
        );
    }
}