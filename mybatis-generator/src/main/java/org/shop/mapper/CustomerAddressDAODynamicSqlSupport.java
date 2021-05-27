package org.shop.mapper;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class CustomerAddressDAODynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final CustomerAddressDAO customerAddressDAO = new CustomerAddressDAO();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> id = customerAddressDAO.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> customerId = customerAddressDAO.customerId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> postCode = customerAddressDAO.postCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> homeAddress = customerAddressDAO.homeAddress;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> phoneNumber = customerAddressDAO.phoneNumber;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<LocalDateTime> createdTime = customerAddressDAO.createdTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<LocalDateTime> updatedTime = customerAddressDAO.updatedTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class CustomerAddressDAO extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.CHAR);

        public final SqlColumn<String> customerId = column("customer_id", JDBCType.CHAR);

        public final SqlColumn<String> postCode = column("post_code", JDBCType.VARCHAR);

        public final SqlColumn<String> homeAddress = column("home_address", JDBCType.VARCHAR);

        public final SqlColumn<String> phoneNumber = column("phone_number", JDBCType.VARCHAR);

        public final SqlColumn<LocalDateTime> createdTime = column("created_time", JDBCType.TIMESTAMP);

        public final SqlColumn<LocalDateTime> updatedTime = column("updated_time", JDBCType.TIMESTAMP);

        public CustomerAddressDAO() {
            super("customer_address");
        }
    }
}