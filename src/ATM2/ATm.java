package ATM2;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ATm extends Thread{
        Scanner input=new Scanner(System.in); 
        int change;
		private Scanner n; 
        
        
        public void ATMinputpass(int s_pass,int s_id,int s_money,String s_name,String s_type) throws ClassNotFoundException, SQLException{
        	Connection con;
	         con = DriverManager.getConnection("jdbc:odbc:ATM","sa","");
			Statement stmt=con.createStatement();
			int userpass;
        	for(int i=1;i>=0;i++) { 
                System.out.println("请输入密码： "); 
                userpass=input.nextInt();    
             if(userpass==s_pass){
            	 ATMwelcome(s_pass,s_id,s_money,s_name,s_type);           	 
             					}else{ 
                                   System.out.println("您输入的密码有误！请重新输入。");
                                   if(i==3){
                                       System.out.println("对不起！密码输错次数达到3次，卡已吞，请带上您的有效证件到柜台办理相关手续！");

              							 	        							
              							stmt.executeUpdate("UPDATE userinfo SET tunka = 110  where id="+s_id+"");//更新数据         	        	            
 				 
                                      zhuchengxu();                                     
                                   }
             					}
                }
            
        }
        
        /*欢迎程序*/
        @SuppressWarnings("deprecation")
		public void ATMwelcome(int s_pass,int s_id,int s_money,String s_name,String s_type) throws ClassNotFoundException, SQLException{     //欢迎界面
        	String admin="admin";
        	String xyk="xyk";
        	String jjk="jjk";
        	String s_guanxi="";
        	if(admin.equals(s_type))
        	{
        		s_guanxi="管理员";
        	}
        	if(xyk.equals(s_type))
        	{
        		s_guanxi="信用卡";
        	}
        	if(jjk.equals(s_type))
        	{
        		s_guanxi="借记卡";
        	}
        	System.out.println("尊敬的"+s_name+"用户！您是"+s_guanxi+",欢迎使用本系统");   
        	System.out.println("请选择您想要的操作：\n1、取款 2、查询\n3、存款 4、转账\n5、账户详单查询\n6、退卡"); 
              	ATm st = new ATm();
              
              	st.start(); //开始90秒延时
             
              	
              	
              	
              	
              	
				if(admin.equals(s_type))
				{
				
				   System.out.println("您可以进入管理员模式");
				   System.out.println("按  “7”  进入");
				   change=input.nextInt();
				   st.stop();
				   if(change==7)
				   {
				   ATMadmin(s_pass, s_id, s_money, s_name, s_type);
				   }
				   else if(change==1){ 
      	             ATMget(s_pass,s_id,s_money,s_name,s_type); 
      	             }
      	else if(change==2){
      	             ATMselect(s_pass,s_id,s_money,s_name,s_type); 
      	}
      	else if(change==3){ 
     	             ATMdeposit(s_pass,s_id,s_money,s_name,s_type);
      	
      	 }else if(change==4){ 
  	             ATMtransfer(s_pass,s_id,s_money,s_name,s_type); 
      	 }
      	 else if(change==6){
      		 ATMexit();
      	 }
      	 else if(change==5){
      		xiangdan(s_pass,s_id,s_money,s_name,s_type);
      	 }
				}
   				 
        	  
        	  
				change=input.nextInt(); 
				st.stop();
				   if(change==1){ 
	      	             ATMget(s_pass,s_id,s_money,s_name,s_type); 
	      	             }
	      	else if(change==2){
	      	             ATMselect(s_pass,s_id,s_money,s_name,s_type); 
	      	}
	      	else if(change==3){ 
	     	             ATMdeposit(s_pass,s_id,s_money,s_name,s_type);
	      	
	      	 }else if(change==4){ 
	  	             ATMtransfer(s_pass,s_id,s_money,s_name,s_type); 
	      	 }
	      	 else if(change==6){
	      		 ATMexit();
	      	 }
	      	 else if(change==5){
	      		xiangdan(s_pass,s_id,s_money,s_name,s_type);
	      	 }
        	  
        	} 
        
        
        /*取款子程序*/
        @SuppressWarnings("deprecation")
		public void ATMget(int s_pass,int s_id,int s_money,String s_name,String s_type) throws ClassNotFoundException{ 			
        	int getmoney=0;  
        	String xyk="xyk";
        	System.out.println("请输入您需要的金额："); 
        	   getmoney=input.nextInt(); 
        	   if(xyk.equals(s_type)){
            	   if(getmoney%100==0){ 
            	               s_money=s_money-getmoney; 
            	               System.out.println("交易完成您的余额为："+s_money+"元");
            	            System.out.println("您是否要继续其他操作！1(继续)/2(打印详单)/3(退出)");
            	        	yanshi stt=new yanshi();
                      	 	stt.start();//开始120秒延时
            	          change = input.nextInt();
            	          stt.stop();
    						try {
    							 Connection con;
    		        	          con = DriverManager.getConnection("jdbc:odbc:ATM","sa","");
    							Statement stmt=con.createStatement();
    							String text="取款金额为"+getmoney+"";
    							ResultSet rs=null; 	
    							rs=stmt.executeQuery("select max(id2) from xiangdan");
    							rs.next();
    							int s_id2=rs.getInt(1);
    							s_id2=s_id2+1;
    							stmt.executeUpdate("INSERT INTO xiangdan (id,text,id2,time) "    //详单记录
    				            		+ "VALUES('"+s_id+"','"+text+"',"+s_id2+",getdate())");
    							
    	        	            stmt.executeUpdate("UPDATE userinfo SET money = "+s_money+" where id="+s_id+"");//更新数据库金额
    	        	            if(change==1){
    	        	            	ATMwelcome(s_pass,s_id,s_money,s_name,s_type); 
    	        	            }else if(change==2){
    	        	            	System.out.println("详单已经打印，请拿好！！");
    	        	            	ATMwelcome(s_pass,s_id,s_money,s_name,s_type); 
    	        	            }else if(change==3){
    	        	            	ATMexit();
    							}
    						} catch (SQLException e) {
    							e.printStackTrace();
    						}      				 
            	}else{ 
            	     System.out.println("请输入100的整数！"); 
            	     }
            	}

        	   else if(s_money>=getmoney){
        	   if(getmoney%100==0){ 
        	               s_money=s_money-getmoney; 
        	               System.out.println("交易完成您的余额为："+s_money+"元");
        	            System.out.println("您是否要继续其他操作！1(继续)/2(打印详单)/3(退出)");
        	        	yanshi stt=new yanshi();
                  	 	stt.start();//开始120秒延时
        	          change = input.nextInt();
        	          stt.stop();
        	          System.out.println("");
						try {
							 Connection con;
		        	          con = DriverManager.getConnection("jdbc:odbc:ATM","sa","");
							Statement stmt=con.createStatement();
							ResultSet rs=null; 	
							rs=stmt.executeQuery("select max(id2) from xiangdan");
							rs.next();
							int s_id2=rs.getInt(1);
							s_id2=s_id2+1;
							String text="取款金额为"+getmoney+"";
							stmt.executeUpdate("INSERT INTO xiangdan (id,text,id2,time) "    //详单记录
				            		+ "VALUES('"+s_id+"','"+text+"',"+s_id2+",getdate())");
							
	        	            stmt.executeUpdate("UPDATE userinfo SET money = "+s_money+" where id="+s_id+"");//更新数据库金额

	        	            if(change==1){
	        	            	ATMwelcome(s_pass,s_id,s_money,s_name,s_type); 
	        	            }else if(change==2){
	        	            	xiangdan2(s_pass,s_id,s_money,s_name,s_type);
	        	            	System.out.println("详单已经打印，请拿好！！");
	        	            	ATMwelcome(s_pass,s_id,s_money,s_name,s_type); 
	        	            }else if(change==3){
	        	            	ATMexit();
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}      				 
        	}else{ 
        	     System.out.println("请输入100的整数！"); 
        	     }
        	}else{ 
        	     System.out.println("您的卡上余额不足！不能完成交易！");
        	 }
        	}
        @SuppressWarnings("deprecation")
		public void ATMselect(int s_pass,int s_id,int s_money,String s_name,String s_type) throws ClassNotFoundException, SQLException{    							//查询子程序
        	  System.out.println("您帐户的余额为："+s_money+"元"); 
        	  System.out.println("您是否要进行其他操作 1(继续)/2(退出)"); 
        		yanshi stt=new yanshi();
          	 	stt.start();//开始120秒延时
        	  change=input.nextInt(); 
        	  stt.stop();
        	  if(change==1){
        	             ATMwelcome(s_pass,s_id,s_money,s_name,s_type); 
        	  
        	  }else if(change==2){ 
        	             ATMexit(); 
        	  }
        	}
        
        /*存款程序*/
        @SuppressWarnings("deprecation")
		public void ATMdeposit(int s_pass,int s_id,int s_money,String s_name,String s_type) throws ClassNotFoundException{	
        	int getmoney=0;   
        	System.out.println("请输入您需要的金额："); 
        	   getmoney=input.nextInt(); 
        	   s_money=s_money+getmoney;
        	   System.out.println("存款成功，账户余额为"+s_money);
        	   System.out.println("您是否要继续其他操作！1(继续)/2(打印详单)/3(退出)");
        		yanshi stt=new yanshi();
          	 	stt.start();//开始120秒延时
        	   try {
					 Connection con;
      	          con = DriverManager.getConnection("jdbc:odbc:ATM","sa","");
					Statement stmt=con.createStatement();	
		   	          ResultSet rs=null; 	
						rs=stmt.executeQuery("select max(id2) from xiangdan");
						rs.next();
						int s_id2=rs.getInt(1);
						s_id2=s_id2+1;					
  	            stmt.executeUpdate("UPDATE userinfo SET money = "+s_money+" where id="+s_id+"");
  	            
  	          String text="存款金额为"+getmoney+"";
  	          stmt.executeUpdate("INSERT INTO xiangdan (id,text,id2,time) "    //详单记录
	            		+ "VALUES('"+s_id+"','"+text+"','"+s_id2+"',getdate())");
  	          
  	            change=input.nextInt();
  	            stt.stop();
  	            if(change==1){
  	            	ATMwelcome(s_pass,s_id,s_money,s_name,s_type); 
  	            }else if(change==3){
  	            	ATMexit();
					}
  	          if(change==2){
  	        	xiangdan2(s_pass,s_id,s_money,s_name,s_type);
            	System.out.println("详单已经打印，请拿好！！");
            	ATMwelcome(s_pass,s_id,s_money,s_name,s_type); 
  	          }
				} catch (SQLException e) {
					e.printStackTrace();
				}      		
        }
        
        
        /*转账子程序*/
        @SuppressWarnings("deprecation")
		public void ATMtransfer(int s_pass,int s_id,int s_money,String s_name,String s_type) throws ClassNotFoundException{
        	int getmoney=0;  
        	int id2=0;
        	System.out.println("请输入您需要转账的金额："); 
        	   getmoney=input.nextInt(); 
        	   System.out.println("请输入您需要转账的账户："); 
        	   id2=input.nextInt();
        	if(s_money>=getmoney){
 
        	               s_money=s_money-getmoney; 
        	               System.out.println("交易完成您的余额为："+s_money+"元");        	         
        	            System.out.println("您是否要继续其他操作！1(继续)/2(打印详单)/3(退出)"); 
        	        	yanshi stt=new yanshi();
                  	 	stt.start();//开始120秒延时
        	          change = input.nextInt();
        	          stt.stop();
						try {
							 Connection con;
		        	          ResultSet rs=null; 
							con = DriverManager.getConnection("jdbc:odbc:ATM","sa","");
							Statement stmt=con.createStatement(); 	
							rs=stmt.executeQuery("select max(id2) from xiangdan");
							rs.next();
							int s_id2=rs.getInt(1);
							s_id2=s_id2+1;
							
	        	            stmt.executeUpdate("UPDATE userinfo SET money = "+s_money+" where id="+s_id+"");
	        	            rs=stmt.executeQuery("select money from userinfo where id="+id2+"");
	        	            rs.next();
	        	            int money2=rs.getInt("money");
	        	            money2=money2+getmoney;
	        	            stmt.executeUpdate("UPDATE userinfo SET money = "+money2+" where id="+id2+"");
	        	            
	        	            String text="转账到银行账户："+id2+"  金额为 ："+getmoney+"";
	        	  	          stmt.executeUpdate("INSERT INTO xiangdan (id,text,id2,time) "    //详单记录
	        		            		+ "VALUES('"+s_id+"','"+text+"',"+s_id2+",getdate())");
	        	  	          
	        	  	          
	        	            if(change==1){
	        	            	ATMwelcome(s_pass,s_id,s_money,s_name,s_type); 
	        	            }else if(change==3){
	        	            	ATMexit();
							}
	        	            if(change==2){
	        	  	        	xiangdan2(s_pass,s_id,s_money,s_name,s_type);
	        	            	System.out.println("详单已经打印，请拿好！！");
	        	            	ATMwelcome(s_pass,s_id,s_money,s_name,s_type); 
	        	  	          }
						} catch (SQLException e) {
							e.printStackTrace();
						}      				 
        	}else{ 
        	     System.out.println("您的卡上余额不足！不能完成交易！");
        	 }
        }
        
        /*退出程序*/
        public void ATMexit() throws ClassNotFoundException, SQLException{                       
            System.out.println("谢谢使用！");
            System.exit(0);
          }
        
        
        /*管理员用户系统*/
        @SuppressWarnings("deprecation")
		public void ATMadmin(int s_pass,int s_id,int s_money,String s_name,String s_type) throws ClassNotFoundException{
        	System.out.println("插入一张要创建的新卡");
        	int newid=0;
        	newid=input.nextInt();
        	System.out.println("读取身份证信息名字");
        	String newname=input.next();
        	System.out.println("输入新卡密码");
        	int newpass=input.nextInt();
        	System.out.println("输入新卡类型 admin、xyk、jjk");
        	String newtype=input.next();
        	System.out.println("输入存款金额");
        	int newmoney=input.nextInt();
        	try {
				 Connection con;
				con = DriverManager.getConnection("jdbc:odbc:ATM","sa","");
				Statement stmt=con.createStatement();					 
	            stmt.executeUpdate("INSERT INTO userinfo (id,name,password,type,money) "
	            		+ "VALUES('"+newid+"','"+newname+"','"+newpass+"','"+newtype+"','"+newmoney+"')");
	            System.out.println("您是否要继续其他操作！1(继续)/2(退出)");
	        	yanshi stt=new yanshi();
          	 	stt.start();//开始120秒延时
	            change=input.nextInt();
	            stt.stop();
	            if(change==1){
	            	ATMadmin(s_pass,s_id,s_money,s_name,s_type); 
	            }else {
	            	ATMexit();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}      		
        }
        
        /*90秒延时计数程序*/
        public void run(){
        	System.out.println("剩余操作时间");
        	try {
        		for(int i=90;i>=0;i--){
				Thread.sleep(1000);
				System.out.print("  "+i);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	System.out.println("长时间未操作卡已吞，请联系客服！");
        	try {
				ATMexit();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        /*打印详单子程序*/
        @SuppressWarnings("deprecation")
		public void xiangdan(int s_pass,int s_id,int s_money,String s_name,String s_type) throws ClassNotFoundException, SQLException{
      
				 Connection con;
   	          ResultSet rs=null; 
				con = DriverManager.getConnection("jdbc:odbc:ATM","sa","");
				Statement stmt=con.createStatement();	
				
	            rs=stmt.executeQuery("select * from xiangdan where id="+s_id+"");
	            while(rs.next()){
	            String text=rs.getString("text");
	            String time=rs.getString("time");
	            System.out.println();	            
	            System.out.println("您的账户：" +s_id+ "在" +time+ "通过ATM" +text);
	            }	
	            System.out.println("您是否要继续其他操作！1(继续)/2(退出)"); 
        		yanshi stt=new yanshi();
          	 	stt.start();//开始120秒延时
        	  
  	            change=input.nextInt();
  	            stt.stop();
  	            if(change==1){
  	            	ATMwelcome(s_pass,s_id,s_money,s_name,s_type); 
  	            }else {
  	            	ATMexit();
					}
        }
        /*打印详单子程序2*/
        @SuppressWarnings("deprecation")
		public void xiangdan2(int s_pass,int s_id,int s_money,String s_name,String s_type) throws ClassNotFoundException, SQLException{
      
				 Connection con;
   	          ResultSet rs=null; 
				con = DriverManager.getConnection("jdbc:odbc:ATM","sa","");
				Statement stmt=con.createStatement();	
				rs=stmt.executeQuery("select max(id2) from xiangdan");
				rs.next();
				int s_id2=rs.getInt(1);
				System.out.println(""+s_id2+"");
	            rs=stmt.executeQuery("select * from xiangdan where id2="+s_id2+"");
	            while(rs.next()){
	            String text=rs.getString("text");
	            String time=rs.getString("time");
	            System.out.println();	            
	            System.out.println("您的账户：" +s_id+ "在" +time+ "通过ATM" +text);
	            }	
	            System.out.println("您是否要继续其他操作！1(继续)/2(退出)"); 
        		yanshi stt=new yanshi();
          	 	stt.start();//开始120秒延时
        	  
  	            change=input.nextInt();
  	            stt.stop();
  	            if(change==1){
  	            	ATMwelcome(s_pass,s_id,s_money,s_name,s_type); 
  	            }else {
  	            	ATMexit();
					}
        }
        
        public void zhuchengxu()throws SQLException, ClassNotFoundException{
        	int s_id=0;
        	  int s_pass=0;
  		  String s_name=null;
  		  String s_type=null;
  		  int s_money=0;
  		  	  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
  			  Connection con= DriverManager.getConnection("jdbc:odbc:ATM","sa","");
  		  	  ResultSet rs=null; 
  			  Statement stmt=con.createStatement();
  			  System.out.println("载入银行卡号");
  			  n = new Scanner(System.in);
  			  s_id = n.nextInt();
  		        rs=stmt.executeQuery("select * from userinfo where id="+s_id+"");
  				    rs.next();
  				       s_name=rs.getString("name");
  				       s_pass=rs.getInt("password");
  				       s_type=rs.getString("type");  //卡的类型  admin,xyk,jjk
  				       s_money=rs.getInt("money");
          	         ATm a = new ATm();
    			      	 a.ATMinputpass(s_pass,s_id,s_money,s_name,s_type);	    
        }
      	public static void main(String[] args) throws ClassNotFoundException, SQLException{
      		ATm b = new ATm();
      		b.zhuchengxu();
      	  
		}
 } 