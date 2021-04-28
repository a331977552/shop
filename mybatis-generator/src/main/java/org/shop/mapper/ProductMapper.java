package org.shop.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static org.shop.mapper.ProductDynamicSqlSupport.*;

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
import org.shop.model.Product;

@Mapper
public interface ProductMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.096507+08:00", comments="Source Table: product")
    BasicColumn[] selectList = BasicColumn.columnList(id, name, price, category, quantity, thumbnailImg, standardImg, createdTime, updatedTime);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.090659+08:00", comments="Source Table: product")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.090799+08:00", comments="Source Table: product")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.090836+08:00", comments="Source Table: product")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<Product> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.090868+08:00", comments="Source Table: product")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<Product> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.090902+08:00", comments="Source Table: product")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("ProductResult")
    Optional<Product> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.090968+08:00", comments="Source Table: product")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="ProductResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="price", property="price", jdbcType=JdbcType.DECIMAL),
        @Result(column="category", property="category", jdbcType=JdbcType.INTEGER),
        @Result(column="quantity", property="quantity", jdbcType=JdbcType.INTEGER),
        @Result(column="thumbnail_img", property="thumbnailImg", jdbcType=JdbcType.INTEGER),
        @Result(column="standard_img", property="standardImg", jdbcType=JdbcType.INTEGER),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.DATE),
        @Result(column="updated_time", property="updatedTime", jdbcType=JdbcType.DATE)
    })
    List<Product> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.091123+08:00", comments="Source Table: product")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.091163+08:00", comments="Source Table: product")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, product, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.091188+08:00", comments="Source Table: product")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, product, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.091211+08:00", comments="Source Table: product")
    default int deleteByPrimaryKey(String id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.09124+08:00", comments="Source Table: product")
    default int insert(Product record) {
        return MyBatis3Utils.insert(this::insert, record, product, c ->
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

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.09167+08:00", comments="Source Table: product")
    default int insertMultiple(Collection<Product> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, product, c ->
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

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.091849+08:00", comments="Source Table: product")
    default int insertSelective(Product record) {
        return MyBatis3Utils.insert(this::insert, record, product, c ->
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

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.096655+08:00", comments="Source Table: product")
    default Optional<Product> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, product, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.096701+08:00", comments="Source Table: product")
    default List<Product> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, product, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.096738+08:00", comments="Source Table: product")
    default List<Product> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, product, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.09677+08:00", comments="Source Table: product")
    default Optional<Product> selectByPrimaryKey(String id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.096805+08:00", comments="Source Table: product")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, product, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.096834+08:00", comments="Source Table: product")
    static UpdateDSL<UpdateModel> updateAllColumns(Product record, UpdateDSL<UpdateModel> dsl) {
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

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.098555+08:00", comments="Source Table: product")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Product record, UpdateDSL<UpdateModel> dsl) {
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

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.099221+08:00", comments="Source Table: product")
    default int updateByPrimaryKey(Product record) {
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

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.09969+08:00", comments="Source Table: product")
    default int updateByPrimaryKeySelective(Product record) {
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