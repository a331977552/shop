package org.shop.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static org.shop.mapper.ShoppingCartDynamicSqlSupport.*;

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
import org.shop.model.ShoppingCart;

@Mapper
public interface ShoppingCartMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.143237+08:00", comments="Source Table: shopping_cart")
    BasicColumn[] selectList = BasicColumn.columnList(id, customerId, totalPrice);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.141294+08:00", comments="Source Table: shopping_cart")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.141361+08:00", comments="Source Table: shopping_cart")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.141388+08:00", comments="Source Table: shopping_cart")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<ShoppingCart> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.14142+08:00", comments="Source Table: shopping_cart")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<ShoppingCart> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.141453+08:00", comments="Source Table: shopping_cart")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("ShoppingCartResult")
    Optional<ShoppingCart> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.141489+08:00", comments="Source Table: shopping_cart")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="ShoppingCartResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="customer_id", property="customerId", jdbcType=JdbcType.INTEGER),
        @Result(column="total_price", property="totalPrice", jdbcType=JdbcType.DECIMAL)
    })
    List<ShoppingCart> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.141629+08:00", comments="Source Table: shopping_cart")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.141655+08:00", comments="Source Table: shopping_cart")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, shoppingCart, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.141677+08:00", comments="Source Table: shopping_cart")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, shoppingCart, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.141699+08:00", comments="Source Table: shopping_cart")
    default int deleteByPrimaryKey(String id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.14173+08:00", comments="Source Table: shopping_cart")
    default int insert(ShoppingCart record) {
        return MyBatis3Utils.insert(this::insert, record, shoppingCart, c ->
            c.map(id).toProperty("id")
            .map(customerId).toProperty("customerId")
            .map(totalPrice).toProperty("totalPrice")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.1418+08:00", comments="Source Table: shopping_cart")
    default int insertMultiple(Collection<ShoppingCart> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, shoppingCart, c ->
            c.map(id).toProperty("id")
            .map(customerId).toProperty("customerId")
            .map(totalPrice).toProperty("totalPrice")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.141866+08:00", comments="Source Table: shopping_cart")
    default int insertSelective(ShoppingCart record) {
        return MyBatis3Utils.insert(this::insert, record, shoppingCart, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(customerId).toPropertyWhenPresent("customerId", record::getCustomerId)
            .map(totalPrice).toPropertyWhenPresent("totalPrice", record::getTotalPrice)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.143328+08:00", comments="Source Table: shopping_cart")
    default Optional<ShoppingCart> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, shoppingCart, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.143363+08:00", comments="Source Table: shopping_cart")
    default List<ShoppingCart> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, shoppingCart, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.143444+08:00", comments="Source Table: shopping_cart")
    default List<ShoppingCart> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, shoppingCart, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.143494+08:00", comments="Source Table: shopping_cart")
    default Optional<ShoppingCart> selectByPrimaryKey(String id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.143542+08:00", comments="Source Table: shopping_cart")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, shoppingCart, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.143571+08:00", comments="Source Table: shopping_cart")
    static UpdateDSL<UpdateModel> updateAllColumns(ShoppingCart record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(customerId).equalTo(record::getCustomerId)
                .set(totalPrice).equalTo(record::getTotalPrice);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.143642+08:00", comments="Source Table: shopping_cart")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(ShoppingCart record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(customerId).equalToWhenPresent(record::getCustomerId)
                .set(totalPrice).equalToWhenPresent(record::getTotalPrice);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.143791+08:00", comments="Source Table: shopping_cart")
    default int updateByPrimaryKey(ShoppingCart record) {
        return update(c ->
            c.set(customerId).equalTo(record::getCustomerId)
            .set(totalPrice).equalTo(record::getTotalPrice)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.143872+08:00", comments="Source Table: shopping_cart")
    default int updateByPrimaryKeySelective(ShoppingCart record) {
        return update(c ->
            c.set(customerId).equalToWhenPresent(record::getCustomerId)
            .set(totalPrice).equalToWhenPresent(record::getTotalPrice)
            .where(id, isEqualTo(record::getId))
        );
    }
}