package org.shop.model.dao;

import java.time.LocalDateTime;

public class BrandDAO {
    private Integer id;

    private String name;

    private String registrationNum;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    private String capitalLetter;

    private Integer priority;

    private Boolean isManufacturer;

    private Boolean visible;

    private String info;

    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistrationNum() {
        return registrationNum;
    }

    public void setRegistrationNum(String registrationNum) {
        this.registrationNum = registrationNum;
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

    public String getCapitalLetter() {
        return capitalLetter;
    }

    public void setCapitalLetter(String capitalLetter) {
        this.capitalLetter = capitalLetter;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Boolean getIsManufacturer() {
        return isManufacturer;
    }

    public void setIsManufacturer(Boolean isManufacturer) {
        this.isManufacturer = isManufacturer;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}