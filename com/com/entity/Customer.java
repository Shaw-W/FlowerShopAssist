package com.entity;

/**
 * 客户账户，继承Account账户基类
 * @author 杜文强
 *
 */
public class Customer extends Account {
	/**
	* 购物车
	*/
	private ShoppingCart cart;

	/**
	 * get and set
	 */	
	public ShoppingCart getCart() {
		return cart;
	}

	public void setCart(ShoppingCart cart) {
		this.cart = cart;
	}
}
