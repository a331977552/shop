package org.shop.mapper;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;
import java.time.LocalDateTime;

public final class CustomerAddressDAODynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.863282+08:00", comments="Source Table: customer_address")
    public static final CustomerAddressDAO customerAddressDAO = new CustomerAddressDAO();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.866544+08:00", comments="Source field: customer_address.id")
    public static final SqlColumn<String> id = customerAddressDAO.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.867291+08:00", comments="Source field: customer_address.customer_id")
    public static final SqlColumn<Integer> customerId = customerAddressDAO.customerId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.867455+08:00", comments="Source field: customer_address.post_code")
    public static final SqlColumn<String> postCode = customerAddressDAO.postCode;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.867548+08:00", comments="Source field: customer_address.home_address")
    public static final SqlColumn<String> homeAddress = customerAddressDAO.homeAddress;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.867779+08:00", comments="Source field: customer_address.phone_number")
    public static final SqlColumn<String> phoneNumber = customerAddressDAO.phoneNumber;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.867999+08:00", comments="Source field: customer_address.created_time")
    public static final SqlColumn<LocalDateTime> createdTime = customerAddressDAO.createdTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.868602+08:00", comments="Source field: customer_address.updated_time")
    public static final SqlColumn<LocalDateTime> updatedTime = customerAddressDAO.updatedTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.864213+08:00", comments="Source Table: customer_address")
    public static final class CustomerAddressDAO extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.CHAR);

        public final SqlColumn<Integer> customerId = column("customer_id", JDBCType.INTEGER);

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