package com.entity;

import java.sql.*;
import java.util.Scanner;


/**
 * 管理员账户，继承Account账户基类
 * @author 杜文强
 *
 */
public class Administrator extends Account {
	public static void Signin() {  //管理员登录
		String id;
		String password;
		System.out.println("Please enter id:");
		Scanner input=new Scanner(System.in);
		id = input.nextLine();
		System.out.println("Please enter password:");
		password = input.nextLine();
		try 
		{ 
			Connection connect = DriverManager.getConnection( "jdbc:mysql://123.207.179.140:3306/flowerassist","flowerassist","flowerassist");
			Statement stmt = connect.createStatement(); 
			ResultSet rs = stmt.executeQuery("select count(*) from account where a_id="+id+" and a_pw="+password +" and vip_level='Administrator'");  //user 为你表的名称 
			int count=0;
			while (rs.next()) {
				count = rs.getInt(1);
				}
			if(count==0)
			{
				System.out.print("Wrong");
			}
			else
			{
				System.out.println("Welcome");
				while(true)
				{
				System.out.println("选择您要进行的操作: 1.订单查询 2.鲜花管理 3.退出");
				int choose = input.nextInt();
				if(choose==1)
				{
					orderinquiry();
				}
				if(choose==2)
				{
					FlowerManage();
				}
				if(choose==3)
				{
					return;
				}
				}
			}
		    connect.close();
         } 
		catch (Exception e) 
		{ 
			System.out.print("获取数据错误!");
			e.printStackTrace(); 
		}
	}
	
	public static void register() {		//管理员注册（新增管理员）
		String id;
		String password;
		String password2;
		String name;
		Scanner input=new Scanner(System.in);
		System.out.print("请输入管理员账号:");
		id = input.nextLine();
		System.out.print("请输入管理员密码:");
		password = input.nextLine();
		System.out.print("确认密码:");
		password2 = input.nextLine();
		System.out.print("请输入管理员名称");
		name = input.nextLine();
		try 
		{ 
			Connection connect = DriverManager.getConnection( "jdbc:mysql://123.207.179.140:3306/flowerassist","flowerassist","flowerassist");
			Statement stmt = connect.createStatement(); 
			ResultSet rs = stmt.executeQuery("select count(*) from account where a_id="+id+" and vip_level='Administrator'");  //user 为你表的名称 
			int count=0;
			while (rs.next()) {
				count = rs.getInt(1);
				}
			if(count==1)
			{
				System.out.print("账号存在");
				return;
			}
		    connect.close();
         } 
		catch (Exception e) 
		{ 
			System.out.print("获取数据错误!");
			e.printStackTrace(); 
		}
		while(true) {
			if(password.equals(password2))
			{
				break;
			}
			System.out.print("密码不同！");
			System.out.print("请输入管理员账号:");
			id = input.nextLine();
			System.out.print("请输入管理员密码:");
			password = input.nextLine();
			System.out.print("确认密码:");
			password2 = input.nextLine();
		}
		try 
		{ 
			Connection connect = DriverManager.getConnection( "jdbc:mysql://123.207.179.140:3306/flowerassist","flowerassist","flowerassist");
			Statement stmt = connect.createStatement(); 	
			stmt.executeUpdate("insert into account values ('"+id+"','"+name+"','"+password+"','Administrator')");  //user 为你表的名称 
		    connect.close();
         } 
		catch (Exception e) 
		{ 
			System.out.print("获取数据错误!");
			e.printStackTrace(); 
		}
		System.out.print("注册成功！");
	}
	
	public static void orderinquiry() {		//订单查询
		try 
		{ 
			Connection connect = DriverManager.getConnection( "jdbc:mysql://123.207.179.140:3306/flowerassist","flowerassist","flowerassist");
			Statement stmt = connect.createStatement(); 
			ResultSet rs =stmt.executeQuery("select * from orders");  //user 为你表的名称 
			int oid;
			int aid;
			int total;
			Time time;
			while (rs.next()) {
                oid = rs.getInt("o_id");
                aid = rs.getInt("a_id");
                total = rs.getInt("total");
                time = rs.getTime("o_time");
                System.out.println("账单id:"+oid+" 用户id:"+aid+ " 订单金额:"+total+" 时间:"+time);
                System.out.println("");
            }
		    connect.close();
         } 
		catch (Exception e) 
		{ 
			System.out.print("获取数据错误!");
			e.printStackTrace(); 
		}
	}
	
