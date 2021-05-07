package org.shop;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
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

	public static Result<?> unknownError(String errorMsg) {
		return new Result<>(ResultCode.SERVER_ERROR, errorMsg);
	}

	public static <T> Result<T> validationError(String errorMsg) {
		return new Result<T>(ResultCode.BAD_REQUEST, errorMsg);
	}
	public static <T> Result<T> badRequest(String errorMsg) {
		return new Result<T>(ResultCode.BAD_REQUEST, errorMsg);
	}

	public static <T> Result<T> validationError(BindingResult result) {
		return new Result<T>(ResultCode.BAD_REQUEST, ErrorResultConvertor.getErrorMsg(result));
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
		return new Result<>(ResultCode.EMPTY, msgDetail);
	}
	public static <T> Result<T> notFound(String msgDetail) {
		return new Result<>(ResultCode.NOT_FOUND, msgDetail);
	}

	public static <T> Result<T> emptyList() {
		Result<T> objectResult = new Result<>(ResultCode.EMPTY, null);
		objectResult.setResult((T) new ArrayList());
		return objectResult;
	}

	public static<T> Result accessDenied(String message) {
		Result<T> objectResult = new Result<>(ResultCode.ACCESS_DENIED, message);
		return objectResult;
	}


	public enum ResultCode {
		INTERNAL_ERROR(500, "服务器异常，请稍微再试"),
		BAD_REQUEST(400, "请求异常"),
		SUCCESS(200, "成功"),
		EMPTY(204, "empty"),
		NOT_FOUND(404, "请求内容未找到"),
		ACCESS_DENIED(403, "没有权限"),
		SERVER_ERROR(599,"unknown error");

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
