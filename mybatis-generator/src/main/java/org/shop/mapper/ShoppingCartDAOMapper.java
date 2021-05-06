package org.shop.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static org.shop.mapper.ShoppingCartDAODynamicSqlSupport.*;

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
import org.shop.model.dao.ShoppingCartDAO;

@Mapper
public interface ShoppingCartDAOMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.972329+08:00", comments="Source Table: shopping_cart")
    BasicColumn[] selectList = BasicColumn.columnList(id, customerId, totalPrice);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.971661+08:00", comments="Source Table: shopping_cart")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.971696+08:00", comments="Source Table: shopping_cart")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.97172+08:00", comments="Source Table: shopping_cart")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<ShoppingCartDAO> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.971747+08:00", comments="Source Table: shopping_cart")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<ShoppingCartDAO> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.971776+08:00", comments="Source Table: shopping_cart")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("ShoppingCartDAOResult")
    Optional<ShoppingCartDAO> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.972016+08:00", comments="Source Table: shopping_cart")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="ShoppingCartDAOResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="customer_id", property="customerId", jdbcType=JdbcType.INTEGER),
        @Result(column="total_price", property="totalPrice", jdbcType=JdbcType.DECIMAL)
    })
    List<ShoppingCartDAO> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.972104+08:00", comments="Source Table: shopping_cart")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.97213+08:00", comments="Source Table: shopping_cart")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, shoppingCartDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.972152+08:00", comments="Source Table: shopping_cart")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, shoppingCartDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.972172+08:00", comments="Source Table: shopping_cart")
    default int deleteByPrimaryKey(String id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.972193+08:00", comments="Source Table: shopping_cart")
    default int insert(ShoppingCartDAO record) {
        return MyBatis3Utils.insert(this::insert, record, shoppingCartDAO, c ->
            c.map(id).toProperty("id")
            .map(customerId).toProperty("customerId")
            .map(totalPrice).toProperty("totalPrice")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.972234+08:00", comments="Source Table: shopping_cart")
    default int insertMultiple(Collection<ShoppingCartDAO> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, shoppingCartDAO, c ->
            c.map(id).toProperty("id")
            .map(customerId).toProperty("customerId")
            .map(totalPrice).toProperty("totalPrice")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.972267+08:00", comments="Source Table: shopping_cart")
    default int insertSelective(ShoppingCartDAO record) {
        return MyBatis3Utils.insert(this::insert, record, shoppingCartDAO, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(customerId).toPropertyWhenPresent("customerId", record::getCustomerId)
            .map(totalPrice).toPropertyWhenPresent("totalPrice", record::getTotalPrice)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.972355+08:00", comments="Source Table: shopping_cart")
    default Optional<ShoppingCartDAO> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, shoppingCartDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.972379+08:00", comments="Source Table: shopping_cart")
    default List<ShoppingCartDAO> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, shoppingCartDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.972403+08:00", comments="Source Table: shopping_cart")
    default List<ShoppingCartDAO> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, shoppingCartDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.972428+08:00", comments="Source Table: shopping_cart")
    default Optional<ShoppingCartDAO> selectByPrimaryKey(String id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.972455+08:00", comments="Source Table: shopping_cart")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, shoppingCartDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.972479+08:00", comments="Source Table: shopping_cart")
    static UpdateDSL<UpdateModel> updateAllColumns(ShoppingCartDAO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(customerId).equalTo(record::getCustomerId)
                .set(totalPrice).equalTo(record::getTotalPrice);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.972638+08:00", comments="Source Table: shopping_cart")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(ShoppingCartDAO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(customerId).equalToWhenPresent(record::getCustomerId)
                .set(totalPrice).equalToWhenPresent(record::getTotalPrice);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.972751+08:00", comments="Source Table: shopping_cart")
    default int updateByPrimaryKey(ShoppingCartDAO record) {
        return update(c ->
            c.set(customerId).equalTo(record::getCustomerId)
            .set(totalPrice).equalTo(record::getTotalPrice)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.972806+08:00", comments="Source Table: shopping_cart")
    default int updateByPrimaryKeySelective(ShoppingCartDAO record) {
        return update(c ->
            c.set(customerId).equalToWhenPresent(record::getCustomerId)
            .set(totalPrice).equalToWhenPresent(record::getTotalPrice)
            .where(id, isEqualTo(record::getId))
        );
    }
}