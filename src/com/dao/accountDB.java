package com.dao;

import java.util.List;
import com.entity.Account;

/**
 * AccountDB interface
 * @author Shaw_W
 *
 */
public interface accountDB {
	
	//updata account information
	public int updateAccount(String sqlOK, Object[] strArr);
	
	//search for flowerstore bill
	public List<Account> getFlowerShopAccount(String sqlOK, String[] strArr);
	
}