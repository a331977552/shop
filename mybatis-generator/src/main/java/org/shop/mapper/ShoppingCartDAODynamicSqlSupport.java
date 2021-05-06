package org.shop.mapper;

import java.math.BigDecimal;
import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class ShoppingCartDAODynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.968421+08:00", comments="Source Table: shopping_cart")
    public static final ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAO();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.969223+08:00", comments="Source field: shopping_cart.id")
    public static final SqlColumn<String> id = shoppingCartDAO.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.970171+08:00", comments="Source field: shopping_cart.customer_id")
    public static final SqlColumn<Integer> customerId = shoppingCartDAO.customerId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.971077+08:00", comments="Source field: shopping_cart.total_price")
    public static final SqlColumn<BigDecimal> totalPrice = shoppingCartDAO.totalPrice;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.968804+08:00", comments="Source Table: shopping_cart")
    public static final class ShoppingCartDAO extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.CHAR);

        public final SqlColumn<Integer> customerId = column("customer_id", JDBCType.INTEGER);

        public final SqlColumn<BigDecimal> totalPrice = column("total_price", JDBCType.DECIMAL);

        public ShoppingCartDAO() {
            super("shopping_cart");
        }
    }
}