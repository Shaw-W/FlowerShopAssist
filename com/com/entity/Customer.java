package com.entity;
import java.sql.*;
import java.util.*;

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
	private String type;
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * get and set
	 */	
	public ShoppingCart getCart() {
		return cart;
	}

	public void setCart(ShoppingCart cart) {
		this.cart = cart;
	}
	
	public void login() {
		Scanner input = new Scanner(System.in);
		System.out.println("请输入您的ID：");  
		long cuId = input.nextLong();
		System.out.println("请您输入密码：");
		String cuPassword = input.nextLine().trim();
		try 
		{ 
			Connection connect = DriverManager.getConnection( "jdbc:mysql://123.207.179.140:3306/flowerassist","flowerassist","flowerassist");
			Statement stmt = connect.createStatement(); 
			ResultSet rs = stmt.executeQuery("select count(*) from customer where id="+cuId+" and password="+cuPassword);  //user 为你表的名称 
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
				this.setId(cuId);
				this.setName(rs.getString(2));
				this.setPassword(cuPassword);
				this.setType(rs.getString(3));
				System.out.print("尊敬的"+this.getType()+",欢迎您!");
			}
		    connect.close();
         } 
		catch (Exception e) 
		{ 
			System.out.print("获取数据错误!");
			e.printStackTrace(); 
		}
		
	}
	
	public void buy() {
		/*导入数据库鲜花数据*/
		List<Flower> flowerList = new ArrayList<Flower>();
		try {
			Connection connect = DriverManager.getConnection( "jdbc:mysql://123.207.179.140:3306/flowerassist","flowerassist","flowerassist");
			Statement stmt = connect.createStatement(); 
			ResultSet rs = stmt.executeQuery("select id,name,price,stock from flower"); // 执行SQL语句

			while (rs.next()) {
				Flower flower = new Flower();
				flower.setId(rs.getInt(1));
				flower.setName(rs.getString(2));
				flower.setPrice(rs.getDouble(3));
				flower.setStorage(rs.getInt(4));
				flowerList.add(flower);
			}
		    connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		
		Scanner input=new Scanner(System.in);
		System.out.println("序号\t" + "鲜花名称\t"+"鲜花品种\t"+"鲜花售价\t"+"");
		for (int i = 0; i < flowerList.size(); i++) {
		Flower flower = flowerList.get(i);
			System.out.println((i + 1)+"\t"+ flower.getId()+"\t"+ flower.getName()+"\t"+ flower.getPrice()+"\t");
		   }
		while(true) {
			System.out.println("您要进行的操作：1.加入购物车  2.从购物车中移除  3.查看购物车  4.结算");
			int choose=input.nextInt();
			switch(choose) {
			case 1:   //加购鲜花
				{
					String name = null;
					long id;
					int count;
					while(true) {
						Boolean flag=false;
						System.out.println("请选择您要购买的鲜花编号：");
						id=input.nextLong();
						for(int i=0;i<flowerList.size();i++)
						{
							if(id==flowerList.get(i).getId())
								{
									flag=true;
									name=flowerList.get(i).getName();
								}
							}
						if(!flag)
							System.out.println("输入序号有误，请重新输入：");
						else 
							break;
					}
					System.out.println("请选择您要购买 "+name+" 的数量");
					while(true) {
						count=input.nextInt();
						Boolean flag=false;
						for(int i=0;i<flowerList.size();i++)
							if((id==flowerList.get(i).getId())&&(flowerList.get(i).getStorage()>=count))
									flag=true;
						if(!flag)
							System.out.println("库存数量不足，请重新输入：");
						else
							break;
					}
					System.out.println("成功添加购物车");
					cart.addFlower(id, count);
					break;
				}
			case 2: //从购物车中移除鲜花
			{
				long id;
				while(true) {
				System.out.println("请输入您要移除的鲜花的编号：");
				id=input.nextLong();
				if(cart.removeFlower(id)) {
					System.out.println("移除成功！");
					break;
					}
				else
					System.out.println("输入有误，请重新输入：");
				}
				break;	
			}
			case 3: //列出购物车商品项
			{
				ArrayList<Long> flowerid=cart.getFlowerIds();
				ArrayList<Integer> flowernum=cart.getFlowerNums();
				System.out.println("编号\t名称\t数量");
				for(int i=0;i<flowerid.size();i++)
					for(int j=0;j<flowerList.size();j++)
						if(flowerid.get(i)==flowerList.get(j).getId()) {
							String name=flowerList.get(j).getName();
							System.out.println(flowerid.get(i)+"\t"+name+"\t"+flowernum.get(i));
							break;
						}
				break;
			}
			case 4://结算
			{
				System.out.println("请确认您购物车里的商品：");
				ArrayList<Long> flowerid=cart.getFlowerIds();
				ArrayList<Integer> flowernum=cart.getFlowerNums();
				double sumPrice=0;
				System.out.println("编号\t名称\t数量\t总计");
				for(int i=0;i<flowerid.size();i++)
					for(int j=0;j<flowerList.size();j++)
						if(flowerid.get(i)==flowerList.get(j).getId()) {
							String name=flowerList.get(j).getName();
							double price=flowerList.get(j).getPrice();
							sumPrice+=price*flowernum.get(i);
							System.out.println(flowerid.get(i)+"\t"+name+"\t"+flowernum.get(i)+"\t"+price*flowernum.get(i));
							break;
						}
				System.out.println("共计"+sumPrice+"元");
				double discount = 0.0;
				switch(this.getType()) {
				case "黄金会员":
					System.out.println("由于您是我们尊贵的黄金会员，享受8折优惠！");
					discount=0.8;
					break;
				case"白金会员":
					System.out.println("由于您是我们尊贵的白金会员，享受85折优惠！");
					discount=0.85;
					break;
				case"普通会员":
					System.out.println("由于您是我们尊贵的白金会员，享受9折优惠！");
					discount=0.9;
					break;
				}
				System.out.println("您共需要支付"+sumPrice*discount+"元");
				System.out.println("支付成功！欢迎下次光临");
				break;
			}
			}
		}		
	}

	public static void register() {		//会员注册
		String id;//账号
		String name; //姓名
		String type; //会员类型
		String password; //密码
		String password2; //确认密码
		Scanner input=new Scanner(System.in);
		while(true) {
		System.out.print("请输入您要注册的账号");
		id = input.nextLine();
		try { 
			Connection connect = DriverManager.getConnection( "jdbc:mysql://123.207.179.140:3306/flowerassist","flowerassist","flowerassist");
			Statement stmt = connect.createStatement(); 
			ResultSet rs = stmt.executeQuery("select count(*) from customer where id="+id);  //user 为你表的名称 
			int count=0;
			while (rs.next()) {
				count = rs.getInt(1);
				}
			if(count!=0)
				System.out.print("账号存在,请重新输入：");
			else
				break;
			    connect.close();
		    } 
		catch (Exception e) { 
			System.out.print("获取数据错误!");
			e.printStackTrace(); 
			}
		}
		System.out.println("请输入您的真实姓名：");
		name=input.nextLine();
		while(true) {
			System.out.print("请输入您的密码：");
			password = input.nextLine();
			System.out.print("再次输入您的密码：");
			password2 = input.nextLine();
			if(password.equals(password2))
				break;
			System.out.println("两次输入密码不一致，请重新输入：");
		}
		System.out.println("请输入您要注册的会员类型：");
		type=input.nextLine();
				
		try { 
			Connection connect = DriverManager.getConnection( "jdbc:mysql://123.207.179.140:3306/flowerassist","flowerassist","flowerassist");
			Statement stmt = connect.createStatement(); 
			stmt.executeUpdate("insert into customer values ('"+id+"','"+name+"','"+type+"','"+password+"')");  //user 为你表的名称 
			connect.close();
		} 
			catch (Exception e) 
			{ 
				System.out.print("获取数据错误!");
				e.printStackTrace(); 
			}
			/*this.setId(id);
			this.setName(name);
			this.setPassword(password);*/
			System.out.print("注册成功！");
		}
	}

