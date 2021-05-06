package org.shop.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static org.shop.mapper.CustomerAddressDAODynamicSqlSupport.*;

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
import org.shop.model.dao.CustomerAddressDAO;

@Mapper
public interface CustomerAddressDAOMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.870777+08:00", comments="Source Table: customer_address")
    BasicColumn[] selectList = BasicColumn.columnList(id, customerId, postCode, homeAddress, phoneNumber, createdTime, updatedTime);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.869921+08:00", comments="Source Table: customer_address")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.870027+08:00", comments="Source Table: customer_address")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.870065+08:00", comments="Source Table: customer_address")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<CustomerAddressDAO> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.870111+08:00", comments="Source Table: customer_address")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<CustomerAddressDAO> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.870153+08:00", comments="Source Table: customer_address")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("CustomerAddressDAOResult")
    Optional<CustomerAddressDAO> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.8702+08:00", comments="Source Table: customer_address")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="CustomerAddressDAOResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="customer_id", property="customerId", jdbcType=JdbcType.INTEGER),
        @Result(column="post_code", property="postCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="home_address", property="homeAddress", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone_number", property="phoneNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_time", property="updatedTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<CustomerAddressDAO> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.870373+08:00", comments="Source Table: customer_address")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.87042+08:00", comments="Source Table: customer_address")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, customerAddressDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.870453+08:00", comments="Source Table: customer_address")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, customerAddressDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.870485+08:00", comments="Source Table: customer_address")
    default int deleteByPrimaryKey(String id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.870518+08:00", comments="Source Table: customer_address")
    default int insert(CustomerAddressDAO record) {
        return MyBatis3Utils.insert(this::insert, record, customerAddressDAO, c ->
            c.map(id).toProperty("id")
            .map(customerId).toProperty("customerId")
            .map(postCode).toProperty("postCode")
            .map(homeAddress).toProperty("homeAddress")
            .map(phoneNumber).toProperty("phoneNumber")
            .map(createdTime).toProperty("createdTime")
            .map(updatedTime).toProperty("updatedTime")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.870595+08:00", comments="Source Table: customer_address")
    default int insertMultiple(Collection<CustomerAddressDAO> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, customerAddressDAO, c ->
            c.map(id).toProperty("id")
            .map(customerId).toProperty("customerId")
            .map(postCode).toProperty("postCode")
            .map(homeAddress).toProperty("homeAddress")
            .map(phoneNumber).toProperty("phoneNumber")
            .map(createdTime).toProperty("createdTime")
            .map(updatedTime).toProperty("updatedTime")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.870653+08:00", comments="Source Table: customer_address")
    default int insertSelective(CustomerAddressDAO record) {
        return MyBatis3Utils.insert(this::insert, record, customerAddressDAO, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(customerId).toPropertyWhenPresent("customerId", record::getCustomerId)
            .map(postCode).toPropertyWhenPresent("postCode", record::getPostCode)
            .map(homeAddress).toPropertyWhenPresent("homeAddress", record::getHomeAddress)
            .map(phoneNumber).toPropertyWhenPresent("phoneNumber", record::getPhoneNumber)
            .map(createdTime).toPropertyWhenPresent("createdTime", record::getCreatedTime)
            .map(updatedTime).toPropertyWhenPresent("updatedTime", record::getUpdatedTime)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.870816+08:00", comments="Source Table: customer_address")
    default Optional<CustomerAddressDAO> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, customerAddressDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.873377+08:00", comments="Source Table: customer_address")
    default List<CustomerAddressDAO> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, customerAddressDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.873859+08:00", comments="Source Table: customer_address")
    default List<CustomerAddressDAO> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, customerAddressDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.873999+08:00", comments="Source Table: customer_address")
    default Optional<CustomerAddressDAO> selectByPrimaryKey(String id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.874066+08:00", comments="Source Table: customer_address")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, customerAddressDAO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.874107+08:00", comments="Source Table: customer_address")
    static UpdateDSL<UpdateModel> updateAllColumns(CustomerAddressDAO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(customerId).equalTo(record::getCustomerId)
                .set(postCode).equalTo(record::getPostCode)
                .set(homeAddress).equalTo(record::getHomeAddress)
                .set(phoneNumber).equalTo(record::getPhoneNumber)
                .set(createdTime).equalTo(record::getCreatedTime)
                .set(updatedTime).equalTo(record::getUpdatedTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.874359+08:00", comments="Source Table: customer_address")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(CustomerAddressDAO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(customerId).equalToWhenPresent(record::getCustomerId)
                .set(postCode).equalToWhenPresent(record::getPostCode)
                .set(homeAddress).equalToWhenPresent(record::getHomeAddress)
                .set(phoneNumber).equalToWhenPresent(record::getPhoneNumber)
                .set(createdTime).equalToWhenPresent(record::getCreatedTime)
                .set(updatedTime).equalToWhenPresent(record::getUpdatedTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.874475+08:00", comments="Source Table: customer_address")
    default int updateByPrimaryKey(CustomerAddressDAO record) {
        return update(c ->
            c.set(customerId).equalTo(record::getCustomerId)
            .set(postCode).equalTo(record::getPostCode)
            .set(homeAddress).equalTo(record::getHomeAddress)
            .set(phoneNumber).equalTo(record::getPhoneNumber)
            .set(createdTime).equalTo(record::getCreatedTime)
            .set(updatedTime).equalTo(record::getUpdatedTime)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.874555+08:00", comments="Source Table: customer_address")
    default int updateByPrimaryKeySelective(CustomerAddressDAO record) {
        return update(c ->
            c.set(customerId).equalToWhenPresent(record::getCustomerId)
            .set(postCode).equalToWhenPresent(record::getPostCode)
            .set(homeAddress).equalToWhenPresent(record::getHomeAddress)
            .set(phoneNumber).equalToWhenPresent(record::getPhoneNumber)
            .set(createdTime).equalToWhenPresent(record::getCreatedTime)
            .set(updatedTime).equalToWhenPresent(record::getUpdatedTime)
            .where(id, isEqualTo(record::getId))
        );
    }
}