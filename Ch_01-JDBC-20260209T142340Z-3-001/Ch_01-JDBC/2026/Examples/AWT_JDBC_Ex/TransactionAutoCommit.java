/* When we don't want one statement to take effect unless 
 * another statement execute successfully executed
 * e.g updating SALES field without updating TOTAL field 
 * will lead to inconsistent result
 * 
 * To ensure both the action occurs use concept of 
 * "transaction": A set of one or more statement that execute
 * together known as transaction
 * 
 * For that purpose we need to disable the default "auto-commit" mode: */

package AWT_JDBC_Ex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionAutoCommit 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		Connection c= null;
		Statement st = null;
		ResultSet r,r1;
		
		try
		{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
		
		}
		catch (java.lang.ClassNotFoundException e1)
		{
			System.out.println(e1.getMessage());
		}
		
		try
		{
			c=DriverManager.getConnection("jdbc:mysql://localhost:3306/SOCS12","root","root123");  
			st=c.createStatement(); 
			r= st.executeQuery("SELECT TEA_NAME, PRICE FROM TEATABLE");  
			
			while(r.next()) 
			{
				String s = r.getString("TEA_NAME");
				float p = r.getFloat("PRICE");
				System.out.println(s + " " + p);  
			}
		}
		
		catch(Exception e)
		{ 
			System.out.println("Sql Exeception Error"+ e.getMessage());
		}		 
			
		try
		{
			c.setAutoCommit(false);
			PreparedStatement ps = c.prepareStatement("UPDATE TEATABLE SET SALES = SALES+? WHERE TEA_NAME LIKE ?");
			ps.setInt(1,200);
			ps.setString(2,"GoldenTea");
			ps.executeUpdate();
			PreparedStatement p = c.prepareStatement("UPDATE TEATABLE SET TOTAL = TOTAL+ ? WHERE TEA_NAME LIKE ?");
			p.setInt(1,200);
			p.setString(2,"GoldenTea");
			p.executeUpdate();
			c.commit();
			c.setAutoCommit(true);
		}
		
		catch(Exception e)
		{ 
			System.out.println("Sql Exeception Error"+ e.getMessage());
		}
		
		try
		{
			r1= st.executeQuery("SELECT TEA_NAME, SALES,TOTAL FROM TEATABLE");
			
			while(r1.next())
			{
				String s = r1.getString("TEA_NAME");
				int p = r1.getInt("SALES");
				int q = r1.getInt("TOTAL");
				System.out.println(s + " " + p +" " + q);
			}
		}
		catch(SQLException e)
		{ 
			System.out.println("Sql Exeception Error"+ e.getMessage());
		}
			
	}	
		
}




