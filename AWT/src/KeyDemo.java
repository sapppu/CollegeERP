import java.awt.*;
import java.awt.event.*;

class KeyDemo extends Frame implements KeyListener {
    TextField tf;
    Label l;

    KeyDemo() {
        tf = new TextField(20);
        tf.addKeyListener(this);

        l = new Label("Type something...");

        add(tf);
        add(l);

        setSize(300, 200);
        setLayout(new FlowLayout());
        setVisible(true);
    }

    public void keyTyped(KeyEvent e) { l.setText("Typed: " + e.getKeyChar()); }
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        new KeyDemo();
    }
}
