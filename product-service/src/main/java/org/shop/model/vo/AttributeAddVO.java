package org.shop.model.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class AttributeAddVO {

	private String name;//color, size

	private String categoryid;

	private List<AttributeValueAddVO> valueVOList;//blue, red,  small, large




}
