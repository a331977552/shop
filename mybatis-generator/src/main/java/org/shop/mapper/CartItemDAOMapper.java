package org.shop.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static org.shop.mapper.CartItemDAODynamicSqlSupport.*;

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
import org.shop.model.dao.CartItemDAO;

@Mapper
public interface CartItemDAOMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.827988+08:00", comments="Source Table: cart_item")
    BasicColumn[] selectList = BasicColumn.columnList(id, customerId, productId, cartId, quantity, totalPrice, createdTime, updatedTime);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.804484+08:00", comments="Source Table: cart_item")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.805415+08:00", comments="Source Table: cart_item")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.805793+08:00", comments="Source Table: cart_item")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<CartItemDAO> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.806497+08:00", comments="Source Table: cart_item")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<CartItemDAO> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.810366+08:00", comments="Source Table: cart_item")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("CartItemDAOResult")
    Optional<CartItemDAO> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.812548+08:00", comments="Source Table: cart_item")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="CartItemDAOResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="customer_id", property="customerId", jdbcType=JdbcType.INTEGER),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.INTEGER),
        @Result(column="cart_id", property="cartId", jdbcType=JdbcType.INTEGER),
        @Result(column="quantity", property="quantity", jdbcType=JdbcType.INTEGER),
        @Result(column="total_price", property="totalPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_time", property="updatedTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<CartItemDAO> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.815049+08:00", comments="Source Table: cart_item")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.816484+08:00", comments="Source Table: cart_item")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, cartItemDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.817948+08:00", comments="Source Table: cart_item")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, cartItemDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.818377+08:00", comments="Source Table: cart_item")
    default int deleteByPrimaryKey(String id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.818769+08:00", comments="Source Table: cart_item")
    default int insert(CartItemDAO record) {
        return MyBatis3Utils.insert(this::insert, record, cartItemDAO, c ->
            c.map(id).toProperty("id")
            .map(customerId).toProperty("customerId")
            .map(productId).toProperty("productId")
            .map(cartId).toProperty("cartId")
            .map(quantity).toProperty("quantity")
            .map(totalPrice).toProperty("totalPrice")
            .map(createdTime).toProperty("createdTime")
            .map(updatedTime).toProperty("updatedTime")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.821841+08:00", comments="Source Table: cart_item")
    default int insertMultiple(Collection<CartItemDAO> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, cartItemDAO, c ->
            c.map(id).toProperty("id")
            .map(customerId).toProperty("customerId")
            .map(productId).toProperty("productId")
            .map(cartId).toProperty("cartId")
            .map(quantity).toProperty("quantity")
            .map(totalPrice).toProperty("totalPrice")
            .map(createdTime).toProperty("createdTime")
            .map(updatedTime).toProperty("updatedTime")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.823355+08:00", comments="Source Table: cart_item")
    default int insertSelective(CartItemDAO record) {
        return MyBatis3Utils.insert(this::insert, record, cartItemDAO, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(customerId).toPropertyWhenPresent("customerId", record::getCustomerId)
            .map(productId).toPropertyWhenPresent("productId", record::getProductId)
            .map(cartId).toPropertyWhenPresent("cartId", record::getCartId)
            .map(quantity).toPropertyWhenPresent("quantity", record::getQuantity)
            .map(totalPrice).toPropertyWhenPresent("totalPrice", record::getTotalPrice)
            .map(createdTime).toPropertyWhenPresent("createdTime", record::getCreatedTime)
            .map(updatedTime).toPropertyWhenPresent("updatedTime", record::getUpdatedTime)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.830999+08:00", comments="Source Table: cart_item")
    default Optional<CartItemDAO> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, cartItemDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.83169+08:00", comments="Source Table: cart_item")
    default List<CartItemDAO> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, cartItemDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.83195+08:00", comments="Source Table: cart_item")
    default List<CartItemDAO> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, cartItemDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.83221+08:00", comments="Source Table: cart_item")
    default Optional<CartItemDAO> selectByPrimaryKey(String id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.832505+08:00", comments="Source Table: cart_item")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, cartItemDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.833343+08:00", comments="Source Table: cart_item")
    static UpdateDSL<UpdateModel> updateAllColumns(CartItemDAO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(customerId).equalTo(record::getCustomerId)
                .set(productId).equalTo(record::getProductId)
                .set(cartId).equalTo(record::getCartId)
                .set(quantity).equalTo(record::getQuantity)
                .set(totalPrice).equalTo(record::getTotalPrice)
                .set(createdTime).equalTo(record::getCreatedTime)
                .set(updatedTime).equalTo(record::getUpdatedTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.833714+08:00", comments="Source Table: cart_item")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(CartItemDAO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(customerId).equalToWhenPresent(record::getCustomerId)
                .set(productId).equalToWhenPresent(record::getProductId)
                .set(cartId).equalToWhenPresent(record::getCartId)
                .set(quantity).equalToWhenPresent(record::getQuantity)
                .set(totalPrice).equalToWhenPresent(record::getTotalPrice)
                .set(createdTime).equalToWhenPresent(record::getCreatedTime)
                .set(updatedTime).equalToWhenPresent(record::getUpdatedTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.834582+08:00", comments="Source Table: cart_item")
    default int updateByPrimaryKey(CartItemDAO record) {
        return update(c ->
            c.set(customerId).equalTo(record::getCustomerId)
            .set(productId).equalTo(record::getProductId)
            .set(cartId).equalTo(record::getCartId)
            .set(quantity).equalTo(record::getQuantity)
            .set(totalPrice).equalTo(record::getTotalPrice)
            .set(createdTime).equalTo(record::getCreatedTime)
            .set(updatedTime).equalTo(record::getUpdatedTime)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.835742+08:00", comments="Source Table: cart_item")
    default int updateByPrimaryKeySelective(CartItemDAO record) {
        return update(c ->
            c.set(customerId).equalToWhenPresent(record::getCustomerId)
            .set(productId).equalToWhenPresent(record::getProductId)
            .set(cartId).equalToWhenPresent(record::getCartId)
            .set(quantity).equalToWhenPresent(record::getQuantity)
            .set(totalPrice).equalToWhenPresent(record::getTotalPrice)
            .set(createdTime).equalToWhenPresent(record::getCreatedTime)
            .set(updatedTime).equalToWhenPresent(record::getUpdatedTime)
            .where(id, isEqualTo(record::getId))
        );
    }
}