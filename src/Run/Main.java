package Run;
import java.util.Scanner;

import com.entity.*;
public class Main {

	public static void main(String[] args) {
	

		System.out.println("��ӭ�����ʻ��̵꣬��ѡ����Ҫ�����ģʽ��\n 1.����Աģʽ\t2.�û�ģʽ");
		Scanner input =new Scanner(System.in);
		int choose=input.nextInt();
		switch(choose) {
		case 1:
			System.out.println("��ѡ����Ҫ���еĲ�����1.��¼\t2.ע��\t3.�˳�");
			int choose2=input.nextInt();
			switch(choose2)
			{
			case 1:
				Administrator.Signin();
				break;
			case 2:
				Administrator.register();
				break;
			case 3:
				break;
			}
			break;
		case 2:
		{
			Customer customer=new Customer();
			System.out.println("��ѡ����Ҫ���еĲ�����1.��¼\t2.ע��\t3.�˳�");
			int choose1=input.nextInt();
			switch(choose1) {
			case 1:
				while(!customer.login());
				customer.buy();
				break;
			case 2:
				while(customer.register());
					customer.buy();
				break;
			case 3:
				return;
			}
			break;
		}
			
		}
		
	}

}
