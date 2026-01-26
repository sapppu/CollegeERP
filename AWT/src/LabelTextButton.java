import java.awt.*;
import java.awt.event.*;

class LabelTextButton extends Frame implements ActionListener {
    Label l;
    TextField tf;
    Button b;

    LabelTextButton() {
        setLayout(new FlowLayout());

        l = new Label("Enter your name:");
        tf = new TextField(20);
        b = new Button("Submit");

        add(l);
        add(tf);
        add(b);

        b.addActionListener(this);

        setSize(300, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Hello " + tf.getText());
    }

    public static void main(String[] args) {
        new LabelTextButton();
    }
}
