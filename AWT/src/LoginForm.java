import java.awt.*;
import java.awt.event.*;

class LoginForm extends Frame implements ActionListener {
    Label l1, l2, msg;
    TextField tf1, tf2;
    Button login;

    LoginForm() {
        setLayout(new FlowLayout());

        l1 = new Label("Username:");
        l2 = new Label("Password:");

        tf1 = new TextField(20);
        tf2 = new TextField(20);
        tf2.setEchoChar('*');

        login = new Button("Login");
        login.addActionListener(this);

        msg = new Label("");

        add(l1); add(tf1);
        add(l2); add(tf2);
        add(login);
        add(msg);

        setSize(300, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (tf1.getText().equals("admin") && tf2.getText().equals("123")) {
            msg.setText("Login Success!");
        } else {
            msg.setText("Invalid Credentials");
        }
    }

    public static void main(String[] args) {
        new LoginForm();
    }
}
