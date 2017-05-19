package com.wenpu.user.spring.resolver;


/**
 * 登录信息
 */
public class LoginInfo {

	private long userId;

	private String userName;


	public LoginInfo() {
		//spring 注入使用
	}


	public LoginInfo( long userId, String userName ) {
		this.userId = userId;
		this.userName = userName;
	}


	public void init() {
		System.out.println(" spring 注入测试，嘎嘎！！！启动...");
	}


	public long getUserId() {
		return userId;
	}


	public void setUserId( long userId ) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName( String userName ) {
		this.userName = userName;
	}

}
