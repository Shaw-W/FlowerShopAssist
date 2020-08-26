package com.dao;

import java.util.List;

import com.entity.Customer;

/**
 * customerDB interface
 * @author Shaw_W
 *
 */
public interface customerDB {
	
	//update customer information
	public int updateCustomer(String sqlOK, Object[] strArr);
	
	//get all customer info
	public List<Customer> getAllCustomers();
	
	//select customer
	public Customer selectCustomer(String sqlOK, String[] strArr);
}
