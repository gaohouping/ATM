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
                System.out.println("���������룺 "); 
                userpass=input.nextInt();    
             if(userpass==s_pass){
            	 ATMwelcome(s_pass,s_id,s_money,s_name,s_type);           	 
             					}else{ 
                                   System.out.println("������������������������롣");
                                   if(i==3){
                                       System.out.println("�Բ��������������ﵽ3�Σ������̣������������Ч֤������̨�������������");

              							 	        							
              							stmt.executeUpdate("UPDATE userinfo SET tunka = 110  where id="+s_id+"");//��������         	        	            
 				 
                                      zhuchengxu();                                     
                                   }
             					}
                }
            
        }
        
        /*��ӭ����*/
        @SuppressWarnings("deprecation")
		public void ATMwelcome(int s_pass,int s_id,int s_money,String s_name,String s_type) throws ClassNotFoundException, SQLException{     //��ӭ����
        	String admin="admin";
        	String xyk="xyk";
        	String jjk="jjk";
        	String s_guanxi="";
        	if(admin.equals(s_type))
        	{
        		s_guanxi="����Ա";
        	}
        	if(xyk.equals(s_type))
        	{
        		s_guanxi="���ÿ�";
        	}
        	if(jjk.equals(s_type))
        	{
        		s_guanxi="��ǿ�";
        	}
        	System.out.println("�𾴵�"+s_name+"�û�������"+s_guanxi+",��ӭʹ�ñ�ϵͳ");   
        	System.out.println("��ѡ������Ҫ�Ĳ�����\n1��ȡ�� 2����ѯ\n3����� 4��ת��\n5���˻��굥��ѯ\n6���˿�"); 
              	ATm st = new ATm();
              
              	st.start(); //��ʼ90����ʱ
             
              	
              	
              	
              	
              	
				if(admin.equals(s_type))
				{
				
				   System.out.println("�����Խ������Աģʽ");
				   System.out.println("��  ��7��  ����");
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
        
        
        /*ȡ���ӳ���*/
        @SuppressWarnings("deprecation")
		public void ATMget(int s_pass,int s_id,int s_money,String s_name,String s_type) throws ClassNotFoundException{ 			
        	int getmoney=0;  
        	String xyk="xyk";
        	System.out.println("����������Ҫ�Ľ�"); 
        	   getmoney=input.nextInt(); 
        	   if(xyk.equals(s_type)){
            	   if(getmoney%100==0){ 
            	               s_money=s_money-getmoney; 
            	               System.out.println("��������������Ϊ��"+s_money+"Ԫ");
            	            System.out.println("���Ƿ�Ҫ��������������1(����)/2(��ӡ�굥)/3(�˳�)");
            	        	yanshi stt=new yanshi();
                      	 	stt.start();//��ʼ120����ʱ
            	          change = input.nextInt();
            	          stt.stop();
    						try {
    							 Connection con;
    		        	          con = DriverManager.getConnection("jdbc:odbc:ATM","sa","");
    							Statement stmt=con.createStatement();
    							String text="ȡ����Ϊ"+getmoney+"";
    							ResultSet rs=null; 	
    							rs=stmt.executeQuery("select max(id2) from xiangdan");
    							rs.next();
    							int s_id2=rs.getInt(1);
    							s_id2=s_id2+1;
    							stmt.executeUpdate("INSERT INTO xiangdan (id,text,id2,time) "    //�굥��¼
    				            		+ "VALUES('"+s_id+"','"+text+"',"+s_id2+",getdate())");
    							
    	        	            stmt.executeUpdate("UPDATE userinfo SET money = "+s_money+" where id="+s_id+"");//�������ݿ���
    	        	            if(change==1){
    	        	            	ATMwelcome(s_pass,s_id,s_money,s_name,s_type); 
    	        	            }else if(change==2){
    	        	            	System.out.println("�굥�Ѿ���ӡ�����úã���");
    	        	            	ATMwelcome(s_pass,s_id,s_money,s_name,s_type); 
    	        	            }else if(change==3){
    	        	            	ATMexit();
    							}
    						} catch (SQLException e) {
    							e.printStackTrace();
    						}      				 
            	}else{ 
            	     System.out.println("������100��������"); 
            	     }
            	}

        	   else if(s_money>=getmoney){
        	   if(getmoney%100==0){ 
        	               s_money=s_money-getmoney; 
        	               System.out.println("��������������Ϊ��"+s_money+"Ԫ");
        	            System.out.println("���Ƿ�Ҫ��������������1(����)/2(��ӡ�굥)/3(�˳�)");
        	        	yanshi stt=new yanshi();
                  	 	stt.start();//��ʼ120����ʱ
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
							String text="ȡ����Ϊ"+getmoney+"";
							stmt.executeUpdate("INSERT INTO xiangdan (id,text,id2,time) "    //�굥��¼
				            		+ "VALUES('"+s_id+"','"+text+"',"+s_id2+",getdate())");
							
	        	            stmt.executeUpdate("UPDATE userinfo SET money = "+s_money+" where id="+s_id+"");//�������ݿ���

	        	            if(change==1){
	        	            	ATMwelcome(s_pass,s_id,s_money,s_name,s_type); 
	        	            }else if(change==2){
	        	            	xiangdan2(s_pass,s_id,s_money,s_name,s_type);
	        	            	System.out.println("�굥�Ѿ���ӡ�����úã���");
	        	            	ATMwelcome(s_pass,s_id,s_money,s_name,s_type); 
	        	            }else if(change==3){
	        	            	ATMexit();
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}      				 
        	}else{ 
        	     System.out.println("������100��������"); 
        	     }
        	}else{ 
        	     System.out.println("���Ŀ������㣡������ɽ��ף�");
        	 }
        	}
        @SuppressWarnings("deprecation")
		public void ATMselect(int s_pass,int s_id,int s_money,String s_name,String s_type) throws ClassNotFoundException, SQLException{    							//��ѯ�ӳ���
        	  System.out.println("���ʻ������Ϊ��"+s_money+"Ԫ"); 
        	  System.out.println("���Ƿ�Ҫ������������ 1(����)/2(�˳�)"); 
        		yanshi stt=new yanshi();
          	 	stt.start();//��ʼ120����ʱ
        	  change=input.nextInt(); 
        	  stt.stop();
        	  if(change==1){
        	             ATMwelcome(s_pass,s_id,s_money,s_name,s_type); 
        	  
        	  }else if(change==2){ 
        	             ATMexit(); 
        	  }
        	}
        
        /*������*/
        @SuppressWarnings("deprecation")
		public void ATMdeposit(int s_pass,int s_id,int s_money,String s_name,String s_type) throws ClassNotFoundException{	
        	int getmoney=0;   
        	System.out.println("����������Ҫ�Ľ�"); 
        	   getmoney=input.nextInt(); 
        	   s_money=s_money+getmoney;
        	   System.out.println("���ɹ����˻����Ϊ"+s_money);
        	   System.out.println("���Ƿ�Ҫ��������������1(����)/2(��ӡ�굥)/3(�˳�)");
        		yanshi stt=new yanshi();
          	 	stt.start();//��ʼ120����ʱ
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
  	            
  	          String text="�����Ϊ"+getmoney+"";
  	          stmt.executeUpdate("INSERT INTO xiangdan (id,text,id2,time) "    //�굥��¼
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
            	System.out.println("�굥�Ѿ���ӡ�����úã���");
            	ATMwelcome(s_pass,s_id,s_money,s_name,s_type); 
  	          }
				} catch (SQLException e) {
					e.printStackTrace();
				}      		
        }
        
        
        /*ת���ӳ���*/
        @SuppressWarnings("deprecation")
		public void ATMtransfer(int s_pass,int s_id,int s_money,String s_name,String s_type) throws ClassNotFoundException{
        	int getmoney=0;  
        	int id2=0;
        	System.out.println("����������Ҫת�˵Ľ�"); 
        	   getmoney=input.nextInt(); 
        	   System.out.println("����������Ҫת�˵��˻���"); 
        	   id2=input.nextInt();
        	if(s_money>=getmoney){
 
        	               s_money=s_money-getmoney; 
        	               System.out.println("��������������Ϊ��"+s_money+"Ԫ");        	         
        	            System.out.println("���Ƿ�Ҫ��������������1(����)/2(��ӡ�굥)/3(�˳�)"); 
        	        	yanshi stt=new yanshi();
                  	 	stt.start();//��ʼ120����ʱ
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
	        	            
	        	            String text="ת�˵������˻���"+id2+"  ���Ϊ ��"+getmoney+"";
	        	  	          stmt.executeUpdate("INSERT INTO xiangdan (id,text,id2,time) "    //�굥��¼
	        		            		+ "VALUES('"+s_id+"','"+text+"',"+s_id2+",getdate())");
	        	  	          
	        	  	          
	        	            if(change==1){
	        	            	ATMwelcome(s_pass,s_id,s_money,s_name,s_type); 
	        	            }else if(change==3){
	        	            	ATMexit();
							}
	        	            if(change==2){
	        	  	        	xiangdan2(s_pass,s_id,s_money,s_name,s_type);
	        	            	System.out.println("�굥�Ѿ���ӡ�����úã���");
	        	            	ATMwelcome(s_pass,s_id,s_money,s_name,s_type); 
	        	  	          }
						} catch (SQLException e) {
							e.printStackTrace();
						}      				 
        	}else{ 
        	     System.out.println("���Ŀ������㣡������ɽ��ף�");
        	 }
        }
        
        /*�˳�����*/
        public void ATMexit() throws ClassNotFoundException, SQLException{                       
            System.out.println("ллʹ�ã�");
            System.exit(0);
          }
        
        
        /*����Ա�û�ϵͳ*/
        @SuppressWarnings("deprecation")
		public void ATMadmin(int s_pass,int s_id,int s_money,String s_name,String s_type) throws ClassNotFoundException{
        	System.out.println("����һ��Ҫ�������¿�");
        	int newid=0;
        	newid=input.nextInt();
        	System.out.println("��ȡ���֤��Ϣ����");
        	String newname=input.next();
        	System.out.println("�����¿�����");
        	int newpass=input.nextInt();
        	System.out.println("�����¿����� admin��xyk��jjk");
        	String newtype=input.next();
        	System.out.println("��������");
        	int newmoney=input.nextInt();
        	try {
				 Connection con;
				con = DriverManager.getConnection("jdbc:odbc:ATM","sa","");
				Statement stmt=con.createStatement();					 
	            stmt.executeUpdate("INSERT INTO userinfo (id,name,password,type,money) "
	            		+ "VALUES('"+newid+"','"+newname+"','"+newpass+"','"+newtype+"','"+newmoney+"')");
	            System.out.println("���Ƿ�Ҫ��������������1(����)/2(�˳�)");
	        	yanshi stt=new yanshi();
          	 	stt.start();//��ʼ120����ʱ
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
        
        /*90����ʱ��������*/
        public void run(){
        	System.out.println("ʣ�����ʱ��");
        	try {
        		for(int i=90;i>=0;i--){
				Thread.sleep(1000);
				System.out.print("  "+i);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	System.out.println("��ʱ��δ���������̣�����ϵ�ͷ���");
        	try {
				ATMexit();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        /*��ӡ�굥�ӳ���*/
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
	            System.out.println("�����˻���" +s_id+ "��" +time+ "ͨ��ATM" +text);
	            }	
	            System.out.println("���Ƿ�Ҫ��������������1(����)/2(�˳�)"); 
        		yanshi stt=new yanshi();
          	 	stt.start();//��ʼ120����ʱ
        	  
  	            change=input.nextInt();
  	            stt.stop();
  	            if(change==1){
  	            	ATMwelcome(s_pass,s_id,s_money,s_name,s_type); 
  	            }else {
  	            	ATMexit();
					}
        }
        /*��ӡ�굥�ӳ���2*/
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
	            System.out.println("�����˻���" +s_id+ "��" +time+ "ͨ��ATM" +text);
	            }	
	            System.out.println("���Ƿ�Ҫ��������������1(����)/2(�˳�)"); 
        		yanshi stt=new yanshi();
          	 	stt.start();//��ʼ120����ʱ
        	  
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
  			  System.out.println("�������п���");
  			  n = new Scanner(System.in);
  			  s_id = n.nextInt();
  		        rs=stmt.executeQuery("select * from userinfo where id="+s_id+"");
  				    rs.next();
  				       s_name=rs.getString("name");
  				       s_pass=rs.getInt("password");
  				       s_type=rs.getString("type");  //��������  admin,xyk,jjk
  				       s_money=rs.getInt("money");
          	         ATm a = new ATm();
    			      	 a.ATMinputpass(s_pass,s_id,s_money,s_name,s_type);	    
        }
      	public static void main(String[] args) throws ClassNotFoundException, SQLException{
      		ATm b = new ATm();
      		b.zhuchengxu();
      	  
		}
 } 