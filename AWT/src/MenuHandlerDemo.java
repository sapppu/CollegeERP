import java.awt.*;
import java.awt.event.*;

public class MenuHandlerDemo extends Frame implements ActionListener {

    MenuItem open, exit;

    MenuHandlerDemo() {
        MenuBar mb = new MenuBar();
        Menu file = new Menu("File");

        open = new MenuItem("Open");
        exit = new MenuItem("Exit");

        file.add(open);
        file.add(exit);
        mb.add(file);
        setMenuBar(mb);

        open.addActionListener(this);
        exit.addActionListener(this);

        setSize(300, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == open) setTitle("Open Selected");
        if (e.getSource() == exit) System.exit(0);
    }

    public static void main(String[] args) {
        new MenuHandlerDemo();
    }
}
