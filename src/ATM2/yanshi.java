package ATM2;

import java.sql.SQLException;

public class yanshi extends Thread{
	/*120秒延时计数程序*/
	ATm yanshi=new ATm();
    public void run(){
    	System.out.println("剩余操作时间");
    	try {
    		for(int i=120;i>=0;i--){
			Thread.sleep(1000);
			System.out.print("  "+i);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println("长时间未操作卡已吞，请联系客服！");
    	try {
			yanshi.ATMexit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
