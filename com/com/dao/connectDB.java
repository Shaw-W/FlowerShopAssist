package com.dao;

import java.io.*;
import java.sql.*;
import java.util.Properties;

//Basic DB Control

public class connectDB {
	public static String DRIVER = "com.mysql.jdbc.Driver"; // DB Driver
	
	Connection connection = null;
	
	//Link DB
	public Connection getConnect() throws ClassNotFoundException, SQLException{
		Connection connection = null;
		
		try {
			Class.forName(DRIVER);
			//Connect To DB Server
			connection = DriverManager.getConnection("jdbc:mysql://123.207.179.140:3306/flowerassist", "flowerassist", "flowerassist");
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return connection;
	}
	
	//release resource
	public void closeAll(Connection connect, PreparedStatement preState, ResultSet rsSet) {
		
		//if connect not null, close connect
		if (connect != null) {
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		//if preState not null, close preState
		if (preState != null) {
			try {
				preState.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		//if rsSet not null, close rsSet
		if (rsSet != null) {
			try {
				rsSet.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	//run sql
	public int runSQL(String sqlOK, Object[] strArr) {
		Connection connection = null;
		PreparedStatement preState = null;
		int tmp = 0;
		
		//do your sql
		try {
			connection = getConnect();
			preState = connection.prepareStatement(sqlOK);
			if (strArr != null) {
				for (int i = 0; i < strArr.length; i++) {
					preState.setObject(i + 1, strArr[i]);
				}
			}
			tmp = preState.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			this.closeAll(connection, preState, null);
		}
		
		return tmp;
	}
}