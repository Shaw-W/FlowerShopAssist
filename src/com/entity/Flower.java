package com.entity;

/**
 * �ʻ�ʵ����
 * @author ����ǿ
 */
public class Flower {
	/**
	 * �ʻ���ʶ��
	 */
	private long id;
	
	/**
	 * �ʻ�����
	 */
	private String name;
	
	/**
	 * �ʻ��۸񣨵��ۣ�
	 */
	private double price;
	
	/**
	 * �ֿ�����
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
