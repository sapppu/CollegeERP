// Insert record into Database
package JDBC_ALL_DEMO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class Insert_JDBC_Demo
{
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException 
	{


		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

		Class.forName("com.mysql.cj.jdbc.Driver");  // To loading MYsql JDBC Driver
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/SOCS","root","root123");  

		Statement stmt=con.createStatement();


		System.out.print("Enter Query For update   : ");
		String str1=br.readLine();


		int up=stmt.executeUpdate(str1);
		System.out.println("No of recored updated = "+up);


		System.out.print("Fired the query  : ");
		String str=br.readLine();

		ResultSet rs=stmt.executeQuery(str);

		System.out.println("\tId\tName\t\tAge\n");
		while(rs.next())
		{
			System.out.print("\t"+rs.getInt(1));
			System.out.print("\t"+rs.getString(2));
			System.out.print("\t"+rs.getInt(3));
			//System.out.print("\t\t"+rs.getInt(3));
			//System.out.print("\t"+rs.getDate(4));
			System.out.println("\n");
		}

	}
}


