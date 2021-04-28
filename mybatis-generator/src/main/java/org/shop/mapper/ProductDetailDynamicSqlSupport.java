package org.shop.mapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class ProductDetailDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.101956+08:00", comments="Source Table: product_detail")
    public static final ProductDetail productDetail = new ProductDetail();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.105547+08:00", comments="Source field: product_detail.id")
    public static final SqlColumn<String> id = productDetail.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.107272+08:00", comments="Source field: product_detail.product_id")
    public static final SqlColumn<Integer> productId = productDetail.productId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.109357+08:00", comments="Source field: product_detail.created_time")
    public static final SqlColumn<Date> createdTime = productDetail.createdTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.111675+08:00", comments="Source field: product_detail.updated_time")
    public static final SqlColumn<Date> updatedTime = productDetail.updatedTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.112899+08:00", comments="Source field: product_detail.description")
    public static final SqlColumn<String> description = productDetail.description;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.102966+08:00", comments="Source Table: product_detail")
    public static final class ProductDetail extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.CHAR);

        public final SqlColumn<Integer> productId = column("product_id", JDBCType.INTEGER);

        public final SqlColumn<Date> createdTime = column("created_time", JDBCType.DATE);

        public final SqlColumn<Date> updatedTime = column("updated_time", JDBCType.DATE);

        public final SqlColumn<String> description = column("description", JDBCType.LONGVARCHAR);

        public ProductDetail() {
            super("product_detail");
        }
    }
}