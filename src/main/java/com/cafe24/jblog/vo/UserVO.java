package com.cafe24.jblog.vo;

import java.sql.Date;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

public class UserVO {
	
	private String id;
	private String name;
	private String pw;
	private Date regDate;
	private String logo;
	
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "UserVO [id=" + id + ", name=" + name + ", pw=" + pw + ", regDate=" + regDate + ", logo=" + logo + "]";
	}
	
}
