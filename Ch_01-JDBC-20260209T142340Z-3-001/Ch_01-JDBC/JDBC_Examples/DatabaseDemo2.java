import java.sql.*;
import java.io.*;

class DatabaseDemo2
{
	public static void main(String [] args)throws Exception
	{


		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

		Connection con=DriverManager.getConnection("Jdbc:Odbc:ashok");

		Statement stmt=con.createStatement();


		System.out.print("For update fired query  : ");
				String str1=br.readLine();


				int up=stmt.executeUpdate(str1);
				System.out.println("No of recored updated = "+up);


		System.out.print("Fired the query  : ");
		String str=br.readLine();

		ResultSet rs=stmt.executeQuery(str);

		System.out.println("\tNumber\tName\t\tSalary\tJoining Date\n");
		while(rs.next())
		{
			System.out.print("\t"+rs.getInt(1));
			System.out.print("\t"+rs.getString(2));
			//System.out.print("\t\t"+rs.getInt(3));
			//System.out.print("\t"+rs.getDate(4));
			System.out.println("\n");
		}

	}
}

