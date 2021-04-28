package org.shop.mapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class SnapshotProductDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.146545+08:00", comments="Source Table: snapshot_product")
    public static final SnapshotProduct snapshotProduct = new SnapshotProduct();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.149321+08:00", comments="Source field: snapshot_product.id")
    public static final SqlColumn<String> id = snapshotProduct.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.150559+08:00", comments="Source field: snapshot_product.product_id")
    public static final SqlColumn<Integer> productId = snapshotProduct.productId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.153067+08:00", comments="Source field: snapshot_product.name")
    public static final SqlColumn<String> name = snapshotProduct.name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.156252+08:00", comments="Source field: snapshot_product.price")
    public static final SqlColumn<Long> price = snapshotProduct.price;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.157409+08:00", comments="Source field: snapshot_product.category")
    public static final SqlColumn<Integer> category = snapshotProduct.category;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.158233+08:00", comments="Source field: snapshot_product.thumbnail_img")
    public static final SqlColumn<Integer> thumbnailImg = snapshotProduct.thumbnailImg;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.159101+08:00", comments="Source field: snapshot_product.standard_img")
    public static final SqlColumn<Integer> standardImg = snapshotProduct.standardImg;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.160629+08:00", comments="Source field: snapshot_product.created_time")
    public static final SqlColumn<Date> createdTime = snapshotProduct.createdTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.162956+08:00", comments="Source field: snapshot_product.updated_time")
    public static final SqlColumn<Date> updatedTime = snapshotProduct.updatedTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.164131+08:00", comments="Source field: snapshot_product.description")
    public static final SqlColumn<String> description = snapshotProduct.description;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.148088+08:00", comments="Source Table: snapshot_product")
    public static final class SnapshotProduct extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.CHAR);

        public final SqlColumn<Integer> productId = column("product_id", JDBCType.INTEGER);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Long> price = column("price", JDBCType.DECIMAL);

        public final SqlColumn<Integer> category = column("category", JDBCType.INTEGER);

        public final SqlColumn<Integer> thumbnailImg = column("thumbnail_img", JDBCType.INTEGER);

        public final SqlColumn<Integer> standardImg = column("standard_img", JDBCType.INTEGER);

        public final SqlColumn<Date> createdTime = column("created_time", JDBCType.DATE);

        public final SqlColumn<Date> updatedTime = column("updated_time", JDBCType.DATE);

        public final SqlColumn<String> description = column("description", JDBCType.LONGVARCHAR);

        public SnapshotProduct() {
            super("snapshot_product");
        }
    }
}