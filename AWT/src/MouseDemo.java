import java.awt.*;
import java.awt.event.*;

class MouseDemo extends Frame implements MouseListener {

    Label l;

    MouseDemo() {
        l = new Label("Mouse Events");
        add(l);

        addMouseListener(this);

        setSize(300, 200);
        setLayout(new FlowLayout());
        setVisible(true);
    }

    public void mouseClicked(MouseEvent e) { l.setText("Clicked"); }
    public void mousePressed(MouseEvent e) { l.setText("Pressed"); }
    public void mouseReleased(MouseEvent e) { l.setText("Released"); }
    public void mouseEntered(MouseEvent e) { l.setText("Entered"); }
    public void mouseExited(MouseEvent e) { l.setText("Exited"); }

    public static void main(String[] args) {
        new MouseDemo();
    }
}
