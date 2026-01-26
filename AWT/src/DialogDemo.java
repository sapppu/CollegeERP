import java.awt.*;
import java.awt.event.*;

public class DialogDemo extends Frame {

    Dialog d;

    DialogDemo() {
        Button b = new Button("Open Dialog");
        add(b);
        setLayout(new FlowLayout());

        d = new Dialog(this, "My Dialog", true);
        d.add(new Label("Dialog Box"));
        d.add(new Button("OK"));
        d.setSize(200, 100);

        b.addActionListener(e -> d.setVisible(true));

        setSize(300, 150);
        setVisible(true);
    }

    public static void main(String[] args) {
        new DialogDemo();
    }
}
