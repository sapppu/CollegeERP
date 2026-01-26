import java.sql.*;

public class Demo {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/Production";
        String user = "root";
        String password = "sappu0";

        String insertSQL = "INSERT INTO prod (pid, pname) VALUES (?, ?)";
        String selectSQL = "SELECT * FROM prod";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement insertPs = conn.prepareStatement(insertSQL);
             Statement stmt = conn.createStatement()) {

            insertPs.setInt(1, 20);
            insertPs.setString(2, "Speaker");
            insertPs.executeUpdate();

            ResultSet rs = stmt.executeQuery(selectSQL);

            while (rs.next()) {
                System.out.println(
                        rs.getInt("pid") + " " +
                                rs.getString("pname")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
