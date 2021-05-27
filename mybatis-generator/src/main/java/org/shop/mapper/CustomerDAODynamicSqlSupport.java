package org.shop.mapper;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class CustomerDAODynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final CustomerDAO customerDAO = new CustomerDAO();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> id = customerDAO.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> username = customerDAO.username;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> password = customerDAO.password;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> email = customerDAO.email;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> phone = customerDAO.phone;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> alias = customerDAO.alias;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> avatar = customerDAO.avatar;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<LocalDateTime> dateOfBirth = customerDAO.dateOfBirth;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> role = customerDAO.role;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<LocalDateTime> createdTime = customerDAO.createdTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<LocalDateTime> updatedTime = customerDAO.updatedTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
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