	public static void FlowerManage() {			//鲜花管理
			System.out.print("请输入您想进行的操作，1.鲜花上架  2.鲜花下架  3.改变库存  4.增加品种  5.删除品种\n");
			Scanner input=new Scanner(System.in);
			int choice = input.nextInt();
			if(choice==1)
			{
				try 
				{ 
					Connection connect = DriverManager.getConnection( "jdbc:mysql://123.207.179.140:3306/flowerassist","flowerassist","flowerassist");
					Statement stmt = connect.createStatement(); 
					ResultSet rs =stmt.executeQuery("select * from flower");  //user 为你表的名称 
					long id;
					String name;
					int price;
					int stock;
					boolean on_the_shelf;
					while (rs.next()) {
		                id = rs.getInt("f_id");
		                name = rs.getString("f_name");
		                price = rs.getInt("f_price");
		                stock = rs.getInt("f_dock");
		                on_the_shelf = rs.getBoolean("on_sale");
		                System.out.println("鲜花id:"+id+" name:"+name+" price:"+price+" stock:"+stock+" on_the_shelf:"+on_the_shelf+"\n");
		            }
					System.out.print("选择想要上架的鲜花id:");
					int choose_id = input.nextInt();
					ResultSet rs2 = stmt.executeQuery("select count(*) from flower where f_id="+choose_id+" and on_sale=0");
					int count=0;
					while (rs2.next()) {
						count = rs2.getInt(1);
						}
					if(count==1)
					{
						System.out.println("上架成功！");
						stmt.executeUpdate("update flower set on_sale=1 where f_id="+choose_id);
					}
					else
					{
						System.out.println("id错误或已经上架");
					}
				    connect.close();
		         } 
				catch (Exception e) 
				{ 
					System.out.println("获取数据错误!");
					e.printStackTrace(); 
				}
			}
			if(choice==2)
			{
				try 
				{ 
					Connection connect = DriverManager.getConnection( "jdbc:mysql://123.207.179.140:3306/flowerassist","flowerassist","flowerassist");
					Statement stmt = connect.createStatement(); 
					ResultSet rs =stmt.executeQuery("select * from flower");  //user 为你表的名称 
					long id;
					String name;
					int price;
					int stock;
					boolean on_the_shelf;
					while (rs.next()) {
		                id = rs.getInt("f_id");
		                name = rs.getString("f_name");
		                price = rs.getInt("f_price");
		                stock = rs.getInt("f_dock");
		                on_the_shelf = rs.getBoolean("on_sale");
		                System.out.println("鲜花id:"+id+" name:"+name+" price:"+price+" stock:"+stock+" on_the_shelf:"+on_the_shelf+"\n");
		            }
					System.out.print("选择想要下架的鲜花id:");
					int choose_id = input.nextInt();
					ResultSet rs2 = stmt.executeQuery("select count(*) from flower where f_id="+choose_id+" and on_sale=1");
					int count=0;
					while (rs2.next()) {
						count = rs2.getInt(1);
						}
					if(count==1)
					{
						System.out.println("下架成功！");
						stmt.executeUpdate("update flower set on_sale=0 where f_id="+choose_id);
					}
					else
					{
						System.out.println("id错误或已经下架");
					}
				    connect.close();
		         } 
				catch (Exception e) 
				{ 
					System.out.println("获取数据错误!");
					e.printStackTrace(); 
				}
			}
			if(choice==3)
			{
				try 
				{ 
					Connection connect = DriverManager.getConnection( "jdbc:mysql://123.207.179.140:3306/flowerassist","flowerassist","flowerassist");
					Statement stmt = connect.createStatement(); 
					ResultSet rs =stmt.executeQuery("select * from flower");  //user 为你表的名称 
					long id;
					String name;
					int price;
					int stock;
					boolean on_the_shelf;
					while (rs.next()) {
		                id = rs.getInt("f_id");
		                name = rs.getString("f_name");
		                price = rs.getInt("f_price");
		                stock = rs.getInt("f_dock");
		                on_the_shelf = rs.getBoolean("on_sale");
		                System.out.println("鲜花id:"+id+" name:"+name+" price:"+price+" stock:"+stock+" on_the_shelf:"+on_the_shelf+"\n");
		            }
					System.out.print("选择改变库存的鲜花id:");
					int choose_id = input.nextInt();
					ResultSet rs2 = stmt.executeQuery("select count(*) from flower where f_id="+choose_id);
					int count=0;
					while (rs2.next()) {
						count = rs2.getInt(1);
						}
					if(count==1)
					{
						System.out.print("请输入修改后的库存量:");
						int change_stock=input.nextInt();
						stmt.executeUpdate("update flower set f_dock="+change_stock+" where f_id="+choose_id);
						System.out.println("修改成功！");
					}
					else
					{
						System.out.println("id错误");
					}
				    connect.close();
		         } 
				catch (Exception e) 
				{ 
					System.out.println("获取数据错误!");
					e.printStackTrace(); 
				}
			}
			if(choice==4)
			{
				try 
				{ 
					Connection connect = DriverManager.getConnection( "jdbc:mysql://123.207.179.140:3306/flowerassist","flowerassist","flowerassist");
					Statement stmt = connect.createStatement(); 
					ResultSet rs =stmt.executeQuery("select * from flower");  //user 为你表的名称 
					long id;
					String name;
					int price;
					int stock;
					boolean on_the_shelf;
					while (rs.next()) {
		                id = rs.getInt("f_id");
		                name = rs.getString("f_name");
		                price = rs.getInt("f_price");
		                stock = rs.getInt("f_dock");
		                on_the_shelf = rs.getBoolean("on_sale");
		                System.out.println("鲜花id:"+id+" name:"+name+" price:"+price+" stock:"+stock+" on_the_shelf:"+on_the_shelf+"\n");
		            }
					System.out.print("想要增加品种的id:");
					int new_id = input.nextInt();
					ResultSet rs2 = stmt.executeQuery("select count(*) from flower where f_id="+new_id);
					int count=0;
					while (rs2.next()) {
						count = rs2.getInt(1);
						}
					if(count==1)
					{
						System.out.print("该品种已经存在");
					}
					else
					{
						String new_name;
						int new_price;
						int new_stock;
						int new_on_the_shelf;
						Scanner input2=new Scanner(System.in);
						System.out.print("输入名称:");
						new_name=input2.nextLine();
						System.out.print("输入价格:");
						new_price=input2.nextInt();
						System.out.print("输入库存:");
						new_stock=input2.nextInt();
						System.out.print("是否上架:");
						new_on_the_shelf=input.nextInt();
						stmt.executeUpdate("insert into flower values('"+new_id+"','"+new_name+"','"+new_price+"','"+new_stock+"','"+new_on_the_shelf+"')");
						System.out.print("更新完成");
					}
				    connect.close();
		         } 
				catch (Exception e) 
				{ 
					System.out.print("获取数据错误!");
					e.printStackTrace(); 
				}
			}
			if(choice==5)
			{
				try 
				{ 
					Connection connect = DriverManager.getConnection( "jdbc:mysql://123.207.179.140:3306/flowerassist","flowerassist","flowerassist");
					Statement stmt = connect.createStatement(); 
					ResultSet rs =stmt.executeQuery("select * from flower");  //user 为你表的名称 
					long id;
					String name;
					int price;
					int stock;
					boolean on_the_shelf;
					while (rs.next()) {
		                id = rs.getInt("f_id");
		                name = rs.getString("f_name");
		                price = rs.getInt("f_price");
		                stock = rs.getInt("f_dock");
		                on_the_shelf = rs.getBoolean("on_sale");
		                System.out.println("鲜花id:"+id+" name:"+name+" price:"+price+" stock:"+stock+" on_the_shelf:"+on_the_shelf+"\n");
		            }
					System.out.print("想要删除品种的id:");
					int choose_id = input.nextInt();
					ResultSet rs2 = stmt.executeQuery("select count(*) from flower where f_id="+choose_id);
					int count=0;
					while (rs2.next()) {
						count = rs2.getInt(1);
						}
					if(count==0)
					{
						System.out.print("该品种不存在");
					}
					else
					{	
						stmt.executeUpdate("delete from flower where f_id="+choose_id);
						System.out.print("更新完成");
					}
				    connect.close();
		         } 
				catch (Exception e) 
				{ 
					System.out.print("获取数据错误!");
					e.printStackTrace(); 
				}
			}
	}
}
