package com.dao.func;

import java.util.*;
import java.sql.*;

import com.dao.accountDB;
import com.dao.connectDB;
import com.entity.Account;

/**
 * AccountDB func
 * @author Shaw_W
 *
 */
public class accountDBfunc extends connectDB implements accountDB{
	
	//DB connection
	private Connection connect = null;
	
	//run sql
	private PreparedStatement preState = null;
	
	//save result
	private ResultSet rsSet = null;
	
	@Override
	public int updateAccount(String sqlOK, Object[] strArr) {
		// TODO 自动生成的方法存根
		int ac = super.runSQL(sqlOK, strArr);
		return ac;
	}

	@Override
	public List<Account> getFlowerShopAccount(String sqlOK, String[] strArr) {
		// TODO 自动生成的方法存根
		List<Account> acList = new ArrayList<Account>();
		try {
			connect = getConnect();//link db
			preState = connect.prepareStatement(sqlOK);//get
			if (strArr != null) {
				for (int i = 0; i < strArr.length; i++) {
					preState.setString(i + 1, strArr[i]);//set
				}
			}
			
			rsSet = preState.executeQuery();//run sql
			Account account = null;
			
			while (rsSet.next()) {
				account = new Account();
				account.setId(rsSet.getLong(1));
				account.setName(rsSet.getString(2));
				account.setPassword(rsSet.getString(3));
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
		return acList;
	}

}