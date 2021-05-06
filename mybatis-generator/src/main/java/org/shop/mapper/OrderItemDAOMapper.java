package org.shop.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static org.shop.mapper.OrderItemDAODynamicSqlSupport.*;

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
import org.shop.model.dao.OrderItemDAO;

@Mapper
public interface OrderItemDAOMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.924541+08:00", comments="Source Table: order_item")
    BasicColumn[] selectList = BasicColumn.columnList(id, customerId, orderId, quantity, productId, snapshotProductId, price, createdTime, updatedTime);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.922009+08:00", comments="Source Table: order_item")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.922172+08:00", comments="Source Table: order_item")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.922205+08:00", comments="Source Table: order_item")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<OrderItemDAO> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.922238+08:00", comments="Source Table: order_item")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<OrderItemDAO> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.922278+08:00", comments="Source Table: order_item")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("OrderItemDAOResult")
    Optional<OrderItemDAO> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.922336+08:00", comments="Source Table: order_item")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="OrderItemDAOResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="customer_id", property="customerId", jdbcType=JdbcType.INTEGER),
        @Result(column="order_id", property="orderId", jdbcType=JdbcType.INTEGER),
        @Result(column="quantity", property="quantity", jdbcType=JdbcType.INTEGER),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.INTEGER),
        @Result(column="snapshot_product_id", property="snapshotProductId", jdbcType=JdbcType.INTEGER),
        @Result(column="price", property="price", jdbcType=JdbcType.DECIMAL),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_time", property="updatedTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<OrderItemDAO> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.922868+08:00", comments="Source Table: order_item")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.922908+08:00", comments="Source Table: order_item")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, orderItemDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.922935+08:00", comments="Source Table: order_item")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, orderItemDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.922964+08:00", comments="Source Table: order_item")
    default int deleteByPrimaryKey(String id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.923015+08:00", comments="Source Table: order_item")
    default int insert(OrderItemDAO record) {
        return MyBatis3Utils.insert(this::insert, record, orderItemDAO, c ->
            c.map(id).toProperty("id")
            .map(customerId).toProperty("customerId")
            .map(orderId).toProperty("orderId")
            .map(quantity).toProperty("quantity")
            .map(productId).toProperty("productId")
            .map(snapshotProductId).toProperty("snapshotProductId")
            .map(price).toProperty("price")
            .map(createdTime).toProperty("createdTime")
            .map(updatedTime).toProperty("updatedTime")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.923095+08:00", comments="Source Table: order_item")
    default int insertMultiple(Collection<OrderItemDAO> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, orderItemDAO, c ->
            c.map(id).toProperty("id")
            .map(customerId).toProperty("customerId")
            .map(orderId).toProperty("orderId")
            .map(quantity).toProperty("quantity")
            .map(productId).toProperty("productId")
            .map(snapshotProductId).toProperty("snapshotProductId")
            .map(price).toProperty("price")
            .map(createdTime).toProperty("createdTime")
            .map(updatedTime).toProperty("updatedTime")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.923147+08:00", comments="Source Table: order_item")
    default int insertSelective(OrderItemDAO record) {
        return MyBatis3Utils.insert(this::insert, record, orderItemDAO, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(customerId).toPropertyWhenPresent("customerId", record::getCustomerId)
            .map(orderId).toPropertyWhenPresent("orderId", record::getOrderId)
            .map(quantity).toPropertyWhenPresent("quantity", record::getQuantity)
            .map(productId).toPropertyWhenPresent("productId", record::getProductId)
            .map(snapshotProductId).toPropertyWhenPresent("snapshotProductId", record::getSnapshotProductId)
            .map(price).toPropertyWhenPresent("price", record::getPrice)
            .map(createdTime).toPropertyWhenPresent("createdTime", record::getCreatedTime)
            .map(updatedTime).toPropertyWhenPresent("updatedTime", record::getUpdatedTime)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.925157+08:00", comments="Source Table: order_item")
    default Optional<OrderItemDAO> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, orderItemDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.925209+08:00", comments="Source Table: order_item")
    default List<OrderItemDAO> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, orderItemDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.925247+08:00", comments="Source Table: order_item")
    default List<OrderItemDAO> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, orderItemDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.925283+08:00", comments="Source Table: order_item")
    default Optional<OrderItemDAO> selectByPrimaryKey(String id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.925355+08:00", comments="Source Table: order_item")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, orderItemDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.925393+08:00", comments="Source Table: order_item")
    static UpdateDSL<UpdateModel> updateAllColumns(OrderItemDAO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(customerId).equalTo(record::getCustomerId)
                .set(orderId).equalTo(record::getOrderId)
                .set(quantity).equalTo(record::getQuantity)
                .set(productId).equalTo(record::getProductId)
                .set(snapshotProductId).equalTo(record::getSnapshotProductId)
                .set(price).equalTo(record::getPrice)
                .set(createdTime).equalTo(record::getCreatedTime)
                .set(updatedTime).equalTo(record::getUpdatedTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.925522+08:00", comments="Source Table: order_item")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(OrderItemDAO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(customerId).equalToWhenPresent(record::getCustomerId)
                .set(orderId).equalToWhenPresent(record::getOrderId)
                .set(quantity).equalToWhenPresent(record::getQuantity)
                .set(productId).equalToWhenPresent(record::getProductId)
                .set(snapshotProductId).equalToWhenPresent(record::getSnapshotProductId)
                .set(price).equalToWhenPresent(record::getPrice)
                .set(createdTime).equalToWhenPresent(record::getCreatedTime)
                .set(updatedTime).equalToWhenPresent(record::getUpdatedTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.925609+08:00", comments="Source Table: order_item")
    default int updateByPrimaryKey(OrderItemDAO record) {
        return update(c ->
            c.set(customerId).equalTo(record::getCustomerId)
            .set(orderId).equalTo(record::getOrderId)
            .set(quantity).equalTo(record::getQuantity)
            .set(productId).equalTo(record::getProductId)
            .set(snapshotProductId).equalTo(record::getSnapshotProductId)
            .set(price).equalTo(record::getPrice)
            .set(createdTime).equalTo(record::getCreatedTime)
            .set(updatedTime).equalTo(record::getUpdatedTime)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.925699+08:00", comments="Source Table: order_item")
    default int updateByPrimaryKeySelective(OrderItemDAO record) {
        return update(c ->
            c.set(customerId).equalToWhenPresent(record::getCustomerId)
            .set(orderId).equalToWhenPresent(record::getOrderId)
            .set(quantity).equalToWhenPresent(record::getQuantity)
            .set(productId).equalToWhenPresent(record::getProductId)
            .set(snapshotProductId).equalToWhenPresent(record::getSnapshotProductId)
            .set(price).equalToWhenPresent(record::getPrice)
            .set(createdTime).equalToWhenPresent(record::getCreatedTime)
            .set(updatedTime).equalToWhenPresent(record::getUpdatedTime)
            .where(id, isEqualTo(record::getId))
        );
    }
}