package com.entity;

/**
 * 账户基类
 * @author 杜文强
 *
 */
public class Account {
	/**
	 * 账户id
	 */
	private long id;
	
	/**
	 * 账户名
	 */
	private String name;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * get and set
	 */
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
