package ATM;

import java.sql.*;

public class Atm {
	public static void main(String[] args) throws SQLException, ClassNotFoundException{
		
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			  Connection con= DriverManager.getConnection("jdbc:odbc:ATM","sa","");
			  ResultSet rs=null; 
			  Statement stmt=con.createStatement();
		        rs=stmt.executeQuery("select * from userinfo");
				    while (rs.next()){
				       String id=rs.getString("password");
				       System.out.println(id);
				    }
				    con.close();
	}
}
