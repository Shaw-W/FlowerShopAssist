package com.entity;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;


import com.dao.flowerDB;
import com.dao.func.flowerDBfunc;




/**
 * �ͻ��˻����̳�Account�˻�����
 * @author ����ǿ
 *
 */
public class Customer extends Account {
	/**
	* ���ﳵ
	*/
	private ShoppingCart cart=new ShoppingCart();
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
	public boolean login() {

		Scanner input = new Scanner(System.in);
		System.out.println("����������ID��");  
		long cuId = input.nextLong();
		input.nextLine();
		System.out.println("�����������룺");
		String cuPassword = input.nextLine().trim();
		
		try 
		{ 
			Connection connect = DriverManager.getConnection("jdbc:mysql://123.207.179.140:3306/flowerassist","flowerassist","flowerassist");
			Statement stmt = connect.createStatement(); 
			ResultSet rs = stmt.executeQuery("select * from account where a_id="+cuId+" and a_pw="+cuPassword);  //user Ϊ�������� 
			int id=0;
			while (rs.next()) {
				id = rs.getInt(1);
				this.setId(cuId);
				this.setName(rs.getString(2));
				this.setPassword(cuPassword);
				this.setType(rs.getString(4));
				}
			if(id==0)
			{
				System.out.println("����������������������");
			}
			else
			{
				System.out.println("�𾴵�"+this.getType()+",��ӭ��!");
				connect.close();
				return true;
			}
			}
		catch (Exception e) 
		{ 
			System.out.print("��ȡ���ݴ���!");
			e.printStackTrace(); 
		}
		
		return false;
		
		
	}
	

