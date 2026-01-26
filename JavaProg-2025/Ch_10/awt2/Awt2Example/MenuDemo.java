
import java.awt.*;
import java.awt.event.*;

public class MenuDemo extends Frame implements ActionListener {

    TextArea text1;
    MenuItem mi1;

    public MenuDemo() {

        // Frame Settings
        setTitle("DemoFrame");
        setSize(300, 300);
        setLayout(new BorderLayout());

        // Text Area
        text1 = new TextArea();
        add(text1, BorderLayout.CENTER);

        // Menu Bar
        MenuBar mb = new MenuBar();
        setMenuBar(mb);

        // File Menu
        Menu file = new Menu("File");
        mi1 = new MenuItem("File");
        file.add(mi1);
        mi1.addActionListener(this);

        file.add(new MenuItem("New"));
        file.add(new MenuItem("Open"));
        file.add(new MenuItem("Close"));
        file.add(new MenuItem("-"));
        file.add(new MenuItem("Quit"));
        mb.add(file);

        // Edit Menu
        Menu edit = new Menu("Edit");
        edit.add(new MenuItem("Cut"));
        edit.add(new MenuItem("Copy"));
        edit.add(new MenuItem("Paste"));
        edit.add(new MenuItem("-"));

        // Submenu
        Menu sub = new Menu("Special");
        sub.add(new MenuItem("ft"));
        sub.add(new MenuItem("st"));
        sub.add(new MenuItem("td"));
        edit.add(sub);

        edit.add(new CheckboxMenuItem("Debug", true));
        edit.add(new CheckboxMenuItem("Testing", true));
        mb.add(edit);

        // Close Window
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == mi1) {
            text1.append("File Menu Option Clicked\n");
        }
    }

    public static void main(String[] args) {
        new MenuDemo();
    }
}
