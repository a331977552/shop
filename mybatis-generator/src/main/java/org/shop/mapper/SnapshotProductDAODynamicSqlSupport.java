package org.shop.mapper;

import java.math.BigDecimal;
import java.sql.JDBCType;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class SnapshotProductDAODynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.978185+08:00", comments="Source Table: snapshot_product")
    public static final SnapshotProductDAO snapshotProductDAO = new SnapshotProductDAO();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.980178+08:00", comments="Source field: snapshot_product.id")
    public static final SqlColumn<String> id = snapshotProductDAO.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.981366+08:00", comments="Source field: snapshot_product.product_id")
    public static final SqlColumn<Integer> productId = snapshotProductDAO.productId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.982192+08:00", comments="Source field: snapshot_product.name")
    public static final SqlColumn<String> name = snapshotProductDAO.name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.983052+08:00", comments="Source field: snapshot_product.price")
    public static final SqlColumn<BigDecimal> price = snapshotProductDAO.price;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.984329+08:00", comments="Source field: snapshot_product.category")
    public static final SqlColumn<Integer> category = snapshotProductDAO.category;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.985427+08:00", comments="Source field: snapshot_product.thumbnail_img")
    public static final SqlColumn<Integer> thumbnailImg = snapshotProductDAO.thumbnailImg;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.986511+08:00", comments="Source field: snapshot_product.standard_img")
    public static final SqlColumn<Integer> standardImg = snapshotProductDAO.standardImg;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.988905+08:00", comments="Source field: snapshot_product.created_time")
    public static final SqlColumn<LocalDateTime> createdTime = snapshotProductDAO.createdTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.989722+08:00", comments="Source field: snapshot_product.updated_time")
    public static final SqlColumn<LocalDateTime> updatedTime = snapshotProductDAO.updatedTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.990413+08:00", comments="Source field: snapshot_product.description")
    public static final SqlColumn<String> description = snapshotProductDAO.description;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.979408+08:00", comments="Source Table: snapshot_product")
    public static final class SnapshotProductDAO extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.CHAR);

        public final SqlColumn<Integer> productId = column("product_id", JDBCType.INTEGER);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<BigDecimal> price = column("price", JDBCType.DECIMAL);

        public final SqlColumn<Integer> category = column("category", JDBCType.INTEGER);

        public final SqlColumn<Integer> thumbnailImg = column("thumbnail_img", JDBCType.INTEGER);

        public final SqlColumn<Integer> standardImg = column("standard_img", JDBCType.INTEGER);

        public final SqlColumn<LocalDateTime> createdTime = column("created_time", JDBCType.TIMESTAMP);

        public final SqlColumn<LocalDateTime> updatedTime = column("updated_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> description = column("description", JDBCType.LONGVARCHAR);

        public SnapshotProductDAO() {
            super("snapshot_product");
        }
    }
}