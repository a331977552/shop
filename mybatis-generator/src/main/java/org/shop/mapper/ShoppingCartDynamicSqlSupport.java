package org.shop.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class ShoppingCartDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.135355+08:00", comments="Source Table: shopping_cart")
    public static final ShoppingCart shoppingCart = new ShoppingCart();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.138361+08:00", comments="Source field: shopping_cart.id")
    public static final SqlColumn<String> id = shoppingCart.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.139414+08:00", comments="Source field: shopping_cart.customer_id")
    public static final SqlColumn<Integer> customerId = shoppingCart.customerId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.14054+08:00", comments="Source field: shopping_cart.total_price")
    public static final SqlColumn<Long> totalPrice = shoppingCart.totalPrice;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.136813+08:00", comments="Source Table: shopping_cart")
    public static final class ShoppingCart extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.CHAR);

        public final SqlColumn<Integer> customerId = column("customer_id", JDBCType.INTEGER);

        public final SqlColumn<Long> totalPrice = column("total_price", JDBCType.DECIMAL);

        public ShoppingCart() {
            super("shopping_cart");
        }
    }
}