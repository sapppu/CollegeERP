//import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//import java.awt.event.ActionListener;
import java.sql.*;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;

class frm extends Frame implements ActionListener {
    Label l1, l2, l3;
    Button b1, b2,b3; //
    TextField tf1, tf2, tf3;
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    public frm() {
        setLayout(new FlowLayout());

        l1 = new Label("EMP ID");
        l2 = new Label("EMP Name");
        l3 = new Label("EMP Age");

        b1 = new Button("View");
        b2 = new Button("Add");
        b2 = new Button("Update");
        tf1 = new TextField(10);
        tf2 = new TextField(10);
        tf3 = new TextField(10);

        add(l1);
        add(tf1);
        add(l2);
        add(tf2);
        add(l3);
        add(tf3);
        add(b1);
        add(b2);
        add(b3);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        try {
            String url = "jdbc:mysql://localhost:3306/MITWPU";
            conn = DriverManager.getConnection(url, "root", "sappu0");
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == b1) {
            try {
                ps = conn.prepareStatement("select * from emp where eid=?");
                ps.setString(1, tf1.getText());
                rs = ps.executeQuery();
                if (rs.next()) {
                    tf2.setText(rs.getString(2));
                    tf3.setText(rs.getString(3));
                }
            } catch (Exception ex) { System.out.println(ex); }
        }


        if(ae.getSource() == b3) {
            try {
                ps = conn.prepareStatement("update emp set age=? where eid=? && ename=?");
                ps.setString(1, tf1.getText());
                ps.setString(2, tf2.getText());
                ps.setString(3, tf3.getText());

                int result = ps.executeUpdate();
                if(result > 0) {
                    System.out.println("Record updated Successfully!");
                    tf1.setText(""); tf2.setText(""); tf3.setText("");
                }
            } catch (Exception ex) { System.out.println(ex);}
        }


        if(ae.getSource() == b2) {
            try {
                ps = conn.prepareStatement("insert into emp (eid, ename, age) values (?, ?, ?)");
                ps.setString(1, tf1.getText());
                ps.setString(2, tf2.getText());
                ps.setString(3, tf3.getText());

                int result = ps.executeUpdate();
                if(result > 0) {
                    System.out.println("Record Added Successfully!");
                    tf1.setText(""); tf2.setText(""); tf3.setText("");
                }
            } catch (Exception ex) { System.out.println(ex); }
        }
    }
}
public class view_rec {
    public static void main(String[] args) {
        frm f = new frm();
        f.setSize(500,500);
        f.setVisible(true);
    }
}