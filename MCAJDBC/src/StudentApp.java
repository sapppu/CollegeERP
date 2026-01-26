import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class StudentApp extends JFrame {

    JTextField tf1, tf2, tf3;
    JButton btn;

    StudentApp() {

        setLayout(new GridLayout(4, 2, 5, 5));

        add(new JLabel("RNo:"));
        tf1 = new JTextField();
        add(tf1);

        add(new JLabel("SName:"));
        tf2 = new JTextField();
        add(tf2);

        add(new JLabel("Per:"));
        tf3 = new JTextField();
        add(tf3);

        btn = new JButton("Show");
        add(btn);

        btn.addActionListener(e -> showRecord());

        setTitle("Student Record");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    void showRecord() {
        String url = "jdbc:mysql://localhost:3306/Student11?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "sappu0";

        String sql = "SELECT * FROM Student LIMIT 1";

        try (
                Connection con = DriverManager.getConnection(url, user, password);
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)
        ) {
            if (rs.next()) {
                tf1.setText(rs.getInt("Rno") + "");
                tf2.setText(rs.getString("Sname"));
                tf3.setText(rs.getFloat("Per") + "");
            } else {
                JOptionPane.showMessageDialog(this, "No records found");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new StudentApp();
    }
}
