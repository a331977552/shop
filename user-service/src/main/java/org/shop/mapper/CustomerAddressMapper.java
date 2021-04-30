package org.shop.mapper;

import org.apache.ibatis.annotations.*;
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

import javax.annotation.Generated;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.shop.mapper.CustomerAddressDynamicSqlSupport.createdTime;
import static org.shop.mapper.CustomerAddressDynamicSqlSupport.customerAddress;
import static org.shop.mapper.CustomerAddressDynamicSqlSupport.customerId;
import static org.shop.mapper.CustomerAddressDynamicSqlSupport.homeAddress;
import static org.shop.mapper.CustomerAddressDynamicSqlSupport.id;
import static org.shop.mapper.CustomerAddressDynamicSqlSupport.phoneNumber;
import static org.shop.mapper.CustomerAddressDynamicSqlSupport.postCode;
import static org.shop.mapper.CustomerAddressDynamicSqlSupport.updatedTime;

@Mapper
public interface CustomerAddressMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.022495+08:00", comments="Source Table: customer_address")
    BasicColumn[] selectList = BasicColumn.columnList(id, customerId, postCode, homeAddress, phoneNumber, createdTime, updatedTime);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.01755+08:00", comments="Source Table: customer_address")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.017952+08:00", comments="Source Table: customer_address")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.018012+08:00", comments="Source Table: customer_address")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<CustomerAddressDAO> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.020108+08:00", comments="Source Table: customer_address")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<CustomerAddressDAO> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.020208+08:00", comments="Source Table: customer_address")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("CustomerAddressResult")
    Optional<CustomerAddressDAO> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.020294+08:00", comments="Source Table: customer_address")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="CustomerAddressResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="customer_id", property="customerId", jdbcType=JdbcType.INTEGER),
        @Result(column="post_code", property="postCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="home_address", property="homeAddress", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone_number", property="phoneNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.DATE),
        @Result(column="updated_time", property="updatedTime", jdbcType=JdbcType.DATE)
    })
    List<CustomerAddressDAO> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.020463+08:00", comments="Source Table: customer_address")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.020506+08:00", comments="Source Table: customer_address")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, customerAddress, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.021849+08:00", comments="Source Table: customer_address")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, customerAddress, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.021991+08:00", comments="Source Table: customer_address")
    default int deleteByPrimaryKey(String id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.022064+08:00", comments="Source Table: customer_address")
    default int insert(CustomerAddressDAO record) {
        return MyBatis3Utils.insert(this::insert, record, customerAddress, c ->
            c.map(id).toProperty("id")
            .map(customerId).toProperty("customerId")
            .map(postCode).toProperty("postCode")
            .map(homeAddress).toProperty("homeAddress")
            .map(phoneNumber).toProperty("phoneNumber")
            .map(createdTime).toProperty("createdTime")
            .map(updatedTime).toProperty("updatedTime")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.022224+08:00", comments="Source Table: customer_address")
    default int insertMultiple(Collection<CustomerAddressDAO> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, customerAddress, c ->
            c.map(id).toProperty("id")
            .map(customerId).toProperty("customerId")
            .map(postCode).toProperty("postCode")
            .map(homeAddress).toProperty("homeAddress")
            .map(phoneNumber).toProperty("phoneNumber")
            .map(createdTime).toProperty("createdTime")
            .map(updatedTime).toProperty("updatedTime")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.022317+08:00", comments="Source Table: customer_address")
    default int insertSelective(CustomerAddressDAO record) {
        return MyBatis3Utils.insert(this::insert, record, customerAddress, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(customerId).toPropertyWhenPresent("customerId", record::getCustomerId)
            .map(postCode).toPropertyWhenPresent("postCode", record::getPostCode)
            .map(homeAddress).toPropertyWhenPresent("homeAddress", record::getHomeAddress)
            .map(phoneNumber).toPropertyWhenPresent("phoneNumber", record::getPhoneNumber)
            .map(createdTime).toPropertyWhenPresent("createdTime", record::getCreatedTime)
            .map(updatedTime).toPropertyWhenPresent("updatedTime", record::getUpdatedTime)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.022545+08:00", comments="Source Table: customer_address")
    default Optional<CustomerAddressDAO> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, customerAddress, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.022587+08:00", comments="Source Table: customer_address")
    default List<CustomerAddressDAO> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, customerAddress, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.02263+08:00", comments="Source Table: customer_address")
    default List<CustomerAddressDAO> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, customerAddress, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.022668+08:00", comments="Source Table: customer_address")
    default Optional<CustomerAddressDAO> selectByPrimaryKey(String id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.022709+08:00", comments="Source Table: customer_address")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, customerAddress, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.022793+08:00", comments="Source Table: customer_address")
    static UpdateDSL<UpdateModel> updateAllColumns(CustomerAddressDAO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(customerId).equalTo(record::getCustomerId)
                .set(postCode).equalTo(record::getPostCode)
                .set(homeAddress).equalTo(record::getHomeAddress)
                .set(phoneNumber).equalTo(record::getPhoneNumber)
                .set(createdTime).equalTo(record::getCreatedTime)
                .set(updatedTime).equalTo(record::getUpdatedTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.02298+08:00", comments="Source Table: customer_address")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(CustomerAddressDAO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(customerId).equalToWhenPresent(record::getCustomerId)
                .set(postCode).equalToWhenPresent(record::getPostCode)
                .set(homeAddress).equalToWhenPresent(record::getHomeAddress)
                .set(phoneNumber).equalToWhenPresent(record::getPhoneNumber)
                .set(createdTime).equalToWhenPresent(record::getCreatedTime)
                .set(updatedTime).equalToWhenPresent(record::getUpdatedTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.023103+08:00", comments="Source Table: customer_address")
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

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.023182+08:00", comments="Source Table: customer_address")
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