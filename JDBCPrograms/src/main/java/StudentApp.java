import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class StudentApp extends JFrame {
    JTextField tf1, tf2, tf3;
    JButton btn;
    StudentApp() {
        setLayout(new GridLayout(4, 2));
        add(new JLabel("RNo:")); tf1 = new JTextField(); add(tf1);
        add(new JLabel("SName:")); tf2 = new JTextField(); add(tf2);
        add(new JLabel("Per:")); tf3 = new JTextField(); add(tf3);
        btn = new JButton("Show"); add(btn);
        btn.addActionListener(e -> showRecord());
        setSize(300, 200); setVisible(true);
    }
    void showRecord() {
        try (Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/student_db", "postgres", "sappu");
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM Student LIMIT 1")) {
            if (rs.next()) {
                tf1.setText(rs.getString(1));
                tf2.setText(rs.getString(2));
                tf3.setText(rs.getString(3));
            }
        } catch (Exception ex) { ex.printStackTrace(); }
    }
    public static void main(String[] args) { new StudentApp(); }
}