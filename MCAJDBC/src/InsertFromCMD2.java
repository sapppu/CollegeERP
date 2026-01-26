import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;

public class InsertFromCMD2 {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/Production";
        String user = "root";
        String password = "sappu0";

        String selectSQL = "SELECT * FROM prod";

        try (
                Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement();
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in))
        ) {

            System.out.println("Enter SQL INSERT command:");
            String insertSQL = br.readLine();   // read full SQL

            stmt.executeUpdate(insertSQL);

            ResultSet rs = stmt.executeQuery(selectSQL);

            while (rs.next()) {
                System.out.println(
                        rs.getInt("pid") + "  " +
                                rs.getString("pname")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
