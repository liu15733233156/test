package com.wenpu.user.entity;

import java.io.Serializable;

/**
 * 用户信息
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 主键ID */
	private Long id;

	/** 年龄 */
	private Integer age;

	/** 姓名 */
	private String name;

	/** 生日 */
	private java.util.Date birthday;
	
	private String password;


	public void setId(Long id) {
		this.id = id;
	}


	public Long getId() {
		return this.id;
	}


	public void setAge(Integer age) {
		this.age = age;
	}


	public Integer getAge() {
		return this.age;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getName() {
		return this.name;
	}


	public void setBirthday(java.util.Date birthday) {
		this.birthday = birthday;
	}


	public java.util.Date getBirthday() {
		return this.birthday;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
