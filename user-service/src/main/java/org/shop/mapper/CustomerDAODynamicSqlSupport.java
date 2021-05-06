package org.shop.mapper;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;
import java.time.LocalDateTime;

public final class CustomerDAODynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.842894+08:00", comments="Source Table: customer")
    public static final CustomerDAO customerDAO = new CustomerDAO();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.845811+08:00", comments="Source field: customer.id")
    public static final SqlColumn<String> id = customerDAO.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.846099+08:00", comments="Source field: customer.username")
    public static final SqlColumn<String> username = customerDAO.username;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.846209+08:00", comments="Source field: customer.password")
    public static final SqlColumn<String> password = customerDAO.password;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.846315+08:00", comments="Source field: customer.email")
    public static final SqlColumn<String> email = customerDAO.email;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.846412+08:00", comments="Source field: customer.phone")
    public static final SqlColumn<String> phone = customerDAO.phone;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.846514+08:00", comments="Source field: customer.alias")
    public static final SqlColumn<String> alias = customerDAO.alias;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.846796+08:00", comments="Source field: customer.avatar")
    public static final SqlColumn<String> avatar = customerDAO.avatar;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.846924+08:00", comments="Source field: customer.date_of_birth")
    public static final SqlColumn<LocalDateTime> dateOfBirth = customerDAO.dateOfBirth;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.847013+08:00", comments="Source field: customer.role")
    public static final SqlColumn<String> role = customerDAO.role;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.847554+08:00", comments="Source field: customer.created_time")
    public static final SqlColumn<LocalDateTime> createdTime = customerDAO.createdTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.848596+08:00", comments="Source field: customer.updated_time")
    public static final SqlColumn<LocalDateTime> updatedTime = customerDAO.updatedTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.843409+08:00", comments="Source Table: customer")
    public static final class CustomerDAO extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.CHAR);

        public final SqlColumn<String> username = column("username", JDBCType.VARCHAR);

        public final SqlColumn<String> password = column("password", JDBCType.VARCHAR);

        public final SqlColumn<String> email = column("email", JDBCType.VARCHAR);

        public final SqlColumn<String> phone = column("phone", JDBCType.VARCHAR);

        public final SqlColumn<String> alias = column("alias", JDBCType.VARCHAR);

        public final SqlColumn<String> avatar = column("avatar", JDBCType.CHAR);

        public final SqlColumn<LocalDateTime> dateOfBirth = column("date_of_birth", JDBCType.TIMESTAMP);

        public final SqlColumn<String> role = column("role", JDBCType.VARCHAR);

        public final SqlColumn<LocalDateTime> createdTime = column("created_time", JDBCType.TIMESTAMP);

        public final SqlColumn<LocalDateTime> updatedTime = column("updated_time", JDBCType.TIMESTAMP);

        public CustomerDAO() {
            super("customer");
        }
    }
}