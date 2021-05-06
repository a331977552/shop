package org.shop.model.dao;

import java.time.LocalDateTime;
import javax.annotation.Generated;

public class ImageDAO {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.875123+08:00", comments="Source field: image.id")
    private String id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.875945+08:00", comments="Source field: image.path")
    private String path;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.876033+08:00", comments="Source field: image.description")
    private String description;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.876085+08:00", comments="Source field: image.created_time")
    private LocalDateTime createdTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.876147+08:00", comments="Source field: image.updated_time")
    private LocalDateTime updatedTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.875734+08:00", comments="Source field: image.id")
    public String getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.875916+08:00", comments="Source field: image.id")
    public void setId(String id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.875971+08:00", comments="Source field: image.path")
    public String getPath() {
        return path;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.876012+08:00", comments="Source field: image.path")
    public void setPath(String path) {
        this.path = path;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.876052+08:00", comments="Source field: image.description")
    public String getDescription() {
        return description;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.87607+08:00", comments="Source field: image.description")
    public void setDescription(String description) {
        this.description = description;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.876104+08:00", comments="Source field: image.created_time")
    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.876124+08:00", comments="Source field: image.created_time")
    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.876175+08:00", comments="Source field: image.updated_time")
    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-05-06T13:49:21.876193+08:00", comments="Source field: image.updated_time")
    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }
}