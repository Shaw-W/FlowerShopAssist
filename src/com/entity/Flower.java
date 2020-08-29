package com.entity;

/**
 * 鲜花实体类
 * @author 杜文强
 */
public class Flower {
	/**
	 * 鲜花标识符
	 */
	private long id;
	
	/**
	 * 鲜花名称
	 */
	private String name;
	
	/**
	 * 鲜花价格（单价）
	 */
	private double price;
	
	/**
	 * 仓库余量
	 */
	private int storage;
	
	
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStorage() {
		return storage;
	}
	public void setStorage(int storage) {
		this.storage = storage;
	}
	
}
