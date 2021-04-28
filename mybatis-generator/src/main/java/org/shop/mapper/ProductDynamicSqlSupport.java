package org.shop.mapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class ProductDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.075531+08:00", comments="Source Table: product")
    public static final Product product = new Product();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.077384+08:00", comments="Source field: product.id")
    public static final SqlColumn<String> id = product.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.078872+08:00", comments="Source field: product.name")
    public static final SqlColumn<String> name = product.name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.079857+08:00", comments="Source field: product.price")
    public static final SqlColumn<Long> price = product.price;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.08089+08:00", comments="Source field: product.category")
    public static final SqlColumn<Integer> category = product.category;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.082268+08:00", comments="Source field: product.quantity")
    public static final SqlColumn<Integer> quantity = product.quantity;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.08376+08:00", comments="Source field: product.thumbnail_img")
    public static final SqlColumn<Integer> thumbnailImg = product.thumbnailImg;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.085912+08:00", comments="Source field: product.standard_img")
    public static final SqlColumn<Integer> standardImg = product.standardImg;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.087112+08:00", comments="Source field: product.created_time")
    public static final SqlColumn<Date> createdTime = product.createdTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.089228+08:00", comments="Source field: product.updated_time")
    public static final SqlColumn<Date> updatedTime = product.updatedTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.076283+08:00", comments="Source Table: product")
    public static final class Product extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.CHAR);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Long> price = column("price", JDBCType.DECIMAL);

        public final SqlColumn<Integer> category = column("category", JDBCType.INTEGER);

        public final SqlColumn<Integer> quantity = column("quantity", JDBCType.INTEGER);

        public final SqlColumn<Integer> thumbnailImg = column("thumbnail_img", JDBCType.INTEGER);

        public final SqlColumn<Integer> standardImg = column("standard_img", JDBCType.INTEGER);

        public final SqlColumn<Date> createdTime = column("created_time", JDBCType.DATE);

        public final SqlColumn<Date> updatedTime = column("updated_time", JDBCType.DATE);

        public Product() {
            super("product");
        }
    }
}