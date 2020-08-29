package com.dao.func;

import java.util.*;
import java.sql.*;

import com.dao.flowerDB;
import com.dao.connectDB;
import com.entity.Flower;

public class flowerDBfunc extends connectDB implements flowerDB {
	
	//DB connection
	private Connection connect = null;
			
	//run sql
	private PreparedStatement preState = null;
			
	//save result
	private ResultSet rsSet = null;
	
	@Override
	public List<Flower> getAllFlower() {
		// TODO 自动生成的方法存根
		List<Flower> flowerList = new ArrayList<Flower>();
		try {
			String preSQL = "select * from flower where on_sale=true";
			connect = getConnect();
			preState = connect.prepareStatement(preSQL);
			rsSet = preState.executeQuery();
			while (rsSet.next()) {
				Flower flower = new Flower();
				flower.setId(rsSet.getLong(1));
				flower.setName(rsSet.getString(2));
				flower.setPrice(rsSet.getInt(3));
				flower.setStorage(rsSet.getInt(4));
				flowerList.add(flower);
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
		return flowerList;
	}

	@Override
	public List<Flower> selectFlower(String sqlOK, String[] strArr) {
		// TODO 自动生成的方法存根
		List<Flower> flowerLi = new ArrayList<Flower>();
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
				Flower flower = new Flower();
				flower.setId(rsSet.getLong(1));
				flower.setName(rsSet.getString(2));
				flower.setPrice(rsSet.getInt(3));
				flower.setStorage(rsSet.getInt(4));
				flowerLi.add(flower);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			super.closeAll(connect, preState, rsSet);
		}
		return flowerLi;
	}

	@Override
	public int updateFlower(String sqlOK, Object[] strArr) {
		// TODO 自动生成的方法存根
		int count = super.runSQL(sqlOK, strArr);
		return count;
	}
	
}