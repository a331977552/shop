package org.shop.mapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class OrderItemDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.045815+08:00", comments="Source Table: order_item")
    public static final OrderItem orderItem = new OrderItem();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.053028+08:00", comments="Source field: order_item.id")
    public static final SqlColumn<String> id = orderItem.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.05341+08:00", comments="Source field: order_item.customer_id")
    public static final SqlColumn<Integer> customerId = orderItem.customerId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.053532+08:00", comments="Source field: order_item.order_id")
    public static final SqlColumn<Integer> orderId = orderItem.orderId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.053623+08:00", comments="Source field: order_item.quantity")
    public static final SqlColumn<Integer> quantity = orderItem.quantity;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.056218+08:00", comments="Source field: order_item.product_id")
    public static final SqlColumn<Integer> productId = orderItem.productId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.058179+08:00", comments="Source field: order_item.snapshot_product_id")
    public static final SqlColumn<Integer> snapshotProductId = orderItem.snapshotProductId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.060172+08:00", comments="Source field: order_item.price")
    public static final SqlColumn<Long> price = orderItem.price;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.063834+08:00", comments="Source field: order_item.created_time")
    public static final SqlColumn<Date> createdTime = orderItem.createdTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.06618+08:00", comments="Source field: order_item.updated_time")
    public static final SqlColumn<Date> updatedTime = orderItem.updatedTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.048944+08:00", comments="Source Table: order_item")
    public static final class OrderItem extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.CHAR);

        public final SqlColumn<Integer> customerId = column("customer_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> orderId = column("order_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> quantity = column("quantity", JDBCType.INTEGER);

        public final SqlColumn<Integer> productId = column("product_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> snapshotProductId = column("snapshot_product_id", JDBCType.INTEGER);

        public final SqlColumn<Long> price = column("price", JDBCType.DECIMAL);

        public final SqlColumn<Date> createdTime = column("created_time", JDBCType.DATE);

        public final SqlColumn<Date> updatedTime = column("updated_time", JDBCType.DATE);

        public OrderItem() {
            super("order_item");
        }
    }
}