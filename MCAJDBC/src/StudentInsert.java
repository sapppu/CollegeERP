import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class StudentInsert extends Frame implements ActionListener {

    Label l1, l2, l3;
    TextField t1, t2, t3;
    Button b1;

    Connection con;
    PreparedStatement ps;

    StudentInsert() {

        setTitle("Student Insert Form");

        l1 = new Label("Roll No:");
        l2 = new Label("Name:");
        l3 = new Label("Age:");

        t1 = new TextField();
        t2 = new TextField();
        t3 = new TextField();

        b1 = new Button("Insert");

        setLayout(new GridLayout(4, 2, 10, 10));

        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(l3);
        add(t3);
        add(new Label());
        add(b1);

        b1.addActionListener(this);

        setSize(300, 200);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/MITWPU", "root", "sappu0");
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public void actionPerformed(ActionEvent e) {
        try {
            int rno = Integer.parseInt(t1.getText());
            String name = t2.getText();
            int age = Integer.parseInt(t3.getText());

            ps = con.prepareStatement("insert into Student values(?,?,?)");
            ps.setInt(1, rno);
            ps.setString(2, name);
            ps.setInt(3, age);

            ps.executeUpdate();

            System.out.println("Record Inserted Successfully!");

            t1.setText("");
            t2.setText("");
            t3.setText("");
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void main(String[] args) {
        new StudentInsert();
    }
}
