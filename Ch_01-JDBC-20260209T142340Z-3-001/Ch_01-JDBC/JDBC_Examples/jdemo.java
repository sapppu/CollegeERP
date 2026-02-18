import java.sql.*;
class jdemo
{
	public static void main(String[] args) throws ClassNotFoundException,SQLException,Exception
	{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");	
		Connection con=DriverManager.getConnection("jdbc:odbc:mydsn");
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from emp11");
		while(rs.next())
		{
			System.out.println(rs.getInt(1)+" "+rs.getString(2));
		}//while
		con.close();
	}//main
}//class