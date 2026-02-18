package JDBC_ALL_DEMO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC_Create {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		Connection con;
		Statement stmt;
		String CreateString;
		CreateString = "create table TEATABLE(TEA_NAME VARCHAR(30),SUPP_ID INT ,PRICE INT ,SALES INT ,TOTAL INT)";
				//"CREATE TABLE TEATABLE (TEA_NAME VARCHAR2(30),SUPP_ID NUMBER(6),PRICE NUMBER(6,2),SALES NUMBER(6,2),TOTAL NUMBER(6,2))";
		
			try
			{  
				Class.forName("com.mysql.cj.jdbc.Driver");  // To loading MYsql JDBC Driver
			
			}
			catch (java.lang.ClassNotFoundException e1)
			{
				System.out.println(e1.getMessage());
			}
		
			try
			{
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/SOCS12","root","root123");  
				// Establishing the Connection: here SOCS is database name, root is username and password is root123  
				stmt=con.createStatement(); // To execute sql statement creating object of statement
				stmt.executeUpdate(CreateString);  // execute result  create object of Resulset
				stmt.close();
				con.close();
			}	
			
			catch(SQLException ex)
			{ 
				System.out.println("Sql Exeception Error"+ ex.getMessage());
			}  
	}

}
