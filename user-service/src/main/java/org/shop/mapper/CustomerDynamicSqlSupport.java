package org.shop.mapper;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;
import java.util.Date;

public final class CustomerDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.989434+08:00", comments="Source Table: customer")
    public static final Customer customer = new Customer();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.99368+08:00", comments="Source field: customer.id")
    public static final SqlColumn<String> id = customer.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.99393+08:00", comments="Source field: customer.username")
    public static final SqlColumn<String> username = customer.username;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.994047+08:00", comments="Source field: customer.password")
    public static final SqlColumn<String> password = customer.password;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.994142+08:00", comments="Source field: customer.email")
    public static final SqlColumn<String> email = customer.email;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.994267+08:00", comments="Source field: customer.alias")
    public static final SqlColumn<String> alias = customer.alias;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.994492+08:00", comments="Source field: customer.avatar")
    public static final SqlColumn<Integer> avatar = customer.avatar;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.994902+08:00", comments="Source field: customer.date_of_birth")
    public static final SqlColumn<Date> dateOfBirth = customer.dateOfBirth;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.995087+08:00", comments="Source field: customer.role")
    public static final SqlColumn<String> role = customer.role;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.995197+08:00", comments="Source field: customer.created_time")
    public static final SqlColumn<Date> createdTime = customer.createdTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.995863+08:00", comments="Source field: customer.updated_time")
    public static final SqlColumn<Date> updatedTime = customer.updatedTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.991045+08:00", comments="Source Table: customer")
    public static final class Customer extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.CHAR);

        public final SqlColumn<String> username = column("username", JDBCType.VARCHAR);

        public final SqlColumn<String> password = column("password", JDBCType.VARCHAR);

        public final SqlColumn<String> email = column("email", JDBCType.VARCHAR);

        public final SqlColumn<String> alias = column("alias", JDBCType.VARCHAR);

        public final SqlColumn<Integer> avatar = column("avatar", JDBCType.INTEGER);

        public final SqlColumn<Date> dateOfBirth = column("date_of_birth", JDBCType.DATE);

        public final SqlColumn<String> role = column("role", JDBCType.VARCHAR);

        public final SqlColumn<Date> createdTime = column("created_time", JDBCType.DATE);

        public final SqlColumn<Date> updatedTime = column("updated_time", JDBCType.DATE);

        public Customer() {
            super("customer");
        }
    }
}