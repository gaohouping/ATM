package ATM2;

import java.sql.SQLException;

public class yanshi extends Thread{
	/*120����ʱ��������*/
	ATm yanshi=new ATm();
    public void run(){
    	System.out.println("ʣ�����ʱ��");
    	try {
    		for(int i=120;i>=0;i--){
			Thread.sleep(1000);
			System.out.print("  "+i);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println("��ʱ��δ���������̣�����ϵ�ͷ���");
    	try {
			yanshi.ATMexit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
