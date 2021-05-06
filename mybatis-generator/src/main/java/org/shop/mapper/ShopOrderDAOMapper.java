package org.shop.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static org.shop.mapper.ShopOrderDAODynamicSqlSupport.*;

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
import org.shop.model.dao.ShopOrderDAO;

@Mapper
public interface ShopOrderDAOMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.967633+08:00", comments="Source Table: shop_order")
    BasicColumn[] selectList = BasicColumn.columnList(id, customerId, totalPrice, createdTime, updatedTime);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.966413+08:00", comments="Source Table: shop_order")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.966495+08:00", comments="Source Table: shop_order")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.966524+08:00", comments="Source Table: shop_order")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<ShopOrderDAO> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.966551+08:00", comments="Source Table: shop_order")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<ShopOrderDAO> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.966582+08:00", comments="Source Table: shop_order")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("ShopOrderDAOResult")
    Optional<ShopOrderDAO> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.966617+08:00", comments="Source Table: shop_order")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="ShopOrderDAOResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="customer_id", property="customerId", jdbcType=JdbcType.INTEGER),
        @Result(column="total_price", property="totalPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_time", property="updatedTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ShopOrderDAO> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.966777+08:00", comments="Source Table: shop_order")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.966924+08:00", comments="Source Table: shop_order")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, shopOrderDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.967068+08:00", comments="Source Table: shop_order")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, shopOrderDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.967115+08:00", comments="Source Table: shop_order")
    default int deleteByPrimaryKey(String id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.967217+08:00", comments="Source Table: shop_order")
    default int insert(ShopOrderDAO record) {
        return MyBatis3Utils.insert(this::insert, record, shopOrderDAO, c ->
            c.map(id).toProperty("id")
            .map(customerId).toProperty("customerId")
            .map(totalPrice).toProperty("totalPrice")
            .map(createdTime).toProperty("createdTime")
            .map(updatedTime).toProperty("updatedTime")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.967293+08:00", comments="Source Table: shop_order")
    default int insertMultiple(Collection<ShopOrderDAO> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, shopOrderDAO, c ->
            c.map(id).toProperty("id")
            .map(customerId).toProperty("customerId")
            .map(totalPrice).toProperty("totalPrice")
            .map(createdTime).toProperty("createdTime")
            .map(updatedTime).toProperty("updatedTime")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.967347+08:00", comments="Source Table: shop_order")
    default int insertSelective(ShopOrderDAO record) {
        return MyBatis3Utils.insert(this::insert, record, shopOrderDAO, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(customerId).toPropertyWhenPresent("customerId", record::getCustomerId)
            .map(totalPrice).toPropertyWhenPresent("totalPrice", record::getTotalPrice)
            .map(createdTime).toPropertyWhenPresent("createdTime", record::getCreatedTime)
            .map(updatedTime).toPropertyWhenPresent("updatedTime", record::getUpdatedTime)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.967661+08:00", comments="Source Table: shop_order")
    default Optional<ShopOrderDAO> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, shopOrderDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.967689+08:00", comments="Source Table: shop_order")
    default List<ShopOrderDAO> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, shopOrderDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.967715+08:00", comments="Source Table: shop_order")
    default List<ShopOrderDAO> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, shopOrderDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.967741+08:00", comments="Source Table: shop_order")
    default Optional<ShopOrderDAO> selectByPrimaryKey(String id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.967896+08:00", comments="Source Table: shop_order")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, shopOrderDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.967944+08:00", comments="Source Table: shop_order")
    static UpdateDSL<UpdateModel> updateAllColumns(ShopOrderDAO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(customerId).equalTo(record::getCustomerId)
                .set(totalPrice).equalTo(record::getTotalPrice)
                .set(createdTime).equalTo(record::getCreatedTime)
                .set(updatedTime).equalTo(record::getUpdatedTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.968046+08:00", comments="Source Table: shop_order")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(ShopOrderDAO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(customerId).equalToWhenPresent(record::getCustomerId)
                .set(totalPrice).equalToWhenPresent(record::getTotalPrice)
                .set(createdTime).equalToWhenPresent(record::getCreatedTime)
                .set(updatedTime).equalToWhenPresent(record::getUpdatedTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.968096+08:00", comments="Source Table: shop_order")
    default int updateByPrimaryKey(ShopOrderDAO record) {
        return update(c ->
            c.set(customerId).equalTo(record::getCustomerId)
            .set(totalPrice).equalTo(record::getTotalPrice)
            .set(createdTime).equalTo(record::getCreatedTime)
            .set(updatedTime).equalTo(record::getUpdatedTime)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.968142+08:00", comments="Source Table: shop_order")
    default int updateByPrimaryKeySelective(ShopOrderDAO record) {
        return update(c ->
            c.set(customerId).equalToWhenPresent(record::getCustomerId)
            .set(totalPrice).equalToWhenPresent(record::getTotalPrice)
            .set(createdTime).equalToWhenPresent(record::getCreatedTime)
            .set(updatedTime).equalToWhenPresent(record::getUpdatedTime)
            .where(id, isEqualTo(record::getId))
        );
    }
}