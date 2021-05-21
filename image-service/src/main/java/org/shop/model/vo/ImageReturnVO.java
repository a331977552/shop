package org.shop.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ImageReturnVO {
	private String id;
	private Thumb thumb;

	private String description;
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Thumb{
		private String id;

	}

}
