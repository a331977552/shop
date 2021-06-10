package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ToString
public class ProductSpecAddVO {

    @NotEmpty(message = "product spec name cannot be empty")
    private String name;

    @NotEmpty(message = "product spec selectType cannot be empty")
    private String selectType; // single, multiple

    @NotEmpty(message = "product spec entryMethod cannot be empty")
    private String entryMethod; // custom, selection
    @NotNull(message = "product spec categoryId cannot be empty")
    private Integer categoryId;


    private Integer sort = 0;

    private Boolean searchable;

    private String value;



}