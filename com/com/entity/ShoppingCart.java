package com.entity;

import java.util.ArrayList;

/**
 * 购物车实体类
 * @author 杜文强
 *
 */
public class ShoppingCart {
	/**
	 * 鲜花种类列表
	 */
	private ArrayList<Long> flowerIds = new ArrayList<Long>();
	
	/**
	 * 鲜花数量列表
	 */
	private ArrayList<Integer> flowerNums = new ArrayList<Integer>();

	/**
	 * set and get
	 */
	public ArrayList<Long> getFlowerIds() {
		return flowerIds;
	}

	public void setFlowerIds(ArrayList<Long> flowerIds) {
		this.flowerIds = flowerIds;
	}

	public ArrayList<Integer> getFlowerNums() {
		return flowerNums;
	}

	public void setFlowerNums(ArrayList<Integer> flowerNums) {
		this.flowerNums = flowerNums;
	}
	
	/**
	 * 购物车添加花
	 * @param id 要添加的花的id 
	 * @param num 要添加的花的数量
	 * @return 是否成功
	 */
	public boolean addFlower(long id, int num) {
		for(int i=0;i<flowerIds.size();++i) {
			if((long)flowerIds.get(i)==id) {
				flowerNums.set(i, new Integer((int)flowerNums.get(i)+num));
				return true;
			}
		}
		
		flowerIds.add(new Long(id));
		flowerNums.add(new Integer(num));
		return true;
	}
	
	/**
	 * 购物车移除花
	 * @param id 要移除的花的id
	 * @return 是否成功
	 */
	public boolean removeFlower(long id) {
		for(int i=0;i<flowerIds.size();++i) {
			if((long)flowerIds.get(i)==id) {
				flowerIds.remove(i);
				flowerNums.remove(i);
				return true;
			}
		}
		return false;
	}
}
