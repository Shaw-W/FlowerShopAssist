package com.entity;

/**
 * �˻�����
 * @author ����ǿ
 *
 */
public class Account {
	/**
	 * �˻�id
	 */
	private long id;
	
	/**
	 * �˻���
	 */
	private String name;
	
	/**
	 * ����
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