import java.awt.*;
import java.awt.event.*;

public class AnonymousDemo extends Frame {

    AnonymousDemo() {
        Button b = new Button("Click");
        add(b);
        setLayout(new FlowLayout());

        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setTitle("Anonymous Listener Worked!");
            }
        });

        setSize(300, 150);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AnonymousDemo();
    }
}
