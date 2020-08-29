package com.entity;

import java.util.ArrayList;

/**
 * ���ﳵʵ����
 * @author ����ǿ
 *
 */
public class ShoppingCart {
	/**
	 * �ʻ������б�
	 */
	private ArrayList<Long> flowerIds = new ArrayList<Long>();
	
	/**
	 * �ʻ������б�
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
	 * ���ﳵ��ӻ�
	 * @param id Ҫ��ӵĻ���id 
	 * @param num Ҫ��ӵĻ�������
	 * @return �Ƿ�ɹ�
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
	 * ���ﳵ�Ƴ���
	 * @param id Ҫ�Ƴ��Ļ���id
	 * @return �Ƿ�ɹ�
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
