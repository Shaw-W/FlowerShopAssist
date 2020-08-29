package com.entity;

import java.sql.*;
import java.util.Scanner;


/**
 * ����Ա�˻����̳�Account�˻�����
 * @author ����ǿ
 *
 */
public class Administrator extends Account {
	public static void Signin() {  //����Ա��¼
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
			ResultSet rs = stmt.executeQuery("select count(*) from account where a_id="+id+" and a_pw="+password +" and vip_level='Administrator'");  //user Ϊ�������� 
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
				System.out.println("ѡ����Ҫ���еĲ���: 1.������ѯ 2.�ʻ����� 3.�˳�");
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
			System.out.print("��ȡ���ݴ���!");
			e.printStackTrace(); 
		}
	}
	
	public static void register() {		//����Աע�ᣨ��������Ա��
		String id;
		String password;
		String password2;
		String name;
		Scanner input=new Scanner(System.in);
		System.out.print("���������Ա�˺�:");
		id = input.nextLine();
		System.out.print("���������Ա����:");
		password = input.nextLine();
		System.out.print("ȷ������:");
		password2 = input.nextLine();
		System.out.print("���������Ա����");
		name = input.nextLine();
		try 
		{ 
			Connection connect = DriverManager.getConnection( "jdbc:mysql://123.207.179.140:3306/flowerassist","flowerassist","flowerassist");
			Statement stmt = connect.createStatement(); 
			ResultSet rs = stmt.executeQuery("select count(*) from account where a_id="+id+" and vip_level='Administrator'");  //user Ϊ�������� 
			int count=0;
			while (rs.next()) {
				count = rs.getInt(1);
				}
			if(count==1)
			{
				System.out.print("�˺Ŵ���");
				return;
			}
		    connect.close();
         } 
		catch (Exception e) 
		{ 
			System.out.print("��ȡ���ݴ���!");
			e.printStackTrace(); 
		}
		while(true) {
			if(password.equals(password2))
			{
				break;
			}
			System.out.print("���벻ͬ��");
			System.out.print("���������Ա�˺�:");
			id = input.nextLine();
			System.out.print("���������Ա����:");
			password = input.nextLine();
			System.out.print("ȷ������:");
			password2 = input.nextLine();
		}
		try 
		{ 
			Connection connect = DriverManager.getConnection( "jdbc:mysql://123.207.179.140:3306/flowerassist","flowerassist","flowerassist");
			Statement stmt = connect.createStatement(); 	
			stmt.executeUpdate("insert into account values ('"+id+"','"+name+"','"+password+"','Administrator')");  //user Ϊ�������� 
		    connect.close();
         } 
		catch (Exception e) 
		{ 
			System.out.print("��ȡ���ݴ���!");
			e.printStackTrace(); 
		}
		System.out.print("ע��ɹ���");
	}
	
	public static void orderinquiry() {		//������ѯ
		try 
		{ 
			Connection connect = DriverManager.getConnection( "jdbc:mysql://123.207.179.140:3306/flowerassist","flowerassist","flowerassist");
			Statement stmt = connect.createStatement(); 
			ResultSet rs =stmt.executeQuery("select * from orders");  //user Ϊ�������� 
			int oid;
			int aid;
			int total;
			Time time;
			while (rs.next()) {
                oid = rs.getInt("o_id");
                aid = rs.getInt("a_id");
                total = rs.getInt("total");
                time = rs.getTime("o_time");
                System.out.println("�˵�id:"+oid+" �û�id:"+aid+ " �������:"+total+" ʱ��:"+time);
                System.out.println("");
            }
		    connect.close();
         } 
		catch (Exception e) 
		{ 
			System.out.print("��ȡ���ݴ���!");
			e.printStackTrace(); 
		}
	}
	
	public static void FlowerManage() {			//�ʻ�����
			System.out.print("������������еĲ�����1.�ʻ��ϼ�  2.�ʻ��¼�  3.�ı���  4.����Ʒ��  5.ɾ��Ʒ��\n");
			Scanner input=new Scanner(System.in);
			int choice = input.nextInt();
			if(choice==1)
			{
				try 
				{ 
					Connection connect = DriverManager.getConnection( "jdbc:mysql://123.207.179.140:3306/flowerassist","flowerassist","flowerassist");
					Statement stmt = connect.createStatement(); 
					ResultSet rs =stmt.executeQuery("select * from flower");  //user Ϊ�������� 
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
		                System.out.println("�ʻ�id:"+id+" name:"+name+" price:"+price+" stock:"+stock+" on_the_shelf:"+on_the_shelf+"\n");
		            }
					System.out.print("ѡ����Ҫ�ϼܵ��ʻ�id:");
					int choose_id = input.nextInt();
					ResultSet rs2 = stmt.executeQuery("select count(*) from flower where f_id="+choose_id+" and on_sale=0");
					int count=0;
					while (rs2.next()) {
						count = rs2.getInt(1);
						}
					if(count==1)
					{
						System.out.println("�ϼܳɹ���");
						stmt.executeUpdate("update flower set on_sale=1 where f_id="+choose_id);
					}
					else
					{
						System.out.println("id������Ѿ��ϼ�");
					}
				    connect.close();
		         } 
				catch (Exception e) 
				{ 
					System.out.println("��ȡ���ݴ���!");
					e.printStackTrace(); 
				}
			}
			if(choice==2)
			{
				try 
				{ 
					Connection connect = DriverManager.getConnection( "jdbc:mysql://123.207.179.140:3306/flowerassist","flowerassist","flowerassist");
					Statement stmt = connect.createStatement(); 
					ResultSet rs =stmt.executeQuery("select * from flower");  //user Ϊ�������� 
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
		                System.out.println("�ʻ�id:"+id+" name:"+name+" price:"+price+" stock:"+stock+" on_the_shelf:"+on_the_shelf+"\n");
		            }
					System.out.print("ѡ����Ҫ�¼ܵ��ʻ�id:");
					int choose_id = input.nextInt();
					ResultSet rs2 = stmt.executeQuery("select count(*) from flower where f_id="+choose_id+" and on_sale=1");
					int count=0;
					while (rs2.next()) {
						count = rs2.getInt(1);
						}
					if(count==1)
					{
						System.out.println("�¼ܳɹ���");
						stmt.executeUpdate("update flower set on_sale=0 where f_id="+choose_id);
					}
					else
					{
						System.out.println("id������Ѿ��¼�");
					}
				    connect.close();
		         } 
				catch (Exception e) 
				{ 
					System.out.println("��ȡ���ݴ���!");
					e.printStackTrace(); 
				}
			}
			if(choice==3)
			{
				try 
				{ 
					Connection connect = DriverManager.getConnection( "jdbc:mysql://123.207.179.140:3306/flowerassist","flowerassist","flowerassist");
					Statement stmt = connect.createStatement(); 
					ResultSet rs =stmt.executeQuery("select * from flower");  //user Ϊ�������� 
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
		                System.out.println("�ʻ�id:"+id+" name:"+name+" price:"+price+" stock:"+stock+" on_the_shelf:"+on_the_shelf+"\n");
		            }
					System.out.print("ѡ��ı�����ʻ�id:");
					int choose_id = input.nextInt();
					ResultSet rs2 = stmt.executeQuery("select count(*) from flower where f_id="+choose_id);
					int count=0;
					while (rs2.next()) {
						count = rs2.getInt(1);
						}
					if(count==1)
					{
						System.out.print("�������޸ĺ�Ŀ����:");
						int change_stock=input.nextInt();
						stmt.executeUpdate("update flower set f_dock="+change_stock+" where f_id="+choose_id);
						System.out.println("�޸ĳɹ���");
					}
					else
					{
						System.out.println("id����");
					}
				    connect.close();
		         } 
				catch (Exception e) 
				{ 
					System.out.println("��ȡ���ݴ���!");
					e.printStackTrace(); 
				}
			}
			if(choice==4)
			{
				try 
				{ 
					Connection connect = DriverManager.getConnection( "jdbc:mysql://123.207.179.140:3306/flowerassist","flowerassist","flowerassist");
					Statement stmt = connect.createStatement(); 
					ResultSet rs =stmt.executeQuery("select * from flower");  //user Ϊ�������� 
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
		                System.out.println("�ʻ�id:"+id+" name:"+name+" price:"+price+" stock:"+stock+" on_the_shelf:"+on_the_shelf+"\n");
		            }
					System.out.print("��Ҫ����Ʒ�ֵ�id:");
					int new_id = input.nextInt();
					ResultSet rs2 = stmt.executeQuery("select count(*) from flower where f_id="+new_id);
					int count=0;
					while (rs2.next()) {
						count = rs2.getInt(1);
						}
					if(count==1)
					{
						System.out.print("��Ʒ���Ѿ�����");
					}
					else
					{
						String new_name;
						int new_price;
						int new_stock;
						int new_on_the_shelf;
						Scanner input2=new Scanner(System.in);
						System.out.print("��������:");
						new_name=input2.nextLine();
						System.out.print("����۸�:");
						new_price=input2.nextInt();
						System.out.print("������:");
						new_stock=input2.nextInt();
						System.out.print("�Ƿ��ϼ�:");
						new_on_the_shelf=input.nextInt();
						stmt.executeUpdate("insert into flower values('"+new_id+"','"+new_name+"','"+new_price+"','"+new_stock+"','"+new_on_the_shelf+"')");
						System.out.print("�������");
					}
				    connect.close();
		         } 
				catch (Exception e) 
				{ 
					System.out.print("��ȡ���ݴ���!");
					e.printStackTrace(); 
				}
			}
			if(choice==5)
			{
				try 
				{ 
					Connection connect = DriverManager.getConnection( "jdbc:mysql://123.207.179.140:3306/flowerassist","flowerassist","flowerassist");
					Statement stmt = connect.createStatement(); 
					ResultSet rs =stmt.executeQuery("select * from flower");  //user Ϊ�������� 
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
		                System.out.println("�ʻ�id:"+id+" name:"+name+" price:"+price+" stock:"+stock+" on_the_shelf:"+on_the_shelf+"\n");
		            }
					System.out.print("��Ҫɾ��Ʒ�ֵ�id:");
					int choose_id = input.nextInt();
					ResultSet rs2 = stmt.executeQuery("select count(*) from flower where f_id="+choose_id);
					int count=0;
					while (rs2.next()) {
						count = rs2.getInt(1);
						}
					if(count==0)
					{
						System.out.print("��Ʒ�ֲ�����");
					}
					else
					{	
						stmt.executeUpdate("delete from flower where f_id="+choose_id);
						System.out.print("�������");
					}
				    connect.close();
		         } 
				catch (Exception e) 
				{ 
					System.out.print("��ȡ���ݴ���!");
					e.printStackTrace(); 
				}
			}
	}
}
