package org.shop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@ToString
public final class Result<T> {

	private int code;
	private String msg;
	private String msgDetail;
	private T result;

	public Result(ResultCode resultCode, String msgDetail) {
		this.code = resultCode.getCode();
		this.msg = resultCode.getMsg();
		this.msgDetail = msgDetail;
	}

	public static Result<?> error(String errorMsg) {
		return new Result<>(ResultCode.REQUEST_ERROR, errorMsg);
	}

	public static <T> Result<T> validationError(String errorMsg) {
		return new Result<T>(ResultCode.REQUEST_ERROR, errorMsg);
	}

	public static <T> Result<T> validationError(BindingResult result) {
		return new Result<T>(ResultCode.REQUEST_ERROR, ErrorResultConvertor.getErrorMsg(result));
	}

	public static <T> Result<T> of(T t) {
		if (t instanceof List) {
			List list = (List) t;
			if (list.isEmpty())
				return Result.emptyList();
		} else if (t == null) {
			return Result.empty(null);
		}
		Result<T> objectResult = new Result<T>(ResultCode.SUCCESS, null);
		objectResult.setResult(t);
		return objectResult;
	}

	public static Result<?> internalError(String errorMsg) {
		return new Result<>(ResultCode.INTERNAL_ERROR, errorMsg);
	}

	public static <T> Result<T> success(T t) {
		Result<T> objectResult = new Result<>(ResultCode.SUCCESS, null);
		objectResult.setResult(t);
		return objectResult;
	}

	public static <T> Result<T> empty(String msgDetail) {
		Result<T> objectResult = new Result<>(ResultCode.NOT_FOUND, msgDetail);
		return objectResult;
	}

	public static <T> Result<T> emptyList() {
		Result<T> objectResult = new Result<>(ResultCode.NOT_FOUND, null);
		objectResult.setResult((T) new ArrayList());
		return objectResult;
	}


	public enum ResultCode {
		INTERNAL_ERROR(500, "服务器异常，请稍微再试"),
		REQUEST_ERROR(400, "请求异常"),
		SUCCESS(200, "成功"),
		NOT_FOUND(404, "请求内容未找到");
		private int code;
		private String msg;

		ResultCode(int code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		public int getCode() {
			return code;
		}

		public String getMsg() {
			return msg;
		}
	}

}
