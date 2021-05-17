package org.shop.model.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Data
@ToString
@NoArgsConstructor
public class CustomerVO implements UserDetails {

	private String id;
	@NotEmpty(message = "用户名不能为空",groups ={RegistryGroup.class,LoginGroup.class})
	@Length(min = 6,max = 20,message = "用户名长度必须在6到20个字符之间")
	private String username;
	@NotEmpty(message = "电子邮件不能为空",groups = RegistryGroup.class)
	@Length(min = 6,max = 30,message = "电子邮件长度必须在6到30个字符之间")
	@Email(regexp = "[a-zA-Z0-9_-]{2,15}@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)",message = "电子邮件格式不正确")
	private String email;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@NotNull(message = "生日不能为空",groups = RegistryGroup.class)
	private LocalDateTime dateOfBirth;
	@NotBlank(message = "密码不能为空",groups = {RegistryGroup.class,LoginGroup.class})
	@Length(min = 6,max = 64,message = "密码长度须大于6",groups = {RegistryGroup.class,LoginGroup.class})
	private String password;
	private String alias;
	private Integer avatar;

	@NotBlank(message = "电话号码不能为空",groups = {RegistryGroup.class})
	@Length(min = 11,max = 20,message = "手机长度不正确",groups = {RegistryGroup.class})
	private String phone;
	@JsonIgnore
	private String role;
	@JsonIgnore
	private LocalDateTime createdTime;
	@JsonIgnore
	private LocalDateTime updatedTime;

	public CustomerVO(String username,String password) {
		this.username = username;
		this.password = password;
	}

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<>();
	}


	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}


	public interface RegistryGroup{

	}
	public interface LoginGroup{

	}
}