	public void buy() {
		/*�������ݿ��ʻ�����*/
		flowerDB f=new flowerDBfunc();
		List<Flower> flowerList = f.getAllFlower();
		/*�г����ϼܵ��ʻ�*/
		boolean flag1=true;
		Scanner input=new Scanner(System.in);
		System.out.println("�ʻ����\t" + "�ʻ�����\t"+"�۸�\t"+"ʣ����\t"+"");
		for (int i = 0; i < flowerList.size(); i++) {
		Flower flower = flowerList.get(i);
			System.out.println( flower.getId()+"\t"+ flower.getName()+"\t"+ flower.getPrice()+"\t"+flower.getStorage());
		   }
		while(flag1) {
			System.out.println("��Ҫ���еĲ�����1.���빺�ﳵ  2.�ӹ��ﳵ���Ƴ�  3.�鿴���ﳵ  4.����");
			int choose=input.nextInt();
			switch(choose) {
			case 1:   //�ӹ��ʻ�
				{
					String name=null;
					long id;
					int count;
					while(true) {
						Boolean flag=false;
						System.out.println("��ѡ����Ҫ������ʻ���ţ�");
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
							System.out.println("��������������������룺");
						else 
							break;
					}
					System.out.println("��ѡ����Ҫ���� "+name+" ������");
					while(true) {
						count=input.nextInt();
						Boolean flag=false;
						for(int i=0;i<flowerList.size();i++)
							if((id==flowerList.get(i).getId())&&(flowerList.get(i).getStorage()>=count))
									flag=true;
						if(!flag)
							System.out.println("����������㣬���������룺");
						else
							break;
					}
					System.out.println("�ɹ���ӹ��ﳵ");
					cart.addFlower(id, count);
					break;
				}
			case 2: //�ӹ��ﳵ���Ƴ��ʻ�
			{
				long id;
				while(true) {
				System.out.println("��������Ҫ�Ƴ����ʻ��ı�ţ�");
				id=input.nextLong();
				if(cart.removeFlower(id)) {
					System.out.println("�Ƴ��ɹ���");
					break;
					}
				else
					System.out.println("�����������������룺");
				}
				break;	
			}
			case 3: //�г����ﳵ��Ʒ��
			{
				ArrayList<Long> flowerid=cart.getFlowerIds();
				ArrayList<Integer> flowernum=cart.getFlowerNums();
				System.out.println("���\t����\t����");
				for(int i=0;i<flowerid.size();i++)
					for(int j=0;j<flowerList.size();j++)
						if(flowerid.get(i)==flowerList.get(j).getId()) {
							String name=flowerList.get(j).getName();
							System.out.println(flowerid.get(i)+"\t"+name+"\t"+flowernum.get(i));
							break;
						}
				break;
			}
			case 4://����
			{
				System.out.println("��ȷ�������ﳵ�����Ʒ��");
				ArrayList<Long> flowerid=cart.getFlowerIds();
				ArrayList<Integer> flowernum=cart.getFlowerNums();
				double totalPrice=0;
				System.out.println("���\t����\t����\t�ܼ�");
				for(int i=0;i<flowerid.size();i++)
					for(int j=0;j<flowerList.size();j++)
						if(flowerid.get(i)==flowerList.get(j).getId()) {
							String name=flowerList.get(j).getName();
							double price=flowerList.get(j).getPrice();
							totalPrice+=price*flowernum.get(i);
							System.out.println(flowerid.get(i)+"\t"+name+"\t"+flowernum.get(i)+"\t"+price*flowernum.get(i));
							break;
						}
				System.out.println("����"+totalPrice+"Ԫ");
				double discount=1;
				switch(this.getType()) {
				case "�ƽ��Ա":
					System.out.println("���������������Ļƽ��Ա������8���Żݣ�");
					discount=0.8;
					break;
				case"�׽��Ա":
					System.out.println("���������������İ׽��Ա������85���Żݣ�");
					discount=0.85;
					break;
				case"��ͨ��Ա":
					System.out.println("���������������İ׽��Ա������9���Żݣ�");
					discount=0.9;
					break;
				}
				totalPrice*=discount;
				System.out.println("������Ҫ֧��"+totalPrice+"Ԫ");
				System.out.println("֧���ɹ�����ӭ�´ι���");
				/*�޸����ݿ����ʻ��Ŀ��*/
				for(int i=0;i<cart.getFlowerIds().size();i++)
				{
					long n=0;
					for(int j=0;j<flowerList.size();j++)
						if(flowerList.get(j).getId()==cart.getFlowerIds().get(i))
							n=flowerList.get(j).getStorage();
					flowerDB f1=new flowerDBfunc();
					Object[] param= {(n-cart.getFlowerNums().get(i)), this.cart.getFlowerIds().get(i)};
					f1.updateFlower("update flower set f_dock=? where f_id=?", param);
					
				}
				/*����������д�����ݿ�*/
				try 
				{ 
					long o_id = 0;
					Connection connect = DriverManager.getConnection( "jdbc:mysql://123.207.179.140:3306/flowerassist","flowerassist","flowerassist");
					Statement stmt = connect.createStatement(); 
					Date dNow = new Date( );
				    SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
				    //System.out.println("��ǰʱ��Ϊ: " + ft.format(dNow));
					stmt.execute("insert into orders(a_id,total,o_time) values("+this.getId()+","+totalPrice+",\'"+ft.format(dNow)+"\')");
					ResultSet rs = stmt.executeQuery("select o_id from orders where a_id="+this.getId()+" and o_time=\'"+ft.format(dNow)+"\'");

					while (rs.next()) {
						o_id = rs.getInt(1);
					}
					for(int i=0;i<cart.getFlowerIds().size();++i) {
						stmt.execute("insert into detail(o_id,f_id,num) values("+o_id+","+cart.getFlowerIds().get(i)+","+cart.getFlowerNums().get(i)+")");
					}
					
				    connect.close();
		         } 
				catch (Exception e) 
				{ 
					System.out.print("д�����ݴ���!");
					e.printStackTrace(); 
				}
				
				return;
			}
			}
			
		}
}

public boolean register() {		//��Աע��
	long id;//�˺�
	String name; //����
	String type; //��Ա����
	String password; //����
	String password2; //ȷ������
	Scanner input=new Scanner(System.in);
	while(true) {
	System.out.print("��������Ҫע����˺�");
	id = input.nextLong();
	input.nextLine();
	try 
	{ 
		Connection connect = DriverManager.getConnection( "jdbc:mysql://123.207.179.140:3306/flowerassist","flowerassist","flowerassist");
		Statement stmt = connect.createStatement(); 
		ResultSet rs = stmt.executeQuery("select count(*) from account where a_id="+id);  //user Ϊ�������� 
		int count=0;
		while (rs.next()) {
			count = rs.getInt(1);
			}
		if(count!=0)
			System.out.print("�˺Ŵ���,���������룺");
		else
			break;

	    connect.close();
     } 
	catch (Exception e) 
	{ 
		System.out.print("��ȡ���ݴ���!");
		e.printStackTrace(); 
	}
	}
	System.out.println("������������ʵ������");
	name=input.nextLine();
	while(true) {
		System.out.print("�������������룺");
		password = input.nextLine();
		System.out.print("�ٴ������������룺");
		password2 = input.nextLine();
		if(password.equals(password2))
			break;
		System.out.println("�����������벻һ�£����������룺");

	}
	System.out.println("��������Ҫע��Ļ�Ա���ͣ�");
	type=input.nextLine().trim();
		
	try 
	{ 
		Connection connect = DriverManager.getConnection( "jdbc:mysql://123.207.179.140:3306/flowerassist?useUnicode=true&characterEncoding=UTF-8","flowerassist","flowerassist");
		Statement stmt = connect.createStatement(); 
		stmt.executeUpdate("insert into account values ("+id+",'"+name+"','"+password+"','"+type+"')");  //user Ϊ�������� 
	    connect.close();
     } 
	catch (Exception e) 
	{ 
		System.out.print("��ȡ���ݴ���!");
		e.printStackTrace(); 
	}
	this.setId(id);
	this.setName(name);
	this.setPassword(password);
	System.out.print("ע��ɹ���");
	return true; 
}


}