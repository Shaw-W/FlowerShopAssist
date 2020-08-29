package Run;
import java.util.Scanner;

import com.entity.*;
public class Main {

	public static void main(String[] args) {
	

		System.out.println("欢迎来到鲜花商店，请选择您要进入的模式：\n 1.管理员模式\t2.用户模式");
		Scanner input =new Scanner(System.in);
		int choose=input.nextInt();
		switch(choose) {
		case 1:
			System.out.println("请选择您要进行的操作：1.登录\t2.注册\t3.退出");
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
			System.out.println("请选择您要进行的操作：1.登录\t2.注册\t3.退出");
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
