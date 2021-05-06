package org.shop.mapper;

import java.math.BigDecimal;
import java.sql.JDBCType;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class ShopOrderDAODynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.957751+08:00", comments="Source Table: shop_order")
    public static final ShopOrderDAO shopOrderDAO = new ShopOrderDAO();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.958611+08:00", comments="Source field: shop_order.id")
    public static final SqlColumn<String> id = shopOrderDAO.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.959542+08:00", comments="Source field: shop_order.customer_id")
    public static final SqlColumn<Integer> customerId = shopOrderDAO.customerId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.961363+08:00", comments="Source field: shop_order.total_price")
    public static final SqlColumn<BigDecimal> totalPrice = shopOrderDAO.totalPrice;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.96469+08:00", comments="Source field: shop_order.created_time")
    public static final SqlColumn<LocalDateTime> createdTime = shopOrderDAO.createdTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.96587+08:00", comments="Source field: shop_order.updated_time")
    public static final SqlColumn<LocalDateTime> updatedTime = shopOrderDAO.updatedTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.95823+08:00", comments="Source Table: shop_order")
    public static final class ShopOrderDAO extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.CHAR);

        public final SqlColumn<Integer> customerId = column("customer_id", JDBCType.INTEGER);

        public final SqlColumn<BigDecimal> totalPrice = column("total_price", JDBCType.DECIMAL);

        public final SqlColumn<LocalDateTime> createdTime = column("created_time", JDBCType.TIMESTAMP);

        public final SqlColumn<LocalDateTime> updatedTime = column("updated_time", JDBCType.TIMESTAMP);

        public ShopOrderDAO() {
            super("shop_order");
        }
    }
}