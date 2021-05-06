package org.shop.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class ImageProductDAODynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.886211+08:00", comments="Source Table: image_product")
    public static final ImageProductDAO imageProductDAO = new ImageProductDAO();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.887624+08:00", comments="Source field: image_product.id")
    public static final SqlColumn<String> id = imageProductDAO.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.88794+08:00", comments="Source field: image_product.product_id")
    public static final SqlColumn<String> productId = imageProductDAO.productId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.888022+08:00", comments="Source field: image_product.img_id")
    public static final SqlColumn<String> imgId = imageProductDAO.imgId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.886473+08:00", comments="Source Table: image_product")
    public static final class ImageProductDAO extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.CHAR);

        public final SqlColumn<String> productId = column("product_id", JDBCType.CHAR);

        public final SqlColumn<String> imgId = column("img_id", JDBCType.CHAR);

        public ImageProductDAO() {
            super("image_product");
        }
    }
}