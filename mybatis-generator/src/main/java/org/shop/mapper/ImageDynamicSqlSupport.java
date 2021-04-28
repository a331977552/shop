package org.shop.mapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class ImageDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.02615+08:00", comments="Source Table: image")
    public static final Image image = new Image();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.029065+08:00", comments="Source field: image.id")
    public static final SqlColumn<String> id = image.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.030135+08:00", comments="Source field: image.path")
    public static final SqlColumn<String> path = image.path;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.030406+08:00", comments="Source field: image.description")
    public static final SqlColumn<String> description = image.description;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.030596+08:00", comments="Source field: image.created_time")
    public static final SqlColumn<Date> createdTime = image.createdTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.031086+08:00", comments="Source field: image.updated_time")
    public static final SqlColumn<Date> updatedTime = image.updatedTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.02683+08:00", comments="Source Table: image")
    public static final class Image extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.CHAR);

        public final SqlColumn<String> path = column("path", JDBCType.VARCHAR);

        public final SqlColumn<String> description = column("description", JDBCType.VARCHAR);

        public final SqlColumn<Date> createdTime = column("created_time", JDBCType.DATE);

        public final SqlColumn<Date> updatedTime = column("updated_time", JDBCType.DATE);

        public Image() {
            super("image");
        }
    }
}