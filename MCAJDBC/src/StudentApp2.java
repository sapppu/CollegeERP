import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class StudentApp2 extends Frame implements ActionListener {

    Label l1, l2, l3, l4;
    TextField tf1, tf2, tf3;
    Checkbox cb1, cb2;
    CheckboxGroup cg;
    Button bt1, bt2, bt3;
    TextArea ta;

    Connection con;

    StudentApp2() {

        setLayout(null);

        l1 = new Label("Student ID");
        l2 = new Label("Student Name");
        l3 = new Label("Gender");
        l4 = new Label("Update Query");

        tf1 = new TextField();
        tf2 = new TextField();
        tf3 = new TextField();

        cg = new CheckboxGroup();
        cb1 = new Checkbox("Male", cg, false);
        cb2 = new Checkbox("Female", cg, false);

        bt1 = new Button("Save");
        bt2 = new Button("View");
        bt3 = new Button("Submit");

        ta = new TextArea();

        l1.setBounds(50, 50, 100, 20);
        tf1.setBounds(170, 50, 150, 20);

        l2.setBounds(50, 80, 100, 20);
        tf2.setBounds(170, 80, 150, 20);

        l3.setBounds(50, 110, 100, 20);
        cb1.setBounds(170, 110, 60, 20);
        cb2.setBounds(240, 110, 70, 20);

        bt1.setBounds(50, 150, 80, 30);
        bt2.setBounds(150, 150, 80, 30);

        l4.setBounds(400, 50, 100, 20);
        tf3.setBounds(400, 75, 250, 25);
        bt3.setBounds(470, 110, 80, 30);

        ta.setBounds(50, 200, 300, 120);

        add(l1);
        add(tf1);
        add(l2);
        add(tf2);
        add(l3);
        add(cb1);
        add(cb2);
        add(bt1);
        add(bt2);
        add(l4);
        add(tf3);
        add(bt3);
        add(ta);

        bt1.addActionListener(this);
        bt2.addActionListener(this);
        bt3.addActionListener(this);

        setTitle("Student table");
        setSize(700, 400);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    if (con != null)
                        con.close();
                } catch (Exception ex) {
                }
                dispose();
            }
        });

        try {
            String url = "jdbc:mysql://localhost:3306/MITWPU";
            String user = "root";
            String pass = "sappu0";

            //Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {

        if (con == null) {
            ta.setText("Database not connected");
            return;
        }

        try {
            Statement st = con.createStatement();

            if (e.getSource() == bt1) {
                int id = Integer.parseInt(tf1.getText());
                String name = tf2.getText();
                String gender = cg.getSelectedCheckbox().getLabel();

                PreparedStatement ps = con.prepareStatement("insert into student2 values(?,?,?)");
                ps.setInt(1, id);
                ps.setString(2, name);
                ps.setString(3, gender);
                ps.executeUpdate();
                ta.setText("Record Inserted");
            }

            if (e.getSource() == bt2) {
                ta.setText("");
                ResultSet rs = st.executeQuery("select * from student2");
                while (rs.next()) {
                    ta.append(
                            rs.getInt(1) + "  " +
                                    rs.getString(2) + "  " +
                                    rs.getString(3) + "\n"
                    );
                }
            }

            if (e.getSource() == bt3) {
                String query = tf3.getText();
                int rows = st.executeUpdate(query);
                ta.setText(rows + " record(s) updated");
            }

        } catch (Exception ex) {
            ta.setText(ex.toString());
        }
    }

    public static void main(String args[]) {
        new StudentApp2();
    }
}
