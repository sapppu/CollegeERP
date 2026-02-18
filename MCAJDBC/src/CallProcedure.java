import java.sql.*;

public class CallProcedure {
    public static void main(String[] args) {

        Connection con = null;
        CallableStatement cs = null;

        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/MITWPU",
                    "root",
                    "sappu0"
            );

            // 3. Prepare CallableStatement
            cs = con.prepareCall("{CALL increment_age(?, ?, ?)}");

            cs.setInt(1, 11);
            cs.setDouble(2, 2.0);

            cs.registerOutParameter(3, Types.DOUBLE);

            cs.execute();

            double updatedAge = cs.getDouble(3);

            System.out.println("Updated Age: " + updatedAge);

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (cs != null) cs.close();
                if (con != null) con.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
