package org.shop;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@ToString
public final class Result<T>{

	private Date timestamp;
	private int code;
	private String msg;
	private String msgDetail;
	private T result;
}