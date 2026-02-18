import java.sql.*;

public class jdemo1
{
	static Connection con=null;
	public static void main(String[] args)
	{
		try
		{
			// * Loading the Driver class and registering to DriverManager

				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");	
				System.out.println("Driver Loaded ::");

			// * Create a new Connection

				Connection con=DriverManager.getConnection("jdbc:odbc:mydsn1");
				System.out.println("Connection Created ::");

			// * Create Statement

				Statement stmt=con.createStatement();
				System.out.println("Statement Created ::");


			// * executeUpdate()(INSERT , UPDATE , DELETE ,)
		
				int i=stmt.executeUpdate("insert into emp11(Emp_Id,Emp_Name,Emp_Sal) values(6,'Sachin',25000)");
				System.out.println(" " +i+"Record is inserted in to Emp11");

		
			// * executeQuery() (SELECT) and get result
		
				ResultSet rs=stmt.executeQuery("select * from emp11");
				System.out.println("Records Present in the emp11 Tables are ::");
				
				while(rs.next())
				{
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
				}//while
		}//try
		catch(ClassNotFoundException e)
		{
		}
		catch(Exception e)
		{
		}
		finally
		{
			try
			{
				con.close();
			}
			catch(SQLException e)
			{
			}
		}
	}
}