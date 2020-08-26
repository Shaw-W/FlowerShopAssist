package com.dao.func;

import java.util.*;
import java.sql.*;

import com.dao.customerDB;
import com.dao.connectDB;
import com.entity.Customer;

//customer info
public class customerDBfunc extends connectDB implements customerDB{
	
	//DB connection
	private Connection connect = null;
		
	//run sql
	private PreparedStatement preState = null;
		
	//save result
	private ResultSet rsSet = null;
	
	@Override
	public int updateCustomer(String sqlOK, Object[] strArr) {
		// TODO 自动生成的方法存根
		int count = super.runSQL(sqlOK, strArr);
		return count;
	}

	@Override
	public List<Customer> getAllCustomers() {
		// TODO 自动生成的方法存根
		List<Customer> customerList = new ArrayList<Customer>();
		try {
			String preSQL = "select * from customer";
			connect = getConnect();
			preState = connect.prepareStatement(preSQL);
			rsSet = preState.executeQuery();
			while (rsSet.next()) {
				Customer customer = new Customer();
				customer.setCart(rsSet.getInt(1));
				customerList.add(customer);
		}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			super.closeAll(connect, preState, rsSet);
		}
		return customerList;
	}

	@Override
	public Customer selectCustomer(String sqlOK, String[] strArr) {
		// TODO 自动生成的方法存根
		Customer custom = null;
		try {
			connect = getConnect();
			preState = connect.prepareStatement(sqlOK);
			if (strArr != null) {
				for (int i = 0; i < strArr.length; i++) {
					preState.setString(i + 1, strArr[i]);
				}
			}
			
			rsSet = preState.executeQuery();
			while (rsSet.next()) {
				custom = new Customer();
				custom.setCart(rsSet.getInt(1));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			super.closeAll(connect, preState, rsSet);
		}
		return custom;
	}
}
