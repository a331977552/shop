package org.shop.mapper;

import java.math.BigDecimal;
import java.sql.JDBCType;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class ProductDAODynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.927916+08:00", comments="Source Table: product")
    public static final ProductDAO productDAO = new ProductDAO();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.92935+08:00", comments="Source field: product.id")
    public static final SqlColumn<String> id = productDAO.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.930544+08:00", comments="Source field: product.name")
    public static final SqlColumn<String> name = productDAO.name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.93162+08:00", comments="Source field: product.price")
    public static final SqlColumn<BigDecimal> price = productDAO.price;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.932718+08:00", comments="Source field: product.category")
    public static final SqlColumn<Integer> category = productDAO.category;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.933597+08:00", comments="Source field: product.quantity")
    public static final SqlColumn<Integer> quantity = productDAO.quantity;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.935115+08:00", comments="Source field: product.thumbnail_img")
    public static final SqlColumn<Integer> thumbnailImg = productDAO.thumbnailImg;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.936343+08:00", comments="Source field: product.standard_img")
    public static final SqlColumn<Integer> standardImg = productDAO.standardImg;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.938618+08:00", comments="Source field: product.created_time")
    public static final SqlColumn<LocalDateTime> createdTime = productDAO.createdTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.939639+08:00", comments="Source field: product.updated_time")
    public static final SqlColumn<LocalDateTime> updatedTime = productDAO.updatedTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.92874+08:00", comments="Source Table: product")
    public static final class ProductDAO extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.CHAR);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<BigDecimal> price = column("price", JDBCType.DECIMAL);

        public final SqlColumn<Integer> category = column("category", JDBCType.INTEGER);

        public final SqlColumn<Integer> quantity = column("quantity", JDBCType.INTEGER);

        public final SqlColumn<Integer> thumbnailImg = column("thumbnail_img", JDBCType.INTEGER);

        public final SqlColumn<Integer> standardImg = column("standard_img", JDBCType.INTEGER);

        public final SqlColumn<LocalDateTime> createdTime = column("created_time", JDBCType.TIMESTAMP);

        public final SqlColumn<LocalDateTime> updatedTime = column("updated_time", JDBCType.TIMESTAMP);

        public ProductDAO() {
            super("product");
        }
    }
}