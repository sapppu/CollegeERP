import java.awt.*;
import java.awt.event.*;

public class AdapterDemo extends Frame {

    AdapterDemo() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setSize(300, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AdapterDemo();
    }
}
