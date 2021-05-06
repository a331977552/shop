package org.shop.mapper;

import java.math.BigDecimal;
import java.sql.JDBCType;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class CartItemDAODynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.794692+08:00", comments="Source Table: cart_item")
    public static final CartItemDAO cartItemDAO = new CartItemDAO();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.798261+08:00", comments="Source field: cart_item.id")
    public static final SqlColumn<String> id = cartItemDAO.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.800825+08:00", comments="Source field: cart_item.customer_id")
    public static final SqlColumn<Integer> customerId = cartItemDAO.customerId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.801019+08:00", comments="Source field: cart_item.product_id")
    public static final SqlColumn<Integer> productId = cartItemDAO.productId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.801193+08:00", comments="Source field: cart_item.cart_id")
    public static final SqlColumn<Integer> cartId = cartItemDAO.cartId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.801323+08:00", comments="Source field: cart_item.quantity")
    public static final SqlColumn<Integer> quantity = cartItemDAO.quantity;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.801533+08:00", comments="Source field: cart_item.total_price")
    public static final SqlColumn<BigDecimal> totalPrice = cartItemDAO.totalPrice;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.803106+08:00", comments="Source field: cart_item.created_time")
    public static final SqlColumn<LocalDateTime> createdTime = cartItemDAO.createdTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.80331+08:00", comments="Source field: cart_item.updated_time")
    public static final SqlColumn<LocalDateTime> updatedTime = cartItemDAO.updatedTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.797108+08:00", comments="Source Table: cart_item")
    public static final class CartItemDAO extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.CHAR);

        public final SqlColumn<Integer> customerId = column("customer_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> productId = column("product_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> cartId = column("cart_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> quantity = column("quantity", JDBCType.INTEGER);

        public final SqlColumn<BigDecimal> totalPrice = column("total_price", JDBCType.DECIMAL);

        public final SqlColumn<LocalDateTime> createdTime = column("created_time", JDBCType.TIMESTAMP);

        public final SqlColumn<LocalDateTime> updatedTime = column("updated_time", JDBCType.TIMESTAMP);

        public CartItemDAO() {
            super("cart_item");
        }
    }
}