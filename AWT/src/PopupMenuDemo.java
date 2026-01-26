import java.awt.*;
import java.awt.event.*;

public class PopupMenuDemo extends Frame {

    PopupMenu pm;

    PopupMenuDemo() {
        pm = new PopupMenu();
        MenuItem cut = new MenuItem("Cut");
        MenuItem copy = new MenuItem("Copy");
        pm.add(cut);
        pm.add(copy);

        add(pm);

        addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger())
                    pm.show(PopupMenuDemo.this, e.getX(), e.getY());
            }
        });

        setSize(300, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new PopupMenuDemo();
    }
}
