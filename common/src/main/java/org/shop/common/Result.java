package org.shop.common;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.shop.common.util.ErrorResultConvertor;
import org.springframework.validation.BindingResult;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@ToString
public final class Result<T>{
	private Date timestamp;
	private int code;
	private String msg;
	private String msgDetail;
	private T result;
//	private String path;
	public Result(int code, String msgDetail) {
		this.code = code;
		this.msg = ResultCode.getErrorMsg(code);
		this.msgDetail = msgDetail;
		timestamp = Date.from(Instant.now());
	}

	public static Result unknownError(String errorMsg) {
		return new Result(599, errorMsg);
	}

	public static <T> Result<T> validationError(String errorMsg) {
		return new Result<T>(400, errorMsg);
	}
	public static <T> Result<T> badRequest(String errorMsg) {
		return new Result<T>(400, errorMsg);
	}

	public static <T> Result<T> validationError(BindingResult result) {
		return new Result<T>(400, ErrorResultConvertor.getErrorMsg(result));
	}

	public static <T> Result<T> of(T t) {
		if (t instanceof List) {
			List list = (List) t;
			if (list.isEmpty())
				return Result.emptyList();
		} else if (t == null) {
			return Result.empty(null);
		}
		Result<T> objectResult = new Result<T>(200, null);
		objectResult.setResult(t);
		return objectResult;
	}

	public static Result<?> internalError(String errorMsg) {
		return new Result(500, errorMsg);
	}

	public static <T> Result<T> success(T t) {
		Result<T> objectResult = new Result(200, null);
		objectResult.setResult(t);
		return objectResult;
	}

	public static <T> Result<T> empty(String msgDetail) {
		return new Result(204, msgDetail);
	}
	public static <T> Result<T> notFound(String msgDetail) {
		return new Result(404, msgDetail);
	}

	public static <T> Result<T> emptyList() {
		Result<T> objectResult = new Result(204, null);
		objectResult.setResult((T) new ArrayList());
		return objectResult;
	}

	public static<T> Result accessDenied(String message) {
		Result<T> objectResult = new Result(403, message);
		return objectResult;
	}


	public enum ResultCode {
		INTERNAL_ERROR(500, "服务器异常，请稍微再试"),
		BAD_REQUEST(400, "请求异常"),
		SUCCESS(200, "成功"),
		EMPTY(204, "empty"),
		NOT_FOUND(404, "请求内容未找到"),
		ACCESS_DENIED(403, "没有权限"),
		AUTHENTICATION_FAILED(401, "账户验证失败"),
		SERVER_ERROR(599,"unknown error");

		private int code;
		private String msg;

		ResultCode(int code, String msg) {
			this.code = code;
			this.msg = msg;
		}
		public static String getErrorMsg(int code){
	      return Arrays.stream(ResultCode.values()).
			 filter(resultCode -> resultCode.code == code).
			 findFirst().orElse(ResultCode.SERVER_ERROR).msg;
		}


		public int getCode() {
			return code;
		}

		public String getMsg() {
			return msg;
		}
	}

}
