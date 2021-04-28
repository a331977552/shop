package org.shop.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static org.shop.mapper.CustomerDynamicSqlSupport.*;

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
import org.shop.model.Customer;

@Mapper
public interface CustomerMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.006516+08:00", comments="Source Table: customer")
    BasicColumn[] selectList = BasicColumn.columnList(id, username, password, email, alias, avatar, dateOfBirth, role, createdTime, updatedTime);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.996658+08:00", comments="Source Table: customer")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.996896+08:00", comments="Source Table: customer")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.996956+08:00", comments="Source Table: customer")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<Customer> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.997006+08:00", comments="Source Table: customer")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<Customer> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.997061+08:00", comments="Source Table: customer")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("CustomerResult")
    Optional<Customer> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.997153+08:00", comments="Source Table: customer")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="CustomerResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="alias", property="alias", jdbcType=JdbcType.VARCHAR),
        @Result(column="avatar", property="avatar", jdbcType=JdbcType.INTEGER),
        @Result(column="date_of_birth", property="dateOfBirth", jdbcType=JdbcType.DATE),
        @Result(column="role", property="role", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.DATE),
        @Result(column="updated_time", property="updatedTime", jdbcType=JdbcType.DATE)
    })
    List<Customer> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.999011+08:00", comments="Source Table: customer")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.999317+08:00", comments="Source Table: customer")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, customer, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.999374+08:00", comments="Source Table: customer")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, customer, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.999415+08:00", comments="Source Table: customer")
    default int deleteByPrimaryKey(String id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.999472+08:00", comments="Source Table: customer")
    default int insert(Customer record) {
        return MyBatis3Utils.insert(this::insert, record, customer, c ->
            c.map(id).toProperty("id")
            .map(username).toProperty("username")
            .map(password).toProperty("password")
            .map(email).toProperty("email")
            .map(alias).toProperty("alias")
            .map(avatar).toProperty("avatar")
            .map(dateOfBirth).toProperty("dateOfBirth")
            .map(role).toProperty("role")
            .map(createdTime).toProperty("createdTime")
            .map(updatedTime).toProperty("updatedTime")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.005256+08:00", comments="Source Table: customer")
    default int insertMultiple(Collection<Customer> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, customer, c ->
            c.map(id).toProperty("id")
            .map(username).toProperty("username")
            .map(password).toProperty("password")
            .map(email).toProperty("email")
            .map(alias).toProperty("alias")
            .map(avatar).toProperty("avatar")
            .map(dateOfBirth).toProperty("dateOfBirth")
            .map(role).toProperty("role")
            .map(createdTime).toProperty("createdTime")
            .map(updatedTime).toProperty("updatedTime")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.006303+08:00", comments="Source Table: customer")
    default int insertSelective(Customer record) {
        return MyBatis3Utils.insert(this::insert, record, customer, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(username).toPropertyWhenPresent("username", record::getUsername)
            .map(password).toPropertyWhenPresent("password", record::getPassword)
            .map(email).toPropertyWhenPresent("email", record::getEmail)
            .map(alias).toPropertyWhenPresent("alias", record::getAlias)
            .map(avatar).toPropertyWhenPresent("avatar", record::getAvatar)
            .map(dateOfBirth).toPropertyWhenPresent("dateOfBirth", record::getDateOfBirth)
            .map(role).toPropertyWhenPresent("role", record::getRole)
            .map(createdTime).toPropertyWhenPresent("createdTime", record::getCreatedTime)
            .map(updatedTime).toPropertyWhenPresent("updatedTime", record::getUpdatedTime)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.006629+08:00", comments="Source Table: customer")
    default Optional<Customer> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, customer, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.006725+08:00", comments="Source Table: customer")
    default List<Customer> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, customer, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.006773+08:00", comments="Source Table: customer")
    default List<Customer> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, customer, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.006818+08:00", comments="Source Table: customer")
    default Optional<Customer> selectByPrimaryKey(String id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.006865+08:00", comments="Source Table: customer")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, customer, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.006949+08:00", comments="Source Table: customer")
    static UpdateDSL<UpdateModel> updateAllColumns(Customer record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(username).equalTo(record::getUsername)
                .set(password).equalTo(record::getPassword)
                .set(email).equalTo(record::getEmail)
                .set(alias).equalTo(record::getAlias)
                .set(avatar).equalTo(record::getAvatar)
                .set(dateOfBirth).equalTo(record::getDateOfBirth)
                .set(role).equalTo(record::getRole)
                .set(createdTime).equalTo(record::getCreatedTime)
                .set(updatedTime).equalTo(record::getUpdatedTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.007607+08:00", comments="Source Table: customer")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Customer record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(username).equalToWhenPresent(record::getUsername)
                .set(password).equalToWhenPresent(record::getPassword)
                .set(email).equalToWhenPresent(record::getEmail)
                .set(alias).equalToWhenPresent(record::getAlias)
                .set(avatar).equalToWhenPresent(record::getAvatar)
                .set(dateOfBirth).equalToWhenPresent(record::getDateOfBirth)
                .set(role).equalToWhenPresent(record::getRole)
                .set(createdTime).equalToWhenPresent(record::getCreatedTime)
                .set(updatedTime).equalToWhenPresent(record::getUpdatedTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.007884+08:00", comments="Source Table: customer")
    default int updateByPrimaryKey(Customer record) {
        return update(c ->
            c.set(username).equalTo(record::getUsername)
            .set(password).equalTo(record::getPassword)
            .set(email).equalTo(record::getEmail)
            .set(alias).equalTo(record::getAlias)
            .set(avatar).equalTo(record::getAvatar)
            .set(dateOfBirth).equalTo(record::getDateOfBirth)
            .set(role).equalTo(record::getRole)
            .set(createdTime).equalTo(record::getCreatedTime)
            .set(updatedTime).equalTo(record::getUpdatedTime)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.008004+08:00", comments="Source Table: customer")
    default int updateByPrimaryKeySelective(Customer record) {
        return update(c ->
            c.set(username).equalToWhenPresent(record::getUsername)
            .set(password).equalToWhenPresent(record::getPassword)
            .set(email).equalToWhenPresent(record::getEmail)
            .set(alias).equalToWhenPresent(record::getAlias)
            .set(avatar).equalToWhenPresent(record::getAvatar)
            .set(dateOfBirth).equalToWhenPresent(record::getDateOfBirth)
            .set(role).equalToWhenPresent(record::getRole)
            .set(createdTime).equalToWhenPresent(record::getCreatedTime)
            .set(updatedTime).equalToWhenPresent(record::getUpdatedTime)
            .where(id, isEqualTo(record::getId))
        );
    }
}