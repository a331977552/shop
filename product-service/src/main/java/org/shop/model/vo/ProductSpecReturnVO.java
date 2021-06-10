package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductSpecReturnVO {
    private int id;

    private String name;

    private String selectType; // single, multiple

    private String entryMethod; // custom, selection

    private Integer categoryId;

    private Integer sort = 0;

    private Boolean searchable;

    private String value;


}