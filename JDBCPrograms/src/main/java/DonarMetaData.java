import java.sql.*;
public class DonarMetaData {
    public static void main(String[] args) throws Exception {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/donor", "postgres", "sappu");
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM DONOR");
        ResultSetMetaData rsmd = rs.getMetaData();
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            System.out.println("Column: " + rsmd.getColumnName(i) + " | Type: " + rsmd.getColumnTypeName(i));
        }
        con.close();
    }
}