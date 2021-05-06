package org.shop.mapper;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class ImageDAODynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.8786+08:00", comments="Source Table: image")
    public static final ImageDAO imageDAO = new ImageDAO();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.880977+08:00", comments="Source field: image.id")
    public static final SqlColumn<String> id = imageDAO.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.881356+08:00", comments="Source field: image.path")
    public static final SqlColumn<String> path = imageDAO.path;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.881644+08:00", comments="Source field: image.description")
    public static final SqlColumn<String> description = imageDAO.description;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.88174+08:00", comments="Source field: image.created_time")
    public static final SqlColumn<LocalDateTime> createdTime = imageDAO.createdTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.88212+08:00", comments="Source field: image.updated_time")
    public static final SqlColumn<LocalDateTime> updatedTime = imageDAO.updatedTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.87992+08:00", comments="Source Table: image")
    public static final class ImageDAO extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.CHAR);

        public final SqlColumn<String> path = column("path", JDBCType.VARCHAR);

        public final SqlColumn<String> description = column("description", JDBCType.VARCHAR);

        public final SqlColumn<LocalDateTime> createdTime = column("created_time", JDBCType.TIMESTAMP);

        public final SqlColumn<LocalDateTime> updatedTime = column("updated_time", JDBCType.TIMESTAMP);

        public ImageDAO() {
            super("image");
        }
    }
}