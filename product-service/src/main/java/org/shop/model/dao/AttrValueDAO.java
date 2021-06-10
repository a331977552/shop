package org.shop.model.dao;

import java.time.LocalDateTime;

public class AttrValueDAO {
    private Integer id;

    private Integer attrKey;

    private String value;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAttrKey() {
        return attrKey;
    }

    public void setAttrKey(Integer attrKey) {
        this.attrKey = attrKey;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }
}