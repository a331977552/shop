package org.shop.tests.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
public class ImageReturnVO {
	private String id;
	private Thumb thumb;

	private String description;
	@Data
	@ToString
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Thumb{
		private String id;

	}

}
