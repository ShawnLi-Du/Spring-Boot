package com.du.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "register")
public class RegisterBean {

	@Id
	@Column(name = "NO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer no;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PASSWORD")
	private String password;

	public RegisterBean() {
		super();
	}

	public RegisterBean(Integer no, String email, String password) {
		super();
		this.no = no;
		this.email = email;
		this.password = password;
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Beans [no=" + no + ", email=" + email + ", password=" + password + "]";
	}

}
