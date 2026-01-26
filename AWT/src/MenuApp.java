import java.awt.*;
import java.awt.event.*;

public class MenuApp extends Frame {

    MenuApp() {

        MenuBar mb = new MenuBar();
        Menu file = new Menu("File");
        MenuItem exit = new MenuItem("Exit");
        file.add(exit);
        mb.add(file);

        exit.addActionListener(e -> System.exit(0));

        setMenuBar(mb);
        setSize(300, 200);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        new MenuApp();
    }
}
