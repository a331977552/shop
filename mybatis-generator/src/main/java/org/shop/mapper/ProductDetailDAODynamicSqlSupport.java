package org.shop.mapper;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class ProductDetailDAODynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.947666+08:00", comments="Source Table: product_detail")
    public static final ProductDetailDAO productDetailDAO = new ProductDetailDAO();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.950124+08:00", comments="Source field: product_detail.id")
    public static final SqlColumn<String> id = productDetailDAO.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.951129+08:00", comments="Source field: product_detail.product_id")
    public static final SqlColumn<Integer> productId = productDetailDAO.productId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.951977+08:00", comments="Source field: product_detail.created_time")
    public static final SqlColumn<LocalDateTime> createdTime = productDetailDAO.createdTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.95282+08:00", comments="Source field: product_detail.updated_time")
    public static final SqlColumn<LocalDateTime> updatedTime = productDetailDAO.updatedTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.953632+08:00", comments="Source field: product_detail.description")
    public static final SqlColumn<String> description = productDetailDAO.description;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.949374+08:00", comments="Source Table: product_detail")
    public static final class ProductDetailDAO extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.CHAR);

        public final SqlColumn<Integer> productId = column("product_id", JDBCType.INTEGER);

        public final SqlColumn<LocalDateTime> createdTime = column("created_time", JDBCType.TIMESTAMP);

        public final SqlColumn<LocalDateTime> updatedTime = column("updated_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> description = column("description", JDBCType.LONGVARCHAR);

        public ProductDetailDAO() {
            super("product_detail");
        }
    }
}