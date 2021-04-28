package org.shop.mapper;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;
import java.util.Date;

public final class CustomerAddressDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.011652+08:00", comments="Source Table: customer_address")
    public static final CustomerAddress customerAddress = new CustomerAddress();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.014088+08:00", comments="Source field: customer_address.id")
    public static final SqlColumn<String> id = customerAddress.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.014251+08:00", comments="Source field: customer_address.customer_id")
    public static final SqlColumn<Integer> customerId = customerAddress.customerId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.015336+08:00", comments="Source field: customer_address.post_code")
    public static final SqlColumn<String> postCode = customerAddress.postCode;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.015559+08:00", comments="Source field: customer_address.home_address")
    public static final SqlColumn<String> homeAddress = customerAddress.homeAddress;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.015681+08:00", comments="Source field: customer_address.phone_number")
    public static final SqlColumn<String> phoneNumber = customerAddress.phoneNumber;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.015802+08:00", comments="Source field: customer_address.created_time")
    public static final SqlColumn<Date> createdTime = customerAddress.createdTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.015925+08:00", comments="Source field: customer_address.updated_time")
    public static final SqlColumn<Date> updatedTime = customerAddress.updatedTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.013491+08:00", comments="Source Table: customer_address")
    public static final class CustomerAddress extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.CHAR);

        public final SqlColumn<Integer> customerId = column("customer_id", JDBCType.INTEGER);

        public final SqlColumn<String> postCode = column("post_code", JDBCType.VARCHAR);

        public final SqlColumn<String> homeAddress = column("home_address", JDBCType.VARCHAR);

        public final SqlColumn<String> phoneNumber = column("phone_number", JDBCType.VARCHAR);

        public final SqlColumn<Date> createdTime = column("created_time", JDBCType.DATE);

        public final SqlColumn<Date> updatedTime = column("updated_time", JDBCType.DATE);

        public CustomerAddress() {
            super("customer_address");
        }
    }
}