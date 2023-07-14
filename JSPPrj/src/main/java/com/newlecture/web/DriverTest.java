package com.newlecture.web;

import java.sql.*;

public class DriverTest {
	public static void main(String[] args) {
		 Connection con = null;
		 
	        try{
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            con=DriverManager.getConnection(
	                    "jdbc:mysql://localhost:3306/mydb?serverTimezone=UTC", // DB URL
	                    "root", "1234");  // USER_NAME과 PASSWORD
	            System.out.println("Success");
	        }
	        catch(SQLException ex){ 
	            System.out.println("SQLException" + ex);
	            ex.printStackTrace();
	        }
	        catch(Exception ex){ 
	            System.out.println("Exception:" + ex);
	            ex.printStackTrace();
	        }
	}

}
