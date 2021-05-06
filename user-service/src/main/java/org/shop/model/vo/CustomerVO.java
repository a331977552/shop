package org.shop.model.vo;


import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@ToString
public class CustomerVO {

	private String id;
	@NotEmpty(message = "用户名不能为空")
	@Length(min = 6,max = 20,message = "用户名长度必须在6到20个字符之间")
	private String username;
	@NotEmpty(message = "电子邮件不能为空")
	@Length(min = 6,max = 30,message = "电子邮件长度必须在6到30个字符之间")
	@Email(regexp = "[a-zA-Z0-9_-]{2,15}@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)",message = "电子邮件格式不正确")
	private String email;
	@NotNull(message = "生日不能为空")
	private LocalDateTime dateOfBirth;
	private String password;
	private String alias;
	private Integer avatar;

	private String phone;
	private String role;
	private LocalDateTime createdTime;
	private LocalDateTime updatedTime;
}
