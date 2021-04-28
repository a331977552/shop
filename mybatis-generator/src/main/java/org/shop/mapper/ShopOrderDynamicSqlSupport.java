package org.shop.mapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class ShopOrderDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.117843+08:00", comments="Source Table: shop_order")
    public static final ShopOrder shopOrder = new ShopOrder();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.119171+08:00", comments="Source field: shop_order.id")
    public static final SqlColumn<String> id = shopOrder.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.123144+08:00", comments="Source field: shop_order.customer_id")
    public static final SqlColumn<Integer> customerId = shopOrder.customerId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.124476+08:00", comments="Source field: shop_order.total_price")
    public static final SqlColumn<Long> totalPrice = shopOrder.totalPrice;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.127333+08:00", comments="Source field: shop_order.created_time")
    public static final SqlColumn<Date> createdTime = shopOrder.createdTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.129937+08:00", comments="Source field: shop_order.updated_time")
    public static final SqlColumn<Date> updatedTime = shopOrder.updatedTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.118484+08:00", comments="Source Table: shop_order")
    public static final class ShopOrder extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.CHAR);

        public final SqlColumn<Integer> customerId = column("customer_id", JDBCType.INTEGER);

        public final SqlColumn<Long> totalPrice = column("total_price", JDBCType.DECIMAL);

        public final SqlColumn<Date> createdTime = column("created_time", JDBCType.DATE);

        public final SqlColumn<Date> updatedTime = column("updated_time", JDBCType.DATE);

        public ShopOrder() {
            super("shop_order");
        }
    }
}