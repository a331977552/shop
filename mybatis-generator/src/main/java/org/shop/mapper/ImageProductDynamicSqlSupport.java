package org.shop.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class ImageProductDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.037148+08:00", comments="Source Table: image_product")
    public static final ImageProduct imageProduct = new ImageProduct();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.039671+08:00", comments="Source field: image_product.id")
    public static final SqlColumn<String> id = imageProduct.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.040265+08:00", comments="Source field: image_product.customer_id")
    public static final SqlColumn<String> customerId = imageProduct.customerId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.040618+08:00", comments="Source field: image_product.img_id")
    public static final SqlColumn<String> imgId = imageProduct.imgId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.038605+08:00", comments="Source Table: image_product")
    public static final class ImageProduct extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.CHAR);

        public final SqlColumn<String> customerId = column("customer_id", JDBCType.CHAR);

        public final SqlColumn<String> imgId = column("img_id", JDBCType.CHAR);

        public ImageProduct() {
            super("image_product");
        }
    }
}