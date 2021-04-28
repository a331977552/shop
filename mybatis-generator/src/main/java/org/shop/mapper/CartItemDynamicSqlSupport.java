package org.shop.mapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class CartItemDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.933231+08:00", comments="Source Table: cart_item")
    public static final CartItem cartItem = new CartItem();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.936802+08:00", comments="Source field: cart_item.id")
    public static final SqlColumn<String> id = cartItem.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.939094+08:00", comments="Source field: cart_item.customer_id")
    public static final SqlColumn<Integer> customerId = cartItem.customerId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.940619+08:00", comments="Source field: cart_item.product_id")
    public static final SqlColumn<Integer> productId = cartItem.productId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.940791+08:00", comments="Source field: cart_item.cart_id")
    public static final SqlColumn<Integer> cartId = cartItem.cartId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.94093+08:00", comments="Source field: cart_item.quantity")
    public static final SqlColumn<Integer> quantity = cartItem.quantity;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.941067+08:00", comments="Source field: cart_item.total_price")
    public static final SqlColumn<Long> totalPrice = cartItem.totalPrice;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.941247+08:00", comments="Source field: cart_item.created_time")
    public static final SqlColumn<Date> createdTime = cartItem.createdTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.941389+08:00", comments="Source field: cart_item.updated_time")
    public static final SqlColumn<Date> updatedTime = cartItem.updatedTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:42.936187+08:00", comments="Source Table: cart_item")
    public static final class CartItem extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.CHAR);

        public final SqlColumn<Integer> customerId = column("customer_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> productId = column("product_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> cartId = column("cart_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> quantity = column("quantity", JDBCType.INTEGER);

        public final SqlColumn<Long> totalPrice = column("total_price", JDBCType.DECIMAL);

        public final SqlColumn<Date> createdTime = column("created_time", JDBCType.DATE);

        public final SqlColumn<Date> updatedTime = column("updated_time", JDBCType.DATE);

        public CartItem() {
            super("cart_item");
        }
    }
}