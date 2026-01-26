import java.awt.*;
import java.awt.event.*;

public class NumberKeyDemo extends Frame implements ActionListener {

    TextField tf;

    NumberKeyDemo() {
        setLayout(new FlowLayout());
        tf = new TextField(20);
        add(tf);

        for (int i = 0; i <= 9; i++) {
            Button b = new Button("" + i);
            b.addActionListener(this);
            add(b);
        }

        setSize(300, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        tf.setText(tf.getText() + e.getActionCommand());
    }

    public static void main(String[] args) {
        new NumberKeyDemo();
    }
}
