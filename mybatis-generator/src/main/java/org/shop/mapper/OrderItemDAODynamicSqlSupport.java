package org.shop.mapper;

import java.math.BigDecimal;
import java.sql.JDBCType;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class OrderItemDAODynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.902073+08:00", comments="Source Table: order_item")
    public static final OrderItemDAO orderItemDAO = new OrderItemDAO();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.90344+08:00", comments="Source field: order_item.id")
    public static final SqlColumn<String> id = orderItemDAO.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.903814+08:00", comments="Source field: order_item.customer_id")
    public static final SqlColumn<Integer> customerId = orderItemDAO.customerId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.904963+08:00", comments="Source field: order_item.order_id")
    public static final SqlColumn<Integer> orderId = orderItemDAO.orderId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.910648+08:00", comments="Source field: order_item.quantity")
    public static final SqlColumn<Integer> quantity = orderItemDAO.quantity;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.912989+08:00", comments="Source field: order_item.product_id")
    public static final SqlColumn<Integer> productId = orderItemDAO.productId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.91454+08:00", comments="Source field: order_item.snapshot_product_id")
    public static final SqlColumn<Integer> snapshotProductId = orderItemDAO.snapshotProductId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.918075+08:00", comments="Source field: order_item.price")
    public static final SqlColumn<BigDecimal> price = orderItemDAO.price;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.920017+08:00", comments="Source field: order_item.created_time")
    public static final SqlColumn<LocalDateTime> createdTime = orderItemDAO.createdTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.921147+08:00", comments="Source field: order_item.updated_time")
    public static final SqlColumn<LocalDateTime> updatedTime = orderItemDAO.updatedTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.902308+08:00", comments="Source Table: order_item")
    public static final class OrderItemDAO extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.CHAR);

        public final SqlColumn<Integer> customerId = column("customer_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> orderId = column("order_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> quantity = column("quantity", JDBCType.INTEGER);

        public final SqlColumn<Integer> productId = column("product_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> snapshotProductId = column("snapshot_product_id", JDBCType.INTEGER);

        public final SqlColumn<BigDecimal> price = column("price", JDBCType.DECIMAL);

        public final SqlColumn<LocalDateTime> createdTime = column("created_time", JDBCType.TIMESTAMP);

        public final SqlColumn<LocalDateTime> updatedTime = column("updated_time", JDBCType.TIMESTAMP);

        public OrderItemDAO() {
            super("order_item");
        }
    }
}