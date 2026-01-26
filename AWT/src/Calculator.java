import java.awt.*;
import java.awt.event.*;

class Calculator extends Frame implements ActionListener {

    TextField tf1, tf2, tfRes;
    Button add, sub, mul, div, clr, eq;

    Calculator() {
        setLayout(new FlowLayout());

        Label l1 = new Label("Num 1:");
        tf1 = new TextField(15);

        Label l2 = new Label("Num 2:");
        tf2 = new TextField(15);

        Label l3 = new Label("Result:");
        tfRes = new TextField(15);
        tfRes.setEditable(false);

        add(l1); add(tf1);
        add(l2); add(tf2);
        add(l3); add(tfRes);

        add = new Button("+");
        sub = new Button("-");
        mul = new Button("*");
        div = new Button("/");
        eq = new Button("=");
        clr = new Button("C");

        add(add); add(sub); add(mul); add(div); add(eq); add(clr);

        add.addActionListener(this);
        sub.addActionListener(this);
        mul.addActionListener(this);
        div.addActionListener(this);
        eq.addActionListener(this);
        clr.addActionListener(this);

        setSize(300, 300);
        setTitle("AWT Calculator");
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        try {
            String s = e.getActionCommand();

            if (s.equals("C")) {
                tf1.setText("");
                tf2.setText("");
                tfRes.setText("");
                return;
            }

            int n1 = Integer.parseInt(tf1.getText());
            int n2 = Integer.parseInt(tf2.getText());
            int result = 0;

            switch (s) {
                case "+": result = n1 + n2; break;
                case "-": result = n1 - n2; break;
                case "*": result = n1 * n2; break;
                case "/":
                    if (n2 == 0) {
                        tfRes.setText("Error");
                        return;
                    }
                    result = n1 / n2;
                    break;
                case "=": result = n1 + n2; break;
            }

            tfRes.setText(String.valueOf(result));

        } catch (Exception ex) {
            tfRes.setText("Invalid");
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
