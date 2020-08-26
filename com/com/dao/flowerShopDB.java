package com.dao;

import java.util.List;
import com.entity.FlowerShop;

public interface flowerShopDB {
	
	//search all flower store
	public List<FlowerShop>getAllShop();
	
	//update information
	public int updateShop(String sqlOK, Object[] strArr);
	
	//get information with conditions
	public FlowerShop getFlowerShop(String sqlOK, Object[] strArr);

}
