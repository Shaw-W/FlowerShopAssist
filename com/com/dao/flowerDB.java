package com.dao;

import java.util.List;
import com.entity.Flower;

public interface flowerDB {
	
	//search all the information about flower
	public List<Flower> getAllFlower();
	
	//search flower information based on known
	public List<Flower> selectFlower();
	
	//updata flower information
	public int updateFlower(String sqlOK, Object[] strArr);
	
}
