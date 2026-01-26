import java.awt.*;
import java.awt.event.*;

class WindowCloseDemo extends Frame {
    WindowCloseDemo() {
        setSize(300, 200);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        new WindowCloseDemo();
    }
}
