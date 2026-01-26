import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;

public class InsertFromCMD {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/Production";
        String user = "root";
        String password = "sappu0";

        String insertSQL = "INSERT INTO prod (pid, pname) VALUES (?, ?)";
        String selectSQL = "SELECT * FROM prod";

        try (
                Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement insertPs = conn.prepareStatement(insertSQL);
                Statement stmt = conn.createStatement();
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in))
        ) {

            System.out.print("Enter product id: ");
            int pid = Integer.parseInt(br.readLine());

            System.out.print("Enter product name: ");
            String pname = br.readLine();


            insertPs.setInt(1, pid);
            insertPs.setString(2, pname);
            insertPs.executeUpdate();

            System.out.println("\nData inserted successfully\n");

            ResultSet rs = stmt.executeQuery(selectSQL);

            System.out.println("----- PRODUCT TABLE -----");
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
