package org.shop.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static org.shop.mapper.ImageProductDynamicSqlSupport.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;
import org.shop.model.ImageProduct;

@Mapper
public interface ImageProductMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.04267+08:00", comments="Source Table: image_product")
    BasicColumn[] selectList = BasicColumn.columnList(id, customerId, imgId);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.040767+08:00", comments="Source Table: image_product")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.04082+08:00", comments="Source Table: image_product")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.040853+08:00", comments="Source Table: image_product")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<ImageProduct> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.040896+08:00", comments="Source Table: image_product")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<ImageProduct> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.040953+08:00", comments="Source Table: image_product")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("ImageProductResult")
    Optional<ImageProduct> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.041028+08:00", comments="Source Table: image_product")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="ImageProductResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="customer_id", property="customerId", jdbcType=JdbcType.CHAR),
        @Result(column="img_id", property="imgId", jdbcType=JdbcType.CHAR)
    })
    List<ImageProduct> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.041147+08:00", comments="Source Table: image_product")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.041186+08:00", comments="Source Table: image_product")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, imageProduct, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.041277+08:00", comments="Source Table: image_product")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, imageProduct, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.041591+08:00", comments="Source Table: image_product")
    default int deleteByPrimaryKey(String id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.042267+08:00", comments="Source Table: image_product")
    default int insert(ImageProduct record) {
        return MyBatis3Utils.insert(this::insert, record, imageProduct, c ->
            c.map(id).toProperty("id")
            .map(customerId).toProperty("customerId")
            .map(imgId).toProperty("imgId")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.042488+08:00", comments="Source Table: image_product")
    default int insertMultiple(Collection<ImageProduct> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, imageProduct, c ->
            c.map(id).toProperty("id")
            .map(customerId).toProperty("customerId")
            .map(imgId).toProperty("imgId")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.042552+08:00", comments="Source Table: image_product")
    default int insertSelective(ImageProduct record) {
        return MyBatis3Utils.insert(this::insert, record, imageProduct, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(customerId).toPropertyWhenPresent("customerId", record::getCustomerId)
            .map(imgId).toPropertyWhenPresent("imgId", record::getImgId)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.042709+08:00", comments="Source Table: image_product")
    default Optional<ImageProduct> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, imageProduct, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.042749+08:00", comments="Source Table: image_product")
    default List<ImageProduct> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, imageProduct, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.042785+08:00", comments="Source Table: image_product")
    default List<ImageProduct> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, imageProduct, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.042822+08:00", comments="Source Table: image_product")
    default Optional<ImageProduct> selectByPrimaryKey(String id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.042858+08:00", comments="Source Table: image_product")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, imageProduct, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.043007+08:00", comments="Source Table: image_product")
    static UpdateDSL<UpdateModel> updateAllColumns(ImageProduct record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(customerId).equalTo(record::getCustomerId)
                .set(imgId).equalTo(record::getImgId);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.044577+08:00", comments="Source Table: image_product")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(ImageProduct record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(customerId).equalToWhenPresent(record::getCustomerId)
                .set(imgId).equalToWhenPresent(record::getImgId);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.044901+08:00", comments="Source Table: image_product")
    default int updateByPrimaryKey(ImageProduct record) {
        return update(c ->
            c.set(customerId).equalTo(record::getCustomerId)
            .set(imgId).equalTo(record::getImgId)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-27T21:17:43.045114+08:00", comments="Source Table: image_product")
    default int updateByPrimaryKeySelective(ImageProduct record) {
        return update(c ->
            c.set(customerId).equalToWhenPresent(record::getCustomerId)
            .set(imgId).equalToWhenPresent(record::getImgId)
            .where(id, isEqualTo(record::getId))
        );
    }
}